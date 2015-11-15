package com.example.jacek.streamthegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by jacek on 11/15/2015.
 */
public class GameObject {
    private Bitmap image;

    public GameObject(Bitmap image) {
        this.image = image;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, 0, 0, null);
    }

    public void update() {}
}
