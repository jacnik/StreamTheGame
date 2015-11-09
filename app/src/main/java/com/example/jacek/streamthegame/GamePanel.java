package com.example.jacek.streamthegame;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by jacek on 11/9/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;

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

        return false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // we can safely start the game loop
        this.thread.setRunning(true);
        this.thread.run();
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

    public void update() {

    }
}
