package com.example.jacek.streamthegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

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
        // empty
    }

    public void draw(Canvas canvas) {
        // todo: handle background in xml
        // http://androidforbeginners.blogspot.com/2010/06/how-to-tile-background-image-in-android.html
        Paint paint = new Paint();
        //paint.setAlpha(100);
        for(int i = 0; i <= canvas.getWidth(); i += this.image.getWidth() ) {
            for(int j = 0; j <= canvas.getHeight(); j += this.image.getHeight() ) {
                canvas.drawBitmap(this.image, this.x + i, this.y + j, paint);
            }
        }
    }

}
