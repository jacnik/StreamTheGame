package com.example.jacek.streamthegame;

import android.graphics.Canvas;

/**
 * Created by jacek on 11/12/2015.
 */
public class Grid {

    private int rows, cols;
    private PointObject[] points;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.points = new PointObject[rows*cols];
    }

    public void draw(Canvas canvas) {
        int dx = canvas.getWidth() / (this.rows); // horizontal separation between points
        int dy = canvas.getHeight() / (this.cols); // vertical separation between points

        for(int i = 0; i < this.rows * this.cols; ++i) {
            PointObject item = this.points[i];
            if (item == null) {
                // create new item
                int col = i / this.cols;
                int row = i % this.cols;
                item = new PointObject(col * dx + dx/2, row * dy + dy/2);
//                if (row == 2 && col == 3) {
//                    item.activate();
//                }
                this.points[i] = item;
            }
            // redraw item
            item.draw(canvas);

        }

    }
}
