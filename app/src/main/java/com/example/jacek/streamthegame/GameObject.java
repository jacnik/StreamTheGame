package com.example.jacek.streamthegame;

import android.graphics.Bitmap;
import android.graphics.Matrix;


/**
 * Created by jacek on 11/15/2015.
 */
public class GameObject {
    private Bitmap image;
    private int widthCells, heightCells; // width and height in cell units!
    private int cellWidth, cellHeight;

    public GameObject(Bitmap image,
                      int widthCells, int heightCells,
                      int cellWidth, int cellHeight) {
        this.image = Bitmap.createScaledBitmap(
                image,
                widthCells * cellWidth,
                heightCells * cellHeight,
                false);
        this.widthCells = widthCells;
        this.heightCells = heightCells;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

//    public void draw(Canvas canvas, int x, int y) {
//        //canvas.drawBitmap(this.image, x, y, null);
//    }

//    public void update() {}

    public Bitmap getImage() {
        return this.image;
    }

    public int getWidthCells() {
        return this.widthCells;
    }

    public int getHeightCells() {
        return  this.heightCells;
    }

//    public void resize(int width, int height) {
//        this.image = Bitmap.createScaledBitmap(
//                this.image,
//                this.widthCells * width,
//                this.heightCells * height,
//                false);
//    }

    /* No additional parameters because only rotation 90 deg right is available */
    public void rotate() {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        this.image = Bitmap.createBitmap(
                this.image, 0, 0, this.image.getWidth(),
                this.image.getHeight(), matrix, true);

        // swap the dimensions
        int tmp = this.widthCells;
        this.widthCells = this.heightCells;
        this.heightCells = tmp;

        // resize the image so it fits new dimensions
        this.image = Bitmap.createScaledBitmap(
                this.image,
                this.widthCells * this.cellWidth,
                this.heightCells * this.cellHeight,
                false);
    }
}
