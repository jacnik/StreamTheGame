package com.example.jacek.streamthegame;

import android.graphics.Canvas;

/**
 * Created by jacek on 11/12/2015.
 */
public class Grid {

    private int nRows, nCols;
    private int cellWidth, cellHeight;

    private PointObject[] points;

    public Grid(int rows, int cols, int cellWidth, int cellHeight) {
        this.nRows = rows;
        this.nCols = cols;

        this.points = new PointObject[rows*cols];
    }

    public void draw(Canvas canvas) {
        int dx = canvas.getWidth() / this.nRows; // horizontal separation between points
        int dy = canvas.getHeight() / this.nCols; // vertical separation between points

        for(int i = 0; i < this.nRows * this.nCols; ++i) {
            PointObject item = this.points[i];
            if (item == null) {
                // create new item
                int col = i / this.nCols;
                int row = i % this.nCols;
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

    public void activatePoint(int row, int col) {
        this.points[row*this.nCols + col].activate(); // todo overflow checking
    }
}
