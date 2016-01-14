package com.example.jacek.streamthegame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by jacek on 11/9/2015.
 */
public class MainThread extends Thread {

    private final int FPS = 30;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        super.run();

        long startTime;
        long timeMillis;
        long waitTime;
        long targetTime = 1000/FPS;

        while(this.running) {
            startTime = System.nanoTime();

            //try locking the canvas for pixel editing
            try {
                this.canvas = this.surfaceHolder.lockCanvas();
                synchronized (this.surfaceHolder) {
                   this.gamePanel.update();
                   this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                this.setRunning(false);
            // todo handle main loop exceptions
            }
            if (this.canvas != null) {
                this.surfaceHolder.unlockCanvasAndPost(this.canvas);
            }

            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;
            try {
                this.sleep(waitTime > 0 ? waitTime : 0);
            } catch (InterruptedException e) {
                // todo: handle/log
            }
        }
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }
}
