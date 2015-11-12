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

//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            if (event.getY() > getHeight() - 50) {
//                thread.setRunning(false);
//                ((Activity)getContext()).finish();
//            } else {
//                Log.d("TAG", "Coords: x=" + event.getX() + ",y=" + event.getY());
//            }
//        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        this.background = new Background(BitmapFactory.decodeResource(
                getResources(),
                R.drawable.logo_ps));

        this.grid = new Grid(10, 5); // todo: implement levels

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
