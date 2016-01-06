package com.example.jacek.streamthegame;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jacek on 1/6/2016.
 */
public class HomeScreen {

    private Bitmap logoImage;
    private Rect logoRect;
    private Paint p = new Paint(Paint.LINEAR_TEXT_FLAG);
    private static final String WELCOME = "Witaj w przejdzie :)";

    public HomeScreen(Context context) {
        this.logoImage = Bitmap.createBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.logo_ps));

        this.p.setColor(Color.WHITE);
        this.p.setTextSize(80);
    }

    public void draw(Canvas canvas) {
        if (this.logoRect == null) {
            this.logoRect = new Rect(
                    canvas.getWidth()/20,
                    canvas.getHeight()/20,
                    canvas.getWidth()/20 + this.logoImage.getWidth(),
                    canvas.getHeight()/20 + + this.logoImage.getHeight());
//                    canvas.getWidth() / 2 - logoImage.getWidth() / 2,
//                    canvas.getHeight() / 2 - logoImage.getHeight() / 2,
//                    canvas.getWidth() / 2 + logoImage.getWidth() / 2,
//                    canvas.getHeight() / 2 + logoImage.getHeight() / 2); //left top, right, bottom
        }

        canvas.drawBitmap(this.logoImage, null, this.logoRect, this.p);
//        canvas.drawText(WELCOME,
//                canvas.getWidth() / 2 - logoImage.getWidth() - 60, // manual centering :)
//                canvas.getHeight() / 2 - logoImage.getHeight(), this.p);
        canvas.drawText(WELCOME,
                canvas.getWidth()/20 + this.logoImage.getWidth() + 20,
                canvas.getHeight()/20 + this.logoImage.getHeight()*0.8f  , this.p);
    }

    public int clicked(int x, int y) {
        if(this.logoRect.contains(x, y)) {
            return 1;
        }
        return 0;
    }
}
