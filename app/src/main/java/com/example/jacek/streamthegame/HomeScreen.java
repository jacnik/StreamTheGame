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

    private static final String WELCOME = "Witaj w przejdzie";
    private static final String SELECT_LEVEL = "Wybierz poziom:";
    private Bitmap logoImage;
    private Rect logoRect;
    private Paint welcomePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint levelSelectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect[] levels;

    public HomeScreen(Context context, int levelCount) {
        this.logoImage = Bitmap.createBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.logo_ps));

        this.welcomePaint.setColor(Color.WHITE);
        this.welcomePaint.setTextSize(80);

        this.levelSelectPaint.setColor(Color.WHITE);
        this.levelSelectPaint.setTextSize(40);

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
        canvas.drawText(SELECT_LEVEL,
                canvas.getWidth() / 20,
                this.logoRect.bottom + this.levelSelectPaint.getTextSize(), this.levelSelectPaint);
        this.drawLevels(canvas);
    }

    public int clicked(int x, int y) {
        for (int i = 0; i < this.levels.length; ++i) {
            if (this.levels[i].contains(x,y)) {
                return i;
            }
        }
        return -1;
    }

    private void drawLevels(Canvas canvas) {
        if (this.levels[0] == null) {
            //initialize
            int cellWidth = canvas.getWidth() / (this.levels.length / 2);
            int cellHeight = (canvas.getHeight() - this.logoRect.height()) / 2;
            int topX = this.logoRect.bottom + (int)(this.levelSelectPaint.getTextSize() * 1.4f);
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

        int d = this.levels[0].width() > this.levels[0].height() ?
                this.levels[0].height() : this.levels[0].width(); // get smaller from width/height
        // draw rectangles and text
        for (int i = 0; i < this.levels.length; ++i) {
            canvas.drawCircle(
                    this.levels[i].centerX(), this.levels[i].centerY(),
                    d/2, this.getScaledPaint(i));
        }
    }

    private Paint getScaledPaint(int i) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        switch (i) {
            case 0:
                p.setColor(Color.rgb(25,2,214));
                return p;
            case 1:
                p.setColor(Color.rgb(1,40,223));
                return p;
            case 2:
                p.setColor(Color.rgb(1,149,221));
                return p;
            case 3:
                p.setColor(Color.rgb(0,223,160));
                return p;
            case 4:
                p.setColor(Color.rgb(9,223,1));
                return p;
            case 5:
                p.setColor(Color.rgb(111,223,0));
                return p;
            case 6:
                p.setColor(Color.rgb(215,223,0));
                return p;
            case 7:
                p.setColor(Color.rgb(221,106,0));
                return p;
            case 8:
                p.setColor(Color.rgb(222,0,11));
                return p;
            case 9:
                p.setColor(Color.rgb(221,0,220));
                return p;
            default:
                p.setColor(Color.rgb(255,255,255)); // default - WHITE
                return p;
        }
    }
}
