package com.example.jacek.streamthegame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by jacek on 11/9/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Grid grid;

    private int nRows = 7; // todo do it better with levels
    private int nCols = 10; // todo do it better  with levels

    private int lastRow = 0; // used for moving objects
    private int lastCol = 0; // used for moving objects
    private GameObject lastObj; // used for moving objects

    final GestureDetector mGesDetect = new GestureDetector(getContext(), new GameGestureDetector());

    public GamePanel(Context context) {
        super(context);
        // add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);



        // make game panel focusable so it can handle events
        setFocusable(true);
        //setClickable(true); // allows to handle ACTION_MOVE event
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        //mGesDetect.
        //mGesDetect.onGenericMotionEvent(event);
        //mGesDetect.
        return true;
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        //mGesDetect.onGenericMotionEvent(event);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mGesDetect.onTouchEvent(event);
        return true;

//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//            int row = (int) event.getY() / this.grid.getCellHeight();
//            int col = (int) event.getX() / this.grid.getCellWidth();
//            //this.grid.activatePoint(row, col);
//            this.lastObj = this.grid.getObjectFromCoords(row, col);
//            if (this.lastObj != null) {
//                this.lastRow = row;
//                this.lastCol = col;
//            }
//
//        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//
//            if (lastObj != null) {
//                int newRow = (int) event.getY() / this.grid.getCellHeight();
//                int newCol = (int) event.getX() / this.grid.getCellWidth(); // event.getHistoricalX(); maybe able to replace this.lastRow
//                if (newRow != this.lastRow || newCol != this.lastCol) {
//                    this.grid.removeObject(this.lastObj);
//                    this.grid.addToLayout(this.lastObj, newRow, newCol);
//                }
//            }
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            this.lastObj = null;
//        }
//        return super.onTouchEvent(event);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        this.grid = new Grid(this.getContext(), this.nRows, this.nCols,
                getWidth()/this.nCols, getHeight()/this.nRows); // todo: implement levels

        //this.grid.tryAddObject(Sprite.short_pipe, 0, 0);
        this.grid.tryAddObject(Sprite.short_pipe, 2, 1);
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

    }

    class GameGestureDetector extends GestureDetector.SimpleOnGestureListener {

//        @Override
//        public boolean onDown(MotionEvent event) {
//            //Log.d(DEBUG_TAG, "onDown: " + event.toString());
//            return true;
//        }

//        @Override
//                 public boolean onGenericMotionEvent(MotionEvent ev) {
//            return true;
//        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
           // mGestureText.setText("onSingleTapConfirmed");
            // rotate
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //mGestureText.setText("onScroll");
            int row = (int) e1.getY() / GamePanel.this.grid.getCellHeight();
            int col = (int) e1.getX() / GamePanel.this.grid.getCellWidth();
            GameObject obj = GamePanel.this.grid.getObjectFromCoords(row, col);
            if (obj != null) {

            }
//            if (lastObj != null) {
//                int newRow = (int) event.getY() / this.grid.getCellHeight();
//                int newCol = (int) event.getX() / this.grid.getCellWidth(); // event.getHistoricalX(); maybe able to replace this.lastRow
//                if (newRow != this.lastRow || newCol != this.lastCol) {
//                    this.grid.removeObject(this.lastObj);
//                    this.grid.addToLayout(this.lastObj, newRow, newCol);
//                }
//            }

            return true;
        }
//        @Override
//        public boolean onDoubleTap(MotionEvent e) {
//            Log.d("TAG", "Double Tap Detected ...");
//            return true;
//        }
    }

}
