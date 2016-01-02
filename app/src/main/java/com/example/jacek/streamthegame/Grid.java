package com.example.jacek.streamthegame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.jacek.streamthegame.GameObjects.GameObject;

import java.util.HashSet;

/**
 * Created by jacek on 11/12/2015.
 */
public class Grid {

    private int nRows, nCols;
    private int cellWidth, cellHeight;

    // currentLayout maps all cells with object that are placed on them
    // from item number to coordinates:
    //      int col = i / this.nCols;
    //      int row = i % this.nCols;
    // from coordinates to element in the array:
    //      int i = row*this.nCols + col;
    private GameObject[] currentLayout; // todo maybe new class.

    private Context context;
    private GameObjectFactory gameObjectFactory;

    public Grid(Context context, int rows, int cols, int cellWidth, int cellHeight) {
        this.context = context; // TODO: replace context with gameobj factory
        this.gameObjectFactory = new GameObjectFactory(context);

        this.nRows = rows;
        this.nCols = cols;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;

        this.currentLayout = new GameObject[rows*cols];
    }

    public void draw(Canvas canvas) {

        HashSet<GameObject> drawn = new HashSet<>();
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
                    null);
                drawn.add(item);
            }
        }
    }

    public int getCellWidth() {
        return this.cellWidth;
    }

    public int getCellHeight() {
        return this.cellHeight;
    }

    public void tryAddObject(Sprite sprite, int row, int col ) {
        GameObject obj;
        Exits exits;
        switch (sprite) {
            case exit:
                exits = new Exits(0, 0, 0, 0, Direction.RIGHT, Direction.RIGHT, 1, 1);
                obj = new GameObject(BitmapFactory.decodeResource(
                        this.context.getResources(),
                        R.drawable.exit_valve_arrow),
                        1, 1, this.cellWidth, this.cellHeight, exits, true);
                obj.setAnimation(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.exit_valve_spritesheet));
                this.addToLayout(obj, row, col);
                break;
            case enter:
                exits = new Exits(0, 0, 0, 0, Direction.RIGHT, Direction.RIGHT, 1, 1);
                obj = new GameObject(BitmapFactory.decodeResource(
                        this.context.getResources(),
                        R.drawable.enter_valve_arrow),
                        1, 1, this.cellWidth, this.cellHeight, exits, true);
                obj.rotate(); // todo: remove that and add separate enums for rotated objects
                this.addToLayout(obj, row, col);
                break;
            case short_pipe:
                exits = new Exits(0, 0, 1, 0, Direction.UP, Direction.DOWN, 1, 2);
                obj = new GameObject(BitmapFactory.decodeResource(
                        this.context.getResources(),
                        R.drawable.short_pipe),
                        1, 2,
                        this.cellWidth, this.cellHeight, exits);
                obj.setAnimation(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.short_pipe_spritesheet));
                this.addToLayout(obj, row, col);
                break;
            case rotated_short_pipe:
//                obj = new GameObject(BitmapFactory.decodeResource(
//                        this.context.getResources(),
//                        R.drawable.short_pipe),
//                        1, 2,
//                        this.cellWidth, this.cellHeight);
//                obj.rotate();
                //this.objects.put(pipe, point);
                break;
            case bend2:
                exits = new Exits(0, 1, 1, 0, Direction.UP, Direction.LEFT, 2, 2);
                obj = new GameObject(BitmapFactory.decodeResource(
                        this.context.getResources(),
                        R.drawable.bend2),
                        2, 2,
                        this.cellWidth, this.cellHeight, exits);
                this.addToLayout(obj, row, col);
                break;
            default: break;
        }
    }

    public GameObject getObjectFromCoords(int row, int col) {
        GameObject res = this.currentLayout[row*this.nCols + col];
        if (res != null && res.isStatic()) { // check if res isStatic and return null if it is
            return null;
        }
        return res;
    }

    public void rotateObjAt(int row, int col) {
        GameObject obj = this.getObjectFromCoords(row, col);
        if (obj != null) {
            this.removeObject(obj);
            obj.rotate();
            this.addToLayout(obj, row, col);
        }
    }

    public void removeObject(GameObject obj) {
        for (int i = 0; i < this.nRows * this.nCols; ++i) {
            if(this.currentLayout[i] == obj) {
                this.currentLayout[i] = null;
            }
        }
    }

    /** Adds GameObject to layout in every row and col that this GameObject will occupy.
     * @param object: object to add to layout.
     * @param row: x coordinate of the upper left corner of the object.
     * @param col: y coordinate of the upper left corner of the object.
     * */
    public void addToLayout(GameObject object, int row, int col) {
        int width = object.getWidthCells();
        int height = object.getHeightCells();
        // width and height are properties of an object

        int[] insertionPoints = new int[height*width];
        boolean insertSafe = true;

        mainLoop:
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pos = (row + i)*this.nCols + (col+j);
                //this.currentLayout[pos] = object;
                if (pos < this.nRows * this.nCols
                       && this.currentLayout[pos] == null
                        /* && add boundary detection
                        * basically it will need to be something like
                        * col + width < last column number in this row*/) {
                    insertionPoints[i*width + j] = pos;
                } else {
                    insertSafe = false;
                    break mainLoop;
                }
            }
        }

        if (insertSafe) {
            for (int i = 0; i < height*width; ++i) {
                this.currentLayout[insertionPoints[i]] = object;
            }
        }
    }

    public void update() {
        HashSet<GameObject> updated = new HashSet<>();
        for (GameObject obj : this.currentLayout) {
            if (obj != null && !updated.contains(obj)) {
                obj.update();
                updated.add(obj);
            }
        }
    }

    private void clearLayout() {
        for (GameObject obj : this.currentLayout) {
            obj = null;
        }
    }
}
