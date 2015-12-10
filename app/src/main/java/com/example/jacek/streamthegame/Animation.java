package com.example.jacek.streamthegame;

import android.graphics.Bitmap;

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

    public Animation(Bitmap spriteGrid, int frameHeight, int frameWidth) {
        this.startTime = System.nanoTime();

        for (int i = 0; i < ANIMATION_FRAMES; ++i) {
            this.frames[i] = Bitmap.createBitmap(
                    spriteGrid,
                    i*frameWidth*SPRITE_CELL_SIDE_PIXELS,
                    0,
                    frameWidth * SPRITE_CELL_SIDE_PIXELS,
                    frameHeight * SPRITE_CELL_SIDE_PIXELS);
        }
    }
    public void update() {
        //if (this.playedOnce) return; // play only once

        long elapsed = (System.nanoTime() - this.startTime)/1000000;

        if (elapsed > delay)
        {
            this.currentFrame++;
            this.startTime = System.nanoTime();
        }

        if (this.currentFrame == ANIMATION_FRAMES) {
            this.playedOnce = true;
            this.currentFrame = 0; // after animating always show last frame todo: revert changes
        }
    }

    public Bitmap getImage() {
        return this.frames[this.currentFrame];
    }

    public void setDelay(long d) {this.delay = d;}
}
