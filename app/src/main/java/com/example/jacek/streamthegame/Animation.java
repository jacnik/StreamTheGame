package com.example.jacek.streamthegame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.jacek.streamthegame.GameObjects.GameObject;

/**
 * Created by jacek on 12/10/2015.
 */
public class Animation {

    public static final int ANIMATION_FRAMES = 9;

    // this means that each sprite has one cell 50x50 pixels
    public static final int SPRITE_CELL_SIDE_PIXELS = 50;

    private Bitmap[] frames = new Bitmap[ANIMATION_FRAMES];
    private int currentFrame;
    private long startTime;
    private long delay = 200; // todo
    private boolean playedOnce;
    private int frameHeight, frameWidth;
    private Direction startDirection; // direction from which animation starts
    private Exit startCorner; //corner from which animation starts
    private int symmetry;

    public Animation(Bitmap spriteGrid, int frameHeight, int frameWidth, Direction startDirection) {
        this.startTime = System.nanoTime();
        this.frameHeight = frameHeight * SPRITE_CELL_SIDE_PIXELS;
        this.frameWidth = frameWidth * SPRITE_CELL_SIDE_PIXELS;
        this.startDirection = startDirection;

        for (int i = 0; i < ANIMATION_FRAMES; ++i) {
            this.frames[i] = Bitmap.createBitmap(
                    spriteGrid,
                    i * this.frameWidth,
                    0,
                    this.frameWidth,
                    this.frameHeight);
        }
    }

    public void update() {
        if (this.playedOnce) return; // play only once

        long elapsed = (System.nanoTime() - this.startTime)/1000000;

        if (elapsed > delay)
        {
            this.currentFrame++;
            this.startTime = System.nanoTime();
        }

        if (this.currentFrame == ANIMATION_FRAMES) {
            this.currentFrame = ANIMATION_FRAMES - 1; // last frame will be shown after animation finishes
            this.playedOnce = true;
        }
    }

    public Bitmap getImage(Direction rotationDirection) {  // todo remove
        int rotationScale = this.startDirection.getDiffFrom(rotationDirection);

        Matrix matrix = new Matrix();
        matrix.postRotate(90 * rotationScale);

       // if (rotationScale == 2 || rotationScale == -2) { // todo handle mirror images
            //matrix.preScale(-1, 1); // mirror image
      //  }

        return Bitmap.createBitmap(
                this.frames[this.currentFrame], 0, 0, this.frameWidth,
                this.frameHeight, matrix, true);
    }

    public void setStartCorner(Exit corner) {
        this.startCorner = corner;
    }
    public void setSymmetry(int symmetry) {
        this.symmetry = symmetry;
    }

    public Bitmap getImage(Exit corner) {
        // todo set trensformation matrix in setter called before, here just use it
        Matrix transform = new Matrix();
        int nRotations = corner.getCorner() - this.startCorner.getCorner(); // todo

        switch (this.symmetry) {
            case GameObject.SYMMETRY_NONE:
                // todo for non symmetrical frames use two spritesheets
                //int rotationScale = corner.getCorner() - this.startCorner.getCorner(); // todo
                //transform.postRotate(90 * rotationScale);
                break;
            case GameObject.SYMMETRY_HEIGHT:
                transform.postRotate(90 * nRotations);
                if (nRotations == 3) {
                    transform.preScale(1,-1);
                }
                break;
            case GameObject.SYMMETRY_WIDTH:
                break;
            case GameObject.SYMMETRY_DIAG:
                break;
        }
        //int rotationScale = corner.getCorner() - this.startCorner.getCorner(); // todo



        // if (rotationScale == 2 || rotationScale == -2) { // todo handle mirror images
        //matrix.preScale(-1, 1); // mirror image
        //  }

        return Bitmap.createBitmap(
                this.frames[this.currentFrame], 0, 0, this.frameWidth,
                this.frameHeight, transform, true);
    }

    public boolean playedOnce() {
        return this.playedOnce;
    }

    public void reset() {
        this.currentFrame = 0;
        this.playedOnce = false;
    }

    public void setDelay(long d) {this.delay = d;}
}
