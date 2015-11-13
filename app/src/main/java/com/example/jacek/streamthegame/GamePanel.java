package com.example.jacek.streamthegame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by jacek on 11/9/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;
    private Background background;
    private Grid grid;
    private int nRows = 10; // todo do it better with levels
    private int nCols = 7; // todo do it better

    private int cellWidth, cellHeight; // todo maybe put this info into grid class

    public GamePanel(Context context) {
        super(context);
        // add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        // make game panel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            if (event.getY() > getHeight() - 50) {
                //this.thread.setRunning(false);
                //((Activity)getContext()).finish();
//            } else {
//                Log.d("TAG", "Coords: x=" + event.getX() + ",y=" + event.getY());
//            }
            int row = (int) event.getX() / this.cellWidth;
            int col = (int) event.getY() / this.cellHeight;
            this.grid.activatePoint(row, col);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        this.background = new Background(BitmapFactory.decodeResource(
                getResources(),
                R.drawable.logo_ps));

        this.grid = new Grid(this.nRows, this.nCols,
                getWidth() / this.nRows, getHeight() / this.nCols); // todo: implement levels

        this.cellWidth = getWidth() / this.nRows; // todo this is repetitive with grid
        this.cellHeight = getHeight() / this.nCols;

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
        this.background.draw(canvas);
        this.grid.draw(canvas);
    }

    public void update() {
        //this.background.update(); // todo remove since background will be static?
    }
}
