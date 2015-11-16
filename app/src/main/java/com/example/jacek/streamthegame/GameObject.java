package com.example.jacek.streamthegame;

import android.graphics.Bitmap;
import android.graphics.Matrix;


/**
 * Created by jacek on 11/15/2015.
 */
public class GameObject {
    private Bitmap image;
    private int widthCells, heightCells; // width and height in cell units!

    public GameObject(Bitmap image, int widthCells, int heightCells) {
        this.image = image;
        this.widthCells = widthCells;
        this.heightCells = heightCells;
    }

//    public void draw(Canvas canvas, int x, int y) {
//        //canvas.drawBitmap(this.image, x, y, null);
//    }

//    public void update() {}

    public Bitmap getImage() {
        return this.image;
    }

    public void resize(int dx, int dy) {
        this.image = Bitmap.createScaledBitmap(this.image, dx, dy, false);
    }

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
    }
}
