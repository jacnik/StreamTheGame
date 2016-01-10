package com.example.jacek.streamthegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


/**
 * Created by jacek on 1/10/2016.
 */
public class CongratsScreen {

    private final String CONGRATS = "Gratulacje";

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CongratsScreen() {
        this.paint.setColor(Color.WHITE);
        this.paint.setTextSize(100);
    }

    public void draw(Canvas canvas) {
        canvas.drawText(CONGRATS, canvas.getWidth()/2 - 200, canvas.getHeight() / 2, this.paint);
    }
}
