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

    private Exits exits;
    private boolean isStatic;

    private boolean isAnimating;
    private Animation animation;

    public GameObject(Bitmap image,
                      int widthCells, int heightCells,
                      int cellWidth, int cellHeight,
                      Exits exits,
                      boolean isStatic) {
        this.image = Bitmap.createScaledBitmap(
                image,
                widthCells * cellWidth,
                heightCells * cellHeight,
                false);
        this.widthCells = widthCells;
        this.heightCells = heightCells;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.isStatic = isStatic;
        this.exits = exits;
    }

    public GameObject(Bitmap image,
                      int widthCells, int heightCells,
                      int cellWidth, int cellHeight, Exits exits) {

        /* isStatic = false is the default */
        this(image, widthCells, heightCells, cellWidth, cellHeight, exits, false);
    }

//    public void draw(Canvas canvas, int x, int y) {
//        //canvas.drawBitmap(this.image, x, y, null);
//    }

    // todo: this is just a fast mockup for tomorrow's presentation.
    // todo: note to self: Think this through when you will have more time next week
    public void setExit() {

    }

    public void update() { this.animation.update();}

    public void setAnimation(Bitmap spriteGrid) {
        this.animation = new Animation(spriteGrid, this.heightCells, this.widthCells);
    }

    public Bitmap getImage() {
        if (this.isAnimating) {
            // images from animation are not scaled
            return Bitmap.createScaledBitmap(
                    this.animation.getImage(),
                    this.widthCells * this.cellWidth,
                    this.heightCells * this.cellHeight,
                    false);
        } else {
            return this.image;
        }
    }

    public int getWidthCells() {
        return this.widthCells;
    }

    public int getHeightCells() {
        return  this.heightCells;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    public void startAnimation() {
        this.isAnimating = true;
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

        // rotate exits
        this.exits.rotate();

        // resize the image so it fits new dimensions
        this.image = Bitmap.createScaledBitmap(
                this.image,
                this.widthCells * this.cellWidth,
                this.heightCells * this.cellHeight,
                false);
    }
}
