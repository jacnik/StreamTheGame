package com.example.jacek.streamthegame.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;

import com.example.jacek.streamthegame.Animation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.Sprite;


/**
 * Created by jacek on 11/15/2015.
 */
public abstract class GameObject {
    protected Bitmap image;
    protected int widthCells, heightCells; // width and height in cell units!
    protected int cellWidth, cellHeight;

    protected Exit exit1;
    protected Exit exit2;
    protected Exit animationStartExit;
    protected boolean isStatic;

    protected boolean isAnimating;
    private boolean finishedAnimating;
    protected Animation animation;

    protected GameObject(int cellWidth, int cellHeight) {
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    public abstract Sprite getType();

    public void update() {
        if (this.animation.playedOnce()) {
            this.isAnimating = false;
            this.finishedAnimating = true;
        } else {
            this.animation.update();
        }
    }

    public Bitmap getImage() {
        if (this.isAnimating || this.finishedAnimating()) {
            // images from animation are not scaled
            return Bitmap.createScaledBitmap(
                    this.animation.getImage(this.animationStartExit.getDir()),
                    this.widthCells * this.cellWidth,
                    this.heightCells * this.cellHeight,
                    false);
        } else {
            return this.image;
        }
    }

    public int getWidthCells() {
        return this.widthCells;
    }

    public int getHeightCells() {
        return  this.heightCells;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    public void startAnimation(Exit exit) {
        this.animationStartExit = exit;
        this.isAnimating = true;
    }

    public boolean isAnimating() {
        return this.isAnimating;
    }

    public boolean finishedAnimating() {
        return this.finishedAnimating;
    }

    public Exit getAnimationEndExit() {
        if (this.animationStartExit == this.exit1) {
            return this.exit2;
        } else {
            return this.exit1;
        }
    }

    public boolean hasExitAt(int row, int col, Direction dir) {
        int corner = this.getCornerFromCoords(row, col);
        if (this.exit1.getDir() == dir && this.exit1.getCorner() == corner) return true;
        if (this.exit2.getDir() == dir && this.exit2.getCorner() == corner) return true;
        return false;
    }

    public Exit getExitAt(int row, int col, Direction dir) {
        int corner = this.getCornerFromCoords(row, col);
        if (this.exit1.getDir() == dir && this.exit1.getCorner() == corner) return this.exit1;
        if (this.exit2.getDir() == dir && this.exit2.getCorner() == corner) return this.exit2;
        return null;
    }

    /* No additional parameters because only rotation 90 deg right is available */
    public void rotate() {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        this.image = Bitmap.createBitmap(
                this.image, 0, 0, this.image.getWidth(),
                this.image.getHeight(), matrix, true);

        // swap the dimensions
        int tmp = this.widthCells;
        this.widthCells = this.heightCells;
        this.heightCells = tmp;

        this.rotateExits();

        // resize the image so it fits new dimensions
        this.image = Bitmap.createScaledBitmap(
                this.image,
                this.widthCells * this.cellWidth,
                this.heightCells * this.cellHeight,
                false);
    }

    /** x = rowCoordinate, y = col coordinate */
    public Point getCoordsFromCorner(int corner) {
        switch (corner) {
            case 0: return new Point(0, 0);
            case 1: return new Point(0, this.getWidthCells() - 1);
            case 2: return new Point(this.getHeightCells() - 1, this.getWidthCells() - 1);
            case 3: return new Point(this.getHeightCells() - 1, 0);
            default: return null;
        }
    }

    private void rotateExits() {
        this.exit1.rotate();
        this.exit2.rotate();
    }

    /*
    * value of the corner in which the exit appears
    * 0 -> upper left   coordinates (row, column): (0,0)
    * 1 -> upper right   (0, lastCol)
    * 2 -> lower right   (lastRow, lastCol)
    * 3 -> lower left    (lastRow, 0)
    * */
    private int getCornerFromCoords(int row, int col) {
        if (row == 0 && col == 0) return 0;
        else if (row == 0) return 1;
        else if (col == 0) return 3;
        else return 2;
    }
}
