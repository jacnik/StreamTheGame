package com.example.jacek.streamthegame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by jacek on 11/9/2015.
 */
public class MainThread extends Thread {
    private final int FPS = 30;
    private double averageFPS;
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
        long startTime;
        long timeMillis;
        long waitTime = 0;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS;

        while(this.running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (this.surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                // todo handle main loop exceptions
            }
            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {
                // todo handle sleep exceptions
            } finally {
                if (canvas != null) {
                    // todo try/catch?
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if(frameCount == FPS) {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                Log.d("MainThread", "averageFPS: " + averageFPS);
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
