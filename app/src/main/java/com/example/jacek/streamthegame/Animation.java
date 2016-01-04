package com.example.jacek.streamthegame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by jacek on 12/10/2015.
 */
public class Animation {

    public static final int ANIMATION_FRAMES = 9;
    public static final int MAX_ROTATIONS = 9;

    // this means that each sprite has one cell 50x50 pixels
    public static final int SPRITE_CELL_SIDE_PIXELS = 50;

    private Bitmap[] frames = new Bitmap[ANIMATION_FRAMES];
    private int currentFrame;
    private long startTime;
    private long delay = 200; // todo
    private boolean playedOnce;
    private int rotations = 0;
    private int frameHeight, frameWidth;

    public Animation(Bitmap spriteGrid, int frameHeight, int frameWidth) {
        this.startTime = System.nanoTime();
        this.frameHeight = frameHeight * SPRITE_CELL_SIDE_PIXELS;
        this.frameWidth = frameWidth * SPRITE_CELL_SIDE_PIXELS;

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
            this.playedOnce = true;
            this.currentFrame = ANIMATION_FRAMES - 1; // last frame will be shown after animation finishes
        }
    }

    public Bitmap getImage() {
        Matrix matrix = new Matrix();
        matrix.postRotate(90 * this.rotations);

        return Bitmap.createBitmap(
                this.frames[this.currentFrame], 0, 0, this.frameWidth,
                this.frameHeight, matrix, true);
    }

    public void rotate() {
        this.rotations = (this.rotations + 1) % MAX_ROTATIONS;
    }

    public boolean playedOnce() {
        return this.playedOnce;
    }

    public void setDelay(long d) {this.delay = d;}
}
