package com.example.jacek.streamthegame.Animations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.jacek.streamthegame.Exit;

/**
 * Created by jacek on 12/10/2015.
 */
abstract public class Animation {

    protected static final int ANIMATION_FRAMES = 9;
    private static final int SPRITE_CELL_SIDE_PIXELS = 50; // each cell in bitmap is 50x50 pixels

    protected Context context;
    protected Bitmap[] frames = new Bitmap[ANIMATION_FRAMES];
    protected Exit startCorner; //corner from which animation starts

    private int currentFrame;
    private long startTime;
    private long delay = 200;
    private boolean playedOnce;
    private int frameHeight, frameWidth;

    protected Animation(Context context, int frameHeight, int frameWidth) {
        this.context = context;
        this.frameHeight = frameHeight * SPRITE_CELL_SIDE_PIXELS;
        this.frameWidth = frameWidth * SPRITE_CELL_SIDE_PIXELS;
    }

    abstract public void setup(Exit exit);

    protected void createFramesFromImage(Bitmap spriteGrid, Matrix transform) {
        for (int i = 0; i < ANIMATION_FRAMES; ++i) {
            this.frames[i] = Bitmap.createBitmap(
                    spriteGrid,
                    i * this.frameWidth,
                    0,
                    this.frameWidth,
                    this.frameHeight,
                    transform, true);
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

    public Bitmap getImage() {
        return this.frames[this.currentFrame];
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
