package com.example.jacek.streamthegame.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.jacek.streamthegame.Animation;
import com.example.jacek.streamthegame.Exits;
import com.example.jacek.streamthegame.Sprite;


/**
 * Created by jacek on 11/15/2015.
 */
public abstract class GameObject {
    protected Bitmap image;
    protected int widthCells, heightCells; // width and height in cell units!
    protected int cellWidth, cellHeight;

    protected Exits exits;
    protected boolean isStatic;

    protected boolean isAnimating;
    protected Animation animation;

    protected GameObject(int cellWidth, int cellHeight) {
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    public abstract Sprite getType();

    public void update() { this.animation.update();}

    public Bitmap getImage() {
        if (this.isAnimating) {
            // images from animation are not scaled
            return Bitmap.createScaledBitmap(
                    this.animation.getImage(),
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

    public void startAnimation() {
        this.isAnimating = true;
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

        // rotate exits and animation
        this.exits.rotate();
        this.animation.rotate();

        // resize the image so it fits new dimensions
        this.image = Bitmap.createScaledBitmap(
                this.image,
                this.widthCells * this.cellWidth,
                this.heightCells * this.cellHeight,
                false);
    }
}
