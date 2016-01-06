package com.example.jacek.streamthegame;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jacek on 1/6/2016.
 */
public class HomeScreen {

    private Context context;
    private Rect logo;

    public HomeScreen(Context context) {
        this.context = context;
    }

    public void draw(Canvas canvas) {
        Bitmap image = Bitmap.createBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.logo_ps));
        Rect rect = new Rect(
                canvas.getWidth()/2 - image.getWidth()/2,
                canvas.getHeight()/2 - image.getHeight()/2,
                canvas.getWidth()/2 + image.getWidth()/2,
                canvas.getHeight()/2 + image.getHeight()/2); //left top, right, bottom
        this.logo = rect;
        canvas.drawBitmap(image, null, rect, new Paint());
    }

    public int clicked(int x, int y) {
        if(this.logo.contains(x, y)) {
            return 1;
        }
        return 0;
    }
}
