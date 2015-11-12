package com.example.jacek.streamthegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by jacek on 11/12/2015.
 */
public class PointObject {
    private boolean isActive;
    private int x, y;
    private Paint paint = new Paint();;
    private final int POINT_SIZE = 10; // todo: calculate that based on window size + make min and max values


    public PointObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.paint.setColor(Color.RED);
        this.paint.setStrokeWidth(7);
    }

    public void draw(Canvas canvas) {
        if (this.isActive) {
            // draw circle
            canvas.drawCircle(this.x, this.y, this.POINT_SIZE * 1.5f, this.paint);
        } else {
            // draw cross
            canvas.drawLine(
                    this.x - this.POINT_SIZE, this.y,
                    this.x + this.POINT_SIZE, this.y,
                    this.paint);
            canvas.drawLine(
                    this.x, this.y - this.POINT_SIZE,
                    this.x, this.y + this.POINT_SIZE,
                    this.paint);
        }

    }

    public boolean isActive() {
        return this.isActive;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public int getX() {
        return this.x;
    }
}
