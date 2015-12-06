package com.example.jacek.streamthegame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by jacek on 11/12/2015.
 */
public class Grid {

    private int nRows, nCols;
    private int cellWidth, cellHeight;
    //private int lastActivatedPoint;

    // currentLayout maps all cells with object that are placed on them
    // from item number to coordinates:
    //      int col = i / this.nCols;
    //      int row = i % this.nCols;
    // from coordinates to element in the array:
    //      int i = row*this.nCols + col;
    private GameObject[] currentLayout; // todo maybe new class.

    private Context context;
    private HashMap<GameObject, Point> objects = new HashMap<>();
    private ArrayList<Point> activeCells = new ArrayList<>();

    public Grid(Context context, int rows, int cols, int cellWidth, int cellHeight) {
        this.context = context;
        this.nRows = rows;
        this.nCols = cols;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;

        this.currentLayout = new GameObject[rows*cols];
    }

    public void draw(Canvas canvas) {

        HashSet<GameObject> drawn = new HashSet<>();
        for (int i = 0; i < this.nRows * this.nCols; ++i) {
            GameObject item = currentLayout[i];
            if (item != null && !drawn.contains(item)) {
                int row = i / this.nCols;
                int col = i % this.nCols;
                // draw Object
                canvas.drawBitmap(
                    item.getImage(),
                    this.cellWidth * col,
                    this.cellHeight * row,
                    null);
                drawn.add(item);
            }
        }
    }

    public void activatePoint(int row, int col) {
//        this.activeCells[this.lastActivatedPoint].deactivate();
//        this.lastActivatedPoint = row*this.nCols + col;
//        this.activeCells[this.lastActivatedPoint].activate(); // todo overflow checking
        this.activeCells.add(new Point(row, col));
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
                this.addToLayout(pipe, row, col);
                break;
            case rotated_short_pipe:
                pipe = new GameObject(BitmapFactory.decodeResource(
                        this.context.getResources(),
                        R.drawable.short_pipe),
                        1, 2,
                        this.cellWidth, this.cellHeight);
                pipe.rotate();
                //this.objects.put(pipe, point);
                break;
            default: break;
        }
    }

    public GameObject getObjectFromCoords(int row, int col) {
        return this.currentLayout[row*this.nCols + col];
    }

    public void removeObject(GameObject obj) {
        for (int i = 0; i < this.nRows * this.nCols; ++i) {
            if(this.currentLayout[i] == obj) {
                this.currentLayout[i] = null;
            }
        }
    }

    /** Adds GameObject to layout in every row and col that this GameObject will occupy.
     * @param object: object to add to layout.
     * @param row: x coordinate of the upper left corner of the object.
     * @param col: y coordinate of the upper left corner of the object.
     * */
    public void addToLayout(GameObject object, int row, int col) {
        int width = object.getWidthCells();
        int height = object.getHeightCells();
        // width and height are properties of an object
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int pos = (row + i)*this.nCols + (col+j);
                this.currentLayout[pos] = object;
            }
        }
    }

    private void clearLayout() {
        for (GameObject obj : this.currentLayout) {
            obj = null;
        }
    }
}
