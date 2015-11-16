package com.example.jacek.streamthegame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
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
    //private PointObject[] points;
    private HashMap<GameObject, Point> objects = new HashMap<>();
    private ArrayList<Point> points = new ArrayList<>();

    public Grid(Context context, int rows, int cols, int cellWidth, int cellHeight) {
        this.context = context;
        this.nRows = rows;
        this.nCols = cols;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;

        //this.points = new PointObject[rows*cols];
    }

    public void draw(Canvas canvas) {
//        for(int i = 0; i < this.nRows * this.nCols; ++i) {
//            PointObject item = this.points[i];
//            if (item == null) {
//                // create new item
//                int col = i / this.nCols;
//                int row = i % this.nCols;
//                item = new PointObject(
//                        col * this.cellWidth + this.cellWidth/2,
//                        row * this.cellHeight + this.cellHeight/2);
//                this.points[i] = item;
//            }
//            // redraw item
//            item.draw(canvas);
//        }

//        for(GameObject obj : this.objects.keySet()) {
//            obj.draw(canvas, 0 ,0);
//        }

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        for (Point point : this.points) {
            int startX = point.x * this.cellWidth;
            int startY = point.y * this.cellHeight;
            canvas.drawRect(
                    startX, // start x
                    startY,  // start y
                    startX + this.cellWidth, // end x
                    startY + this.cellHeight, // end y
                    paint);
        }

        for(Map.Entry<GameObject, Point> entry : this.objects.entrySet()) {
//            canvas.drawBitmap(
//                    entry.getKey().getImage(),
//                    this.cellWidth * entry.getValue().y,
//                    this.cellHeight * entry.getValue().x,
//                    null);
            canvas.drawBitmap(
                    entry.getKey().getImage(),
                    this.cellWidth * entry.getValue().y,
                    this.cellHeight * entry.getValue().x,
                    null);
        }
    }

    public void activatePoint(int row, int col) {
//        this.points[this.lastActivatedPoint].deactivate();
//        this.lastActivatedPoint = row*this.nCols + col;
//        this.points[this.lastActivatedPoint].activate(); // todo overflow checking
        this.points.add(new Point(row, col));
    }

    public int getCellWidth() {
        return this.cellWidth;
    }

    public int getCellHeight() {
        return this.cellHeight;
    }

    public void tryAddObject(Sprite sprite, int row, int col ) {
        GameObject pipe;
        Point point = new Point(row, col);
        switch (sprite) {
            case short_pipe:
                pipe = new GameObject(BitmapFactory.decodeResource(
                        this.context.getResources(),
                        R.drawable.short_pipe),
                        1, 2,
                        this.cellWidth, this.cellHeight);
                this.objects.put(pipe, point);
                break;
            case rotated_short_pipe:
                pipe = new GameObject(BitmapFactory.decodeResource(
                        this.context.getResources(),
                        R.drawable.short_pipe), 1, 2,
                        this.cellWidth, this.cellHeight);
                pipe.rotate();
                this.objects.put(pipe, point);
                break;
            default: break;
        }
    }
}
