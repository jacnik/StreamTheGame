package com.example.jacek.streamthegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by jacek on 11/11/2015.
 */
public class Background {

    private Bitmap image;
    private int x = 0, y = 0;

    public Background(Bitmap res) {
        this.image = res;
    }

    public void update() {
        this.x += -5;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, this.x, this.y, null);
    }
}
