package com.example.jacek.streamthegame;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by jacek on 1/6/2016.
 */
public class HomeScreen {

    private static final String WELCOME = "Witaj w przejdzie :)";
    private Bitmap logoImage;
    private Rect logoRect;
    private Paint welcomePaint = new Paint(Paint.LINEAR_TEXT_FLAG);
    private Rect[] levels;

    public HomeScreen(Context context, int levelCount) {
        this.logoImage = Bitmap.createBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.logo_ps));

        this.welcomePaint.setColor(Color.WHITE);
        this.welcomePaint.setTextSize(80);

        this.levels = new Rect[levelCount];
    }

    public void draw(Canvas canvas) {
        if (this.logoRect == null) {
            this.logoRect = new Rect(
                    canvas.getWidth()/20,
                    canvas.getHeight()/20,
                    canvas.getWidth()/20 + this.logoImage.getWidth(),
                    canvas.getHeight()/20 + + this.logoImage.getHeight());
        }

        canvas.drawBitmap(this.logoImage, null, this.logoRect, this.welcomePaint);
        canvas.drawText(WELCOME,
                canvas.getWidth() / 20 + this.logoImage.getWidth() + 20,
                canvas.getHeight() / 20 + this.logoImage.getHeight() * 0.8f, this.welcomePaint);
        this.drawLevels(canvas);
    }

    public int clicked(int x, int y) {
        if(this.logoRect.contains(x, y)) {
            return 1;
        }
        return 0;
    }

    private void drawLevels(Canvas canvas) {
        if (this.levels[0] == null) {
            //initialize
            int cellWidth = canvas.getWidth() / (this.levels.length / 2);
            int cellHeight = (canvas.getHeight() - this.logoRect.height()) / 2;
            int topX = this.logoRect.bottom;
            // first row
            int i = 0;
            for (; i < this.levels.length / 2; ++i) {
                this.levels[i] = new Rect(
                        i * cellWidth,
                        topX,
                        (i + 1) * cellWidth,
                        this.logoRect.height() + cellHeight);
            }

            // second row
            for (int j = 0; i < this.levels.length; ++i, ++j) {
                this.levels[i] = new Rect(
                        j * cellWidth,
                        topX + cellHeight,
                        (j + 1) * cellWidth,
                        this.logoRect.height() + 2 * cellHeight);
            }
        }

        Paint p = new Paint(Color.BLUE);
        p.setColor(Color.BLUE);
        // draw rectangles and text
        for (int i = 0; i < this.levels.length; ++i) {
            canvas.drawRect(this.levels[i], p);
            canvas.drawText(Integer.toString(i + 1), this.levels[i].centerX(), this.levels[i].centerY(), welcomePaint);
        }
    }
}
