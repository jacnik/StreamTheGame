package com.example.jacek.streamthegame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jacek on 11/12/2015.
 */
public class Grid {

    private int nRows, nCols;
    private int cellWidth, cellHeight;
    private int lastActivatedPoint;

    private Context context;
    private PointObject[] points;
    private HashMap<GameObject, Point> objects = new HashMap<>();

    public Grid(Context context, int rows, int cols, int cellWidth, int cellHeight) {
        this.context = context;
        this.nRows = rows;
        this.nCols = cols;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;

        this.points = new PointObject[rows*cols];
    }

    public void draw(Canvas canvas) {
        for(int i = 0; i < this.nRows * this.nCols; ++i) {
            PointObject item = this.points[i];
            if (item == null) {
                // create new item
                int col = i / this.nCols;
                int row = i % this.nCols;
                item = new PointObject(
                        col * this.cellWidth + this.cellWidth/2,
                        row * this.cellHeight + this.cellHeight/2);
                this.points[i] = item;
            }
            // redraw item
            item.draw(canvas);
        }

//        for(GameObject obj : this.objects.keySet()) {
//            obj.draw(canvas, 0 ,0);
//        }
        for(Map.Entry<GameObject, Point> entry : this.objects.entrySet()) {
            canvas.drawBitmap(
                    entry.getKey().getImage(),
                    this.cellHeight * entry.getValue().x,
                    this.cellHeight * entry.getValue().y,
                    null);
        }
    }

    public void activatePoint(int row, int col) {
        this.points[this.lastActivatedPoint].deactivate();
        this.lastActivatedPoint = row*this.nCols + col;
        this.points[this.lastActivatedPoint].activate(); // todo overflow checking
    }

    public int getCellWidth() {
        return this.cellWidth;
    }

    public int getCellHeight() {
        return this.cellHeight;
    }

    public void tryAddObject(Sprite sprite, int col, int row ) {
        GameObject pipe = null;
        switch (sprite) {
            case short_pipe:
                pipe = new GameObject(BitmapFactory.decodeResource(
                        this.context.getResources(),
                        R.drawable.short_pipe), 1, 2);
                pipe.resize(this.cellWidth, this.cellHeight * 2);
                this.objects.put(pipe, new Point(col, row));
                break;
            case rotated_short_pipe:
                pipe = new GameObject(BitmapFactory.decodeResource(
                        this.context.getResources(),
                        R.drawable.short_pipe), 1, 2);
                pipe.resize(this.cellWidth, this.cellHeight * 2);
                pipe.rotate();
                this.objects.put(pipe, new Point(col, row));
                break;
            default: break;
        }
    }
}
