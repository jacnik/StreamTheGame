package com.example.jacek.streamthegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.jacek.streamthegame.GameObjects.GameObject;

import java.util.HashSet;

/**
 * Created by jacek on 11/12/2015.
 */
public class Grid {

    private AnimationFinishedListener animationFinishedListener;

    private int nRows, nCols;
    private int cellWidth, cellHeight;
    private int canvasHeight, canvasWidth;

    // currentLayout maps all cells with object that are placed on them
    // from item number to coordinates:
    //      int col = i / this.nCols;
    //      int row = i % this.nCols;
    // from coordinates to element in the array:
    //      int i = row*this.nCols + col;
    private GameObject[] currentLayout;
    private GameObjectFactory gameObjectFactory;
    private Paint drawingPaint = new Paint();
    private HashSet<GameObject> drawn = new HashSet<>(); // for optimisation don't make this local

    public Grid(Context context, int canvasHeight, int canvasWidth) {
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
        this.gameObjectFactory = new GameObjectFactory(context);
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < this.nRows * this.nCols; ++i) {
            GameObject item = currentLayout[i];
            if (item != null && !drawn.contains(item)) {
                int row = i / this.nCols;
                int col = i % this.nCols;
                // draw Object
                canvas.drawBitmap(
                    item.getImage(),
                    this.cellWidth * col,
                    this.cellHeight * row,
                    this.drawingPaint);
                drawn.add(item);
            }
        }
        drawn.clear();
    }

    public int getCellWidth() {
        return this.cellWidth;
    }

    public int getCellHeight() { return this.cellHeight; }

    public GameObject getObjectFromCoords(int row, int col) {
        return this.currentLayout[row*this.nCols + col];
    }

    public void rotateObjAt(int row, int col) {
        GameObject obj = this.getObjectFromCoords(row, col);
        if (obj != null) {
            Point startCoords = this.getStartCoords(row, col);
            if (this.isRotationSave(startCoords.x, startCoords.y)) {
                this.removeObject(obj);
                obj.rotate();
                this.addToLayout(obj, startCoords.x, startCoords.y);
            }
        }
    }

    /**
     * @param row: row coordinate of the object to move.
     * @param col: col coordinate of the object to move.
     * @param drow: movement in y-axis: + row down, - row up
     * @param dcol: movement in x-axis: + col right, - col left
     * @return true if move was successful
     * */
    public boolean moveObjAt(int row, int col, int drow, int dcol) {
        GameObject obj = this.getObjectFromCoords(row, col);
        if (obj != null) {
            Point startCoords = this.getStartCoords(row, col);
            if (!this.checkObjCollision(startCoords.x, startCoords.y, drow, dcol)) {
                this.removeObject(obj);
                this.addToLayout(obj, startCoords.x + drow, startCoords.y + dcol);
                return true;
            }
        }
        return false;
    }

    public void setLevel(LevelDefinition level) {
        this.nRows = level.getHeight();
        this.nCols = level.getWidth();
        this.cellHeight = this.canvasHeight/this.nRows;
        this.cellWidth = this.canvasWidth/this.nCols;
        this.currentLayout = new GameObject[this.nRows*this.nCols];
        this.addLevelObjects(level);
    }

    public void update() {
        for (int i = 0; i < this.nRows * this.nCols; ++i) {
            GameObject obj = this.currentLayout[i];
            if (obj != null && obj.isAnimating()) {
                this.updateAnimations(obj, i);
                break;
            }
        }
    }

    public synchronized void registerAnimationFinishedHandler(AnimationFinishedListener listener) {
        this.animationFinishedListener = listener;
    }

    public void setBlur() {
        this.drawingPaint.setAlpha(150);
    }

    public void resetBlur() {
        this.drawingPaint.setAlpha(255);
    }

    private void updateAnimations(GameObject obj, int pos) {
        obj.update();
        if (obj.finishedAnimating()) {
            if (obj.getType() == Sprite.enter) {
                this.animationFinished(true);
            } else {
                this.startPairedAnimation(obj, pos);
            }
        }
    }

    private void removeObject(GameObject obj) {
        for (int i = 0; i < this.nRows * this.nCols; ++i) {
            if(this.currentLayout[i] == obj) {
                this.currentLayout[i] = null;
            }
        }
    }

    private void addLevelObjects(LevelDefinition level) {
        for(GameObjectDefinition objDef : level.getObjects()) {
            GameObject obj =  this.gameObjectFactory.getObject(
                    objDef.getSprite(), this.cellWidth, this.cellHeight);
            obj.rotate(objDef.getRotations());
            this.addToLayout(obj, objDef.getInsertionRow(), objDef.getInsertionCol());
        }
    }

    private void startPairedAnimation(GameObject obj, int pos) {
        //find another object in chain to start animation on it
        Exit exit = obj.getAnimationEndExit();
        Point cornerCoords = obj.getCoordsFromCorner(exit.getCorner());
        int oldRow = pos / this.nCols + cornerCoords.x;
        int oldCol = pos % this.nCols + cornerCoords.y;

        int newRow = this.getPairedRow(exit.getDir(), oldRow);
        int newCol = this.getPairedCol(exit.getDir(), oldCol);

        if (this.hasPairedExit(newRow, newCol, exit.getDir())) {
            GameObject newObj = this.getObjectFromCoords(newRow, newCol);
            //if (!newObj.finishedAnimating()) {
                newObj.startAnimation(this.getPairedExit(newRow, newCol, exit.getDir()));
           // }
        } else {
            //this.startFailLevelAnimation(newRow, newCol, exit.getDir()); // todo maybe in the futute, for now just reset animations
            this.resetAnimations();
            this.animationFinished(false);
        }
    }

    private int getPairedRow(Direction dir, int row) {
        switch(dir) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
        }
        return row;
    }

    private int getPairedCol(Direction dir, int col) {
        switch(dir) {
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
        }
        return col;
    }

    private boolean hasPairedExit(int row, int col, Direction dir) {
        if (row < 0 || row >= this.nRows) return false;
        if (col < 0 || col >= this.nCols) return false;
        GameObject obj = this.getObjectFromCoords(row, col);
        if(obj == null) return false;

        Point startCoords = this.getStartCoords(row, col);
        return obj.hasExitAt(row - startCoords.x, col - startCoords.y, dir.opposite());
    }

    private Exit getPairedExit(int row, int col, Direction dir) {
        GameObject obj = this.getObjectFromCoords(row, col);
        Point startCoords = this.getStartCoords(row, col);
        return obj.getExitAt(row - startCoords.x, col - startCoords.y, dir.opposite());
    }

    /** Performs add to layout without any collision and boundary checks.
     * Assumes that checks for save insert was performed prior to calling this function.
     * @param object: object to add to layout.
     * @param row: x coordinate of the upper left corner of the object.
     * @param col: y coordinate of the upper left corner of the object.
     * */
    private void addToLayout(GameObject object, int row, int col) {
        int width = object.getWidthCells();
        int height = object.getHeightCells();

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pos = (row + i)*this.nCols + (col+j);
                this.currentLayout[pos] = object;
            }
        }
    }

    /**
     * return upper left corner coordinates of an object from row and col
     * @return Point object: x = row, y = col
     * */
    private Point getStartCoords(int row, int col) {
        GameObject obj = this.getObjectFromCoords(row, col);
        if(obj == null) return new Point(row, col);

        // first check up
        for(int r = row - 1; r >= 0; --r) {
            if (this.getObjectFromCoords(r, col) == obj) {
                row = r;
            } else break;
        }
        // then check left
        for(int c = col - 1; c >= 0; --c) {
            if (this.getObjectFromCoords(row, c) == obj) {
                col = c;
            } else break;
        }

        return new Point(row, col);
    }

    /**
     * Important note: assumes that srow and scol are coordinates
     * of the upper left corner of an object
     *
     * @param srow: start (upper left) row
     * @param scol: start (upper left) col
     * @param drow: movement in y-axis: + row down, - row up
     * @param dcol: movement in x-axis: + col right, - col left
     * @returns:
     * true = collision detected
     * false = collision not detected
     * */
    private boolean checkObjCollision(int srow, int scol, int drow, int dcol) {
        GameObject obj = this.getObjectFromCoords(srow, scol);
        int width = obj.getWidthCells();
        int height = obj.getHeightCells();

        int row = srow + drow; // new insertion row
        int col = scol + dcol; // new insertion col

        // check boundaries
        if (row < 0 || row + height > this.nRows) return true;
        if (col < 0 || col + width > this.nCols) return true;

        // check if all cells occupied by object are empty
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pos = (row + i)*this.nCols + (col+j);
                if (this.currentLayout[pos] != null && this.currentLayout[pos] != obj) {
                    return true;
                }
            }
        }
        // if checks above didn't find a collision there is none
        return false;
    }

    /**
     * checks if object rotation is not colliding with other object and screen end
     * @param srow: start (upper left) row of an object
     * @param scol: start (upper left) col of an object
     * */
    private boolean isRotationSave(int srow, int scol) {
        GameObject obj = this.getObjectFromCoords(srow, scol);
        // after rotation objects dimensions will swap
        int height = obj.getWidthCells();
        int width = obj.getHeightCells();

        if (srow + height > this.nRows) return false;
        if (scol + width > this.nCols) return false;

        // check if all new cells occupied by object are empty
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pos = (srow + i)*this.nCols + (scol+j);
                if (this.currentLayout[pos] != null && this.currentLayout[pos] != obj) {
                    return false;
                }
            }
        }
        return true;
    }

    private synchronized void resetAnimations() {
        HashSet<GameObject> visited = new HashSet<>();
        for (GameObject obj : this.currentLayout) {
            if (obj != null && !visited.contains(obj)) {
                obj.resetAnimation();
                visited.add(obj);
            }
        }
    }

    private synchronized void animationFinished (boolean isSuccess) {
        AnimationFinishedEvent event = new AnimationFinishedEvent(this, isSuccess);
        if(this.animationFinishedListener != null){
            this.animationFinishedListener.animationFinished(event);
        }
    }

    private void clearLayout() {
        for (GameObject obj : this.currentLayout) {
            obj = null;
        }
    }
}
