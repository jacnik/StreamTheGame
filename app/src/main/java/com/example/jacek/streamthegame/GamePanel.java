package com.example.jacek.streamthegame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.jacek.streamthegame.GameObjects.GameObject;

/**
 * Created by jacek on 11/9/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private final long TAP_LENGTH = 300; // milliseconds
    private MainThread thread;
    private Grid grid;

    private int nRows = 7; // todo do it better with levels
    private int nCols = 10; // todo do it better  with levels

    private int lastRow = 0; // used for moving objects
    private int lastCol = 0; // used for moving objects
    private GameObject lastObj; // used for moving objects
    private long lastTap; // used for short tap events

    private boolean isAnimating;

    public GamePanel(Context context) {
        super(context);
        // add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        // make game panel focusable so it can handle events
        setFocusable(true);
        setClickable(true); // allows to handle ACTION_MOVE event
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int row = (int) event.getY() / this.grid.getCellHeight();
            int col = (int) event.getX() / this.grid.getCellWidth();

            this.lastObj = this.grid.getObjectFromCoords(row, col);
            if (this.lastObj != null) {
                this.lastRow = row;
                this.lastCol = col;
                this.lastTap = System.nanoTime()/1000000;
            }

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

            if (this.lastObj != null && !this.lastObj.isStatic()) {
                int newRow = (int) event.getY() / this.grid.getCellHeight();
                int newCol = (int) event.getX() / this.grid.getCellWidth();
                if (newRow != this.lastRow || newCol != this.lastCol) {

                    /* if moving object was successful*/
                    if (this.grid.moveObjAt(
                            this.lastRow, this.lastCol,
                            newRow - this.lastRow, newCol - this.lastCol)) {
                        this.lastRow = newRow;
                        this.lastCol = newCol;
                    }
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            /* if tap event occurred */
            if (System.nanoTime()/1000000 - this.lastTap < TAP_LENGTH) {
                if (this.lastObj != null && !this.lastObj.isStatic()) {
                    // rotate
                    this.grid.rotateObjAt(this.lastRow, this.lastCol);
                }
                // else if last obj is exit then start animation
                //this.isAnimating = true; // todo:remove
                //this.lastObj.startAnimation(); // todo:remove
            }

            this.lastObj = null;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        this.grid = new Grid(this.getContext(), this.nRows, this.nCols,
                getWidth()/this.nCols, getHeight()/this.nRows); // todo: implement levels

        this.grid.tryAddObject(Sprite.exit, 1, 2);
        this.grid.tryAddObject(Sprite.enter, 5, 6);
        this.grid.tryAddObject(Sprite.short_pipe, 2, 1);
        this.grid.tryAddObject(Sprite.short_pipe, 3, 4);
        this.grid.tryAddObject(Sprite.bend2, 5, 7);

        //this.grid.tryAddObject(Sprite.rotated_short_pipe, 0, 1);
        //this.grid.tryAddObject(Sprite.rotated_short_pipe, 2, 3);

        // we can safely start the game loop
        this.thread.setRunning(true);
        this.thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
           try{
               this.thread.setRunning(false);
               thread.join();

           } catch (InterruptedException e) {
               e.printStackTrace(); // todo handle it nicely
           }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.grid.draw(canvas);
    }

    public void update() {
        if (this.isAnimating) {
            this.grid.update();
        }
    }
}
