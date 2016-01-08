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
        Point crds = this.getCoordsFromCorner(this.exit1.getCorner());
        if (this.exit1.getDir() == dir && row == crds.x && col == crds.y) return true;
        crds = this.getCoordsFromCorner(this.exit2.getCorner());
        if (this.exit2.getDir() == dir && row == crds.x && col == crds.y) return true;
        return false;
    }

    public Exit getExitAt(int row, int col, Direction dir) {
        Point crds = this.getCoordsFromCorner(this.exit1.getCorner());
        if (this.exit1.getDir() == dir && row == crds.x && col == crds.y) return this.exit1;
        crds = this.getCoordsFromCorner(this.exit2.getCorner());
        if (this.exit2.getDir() == dir && row == crds.x && col == crds.y) return this.exit2;
        return null;
    }

    /** Rotates current object 90*rotations degrees */
    public void rotate(int rotations) {
        Matrix matrix = new Matrix();
        matrix.postRotate(90 * rotations);

        this.image = Bitmap.createBitmap(
                this.image, 0, 0, this.image.getWidth(),
                this.image.getHeight(), matrix, true);

        for (int i = 0; i < rotations % 4; ++i) {
            // swap the dimensions
            int tmp = this.widthCells;
            this.widthCells = this.heightCells;
            this.heightCells = tmp;

            this.rotateExits();
        }

        // resize the image so it fits new dimensions
        this.image = Bitmap.createScaledBitmap(
                this.image,
                this.widthCells * this.cellWidth,
                this.heightCells * this.cellHeight,
                false);
    }

    /** just rotate 90Deg */
    public void rotate() {
        this.rotate(1);
    }

    public void resetAnimation() {
        this.animation.reset();
        this.isAnimating = false;
        this.finishedAnimating = false;
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
}
