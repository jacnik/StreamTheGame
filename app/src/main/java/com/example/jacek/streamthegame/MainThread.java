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

            this.gamePanel.update();

            this.canvas = this.surfaceHolder.lockCanvas();
            // todo try/catch?
            synchronized (this.surfaceHolder) {
                this.gamePanel.draw(canvas);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);

            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;
            try {
                this.sleep(waitTime);
            } catch (InterruptedException e) {
                // todo: handle/log
            }
        }


//            this.canvas = this.surfaceHolder.lockCanvas();
//            this.gamePanel.update();
//            this.gamePanel.draw(canvas);
//            surfaceHolder.unlockCanvasAndPost(canvas);


//        while(this.running) {
//            startTime = System.nanoTime();
//            this.canvas = null;

            //try locking the canvas for pixel editing
//            try {
//                this.canvas = this.surfaceHolder.lockCanvas();
//                synchronized (this.surfaceHolder) {
//                    this.gamePanel.update();
//                    this.gamePanel.draw(canvas);
//                }
//            } catch (Exception e) {
//                // todo handle main loop exceptions
//            }
//            timeMillis = (System.nanoTime() - startTime)/1000000;
//            waitTime = targetTime - timeMillis;
//
//            try {
//                this.sleep(waitTime);
//            } catch (Exception e) {
//                // todo handle sleep exceptions
//            } finally {
//                if (canvas != null) {
//                    surfaceHolder.unlockCanvasAndPost(canvas);
//                }
//            }
//
//            totalTime += System.nanoTime() - startTime;
//            frameCount++;
//
//            if(frameCount == FPS) {
//                averageFPS = 1000/((totalTime/frameCount)/1000000);
//                frameCount = 0;
//                totalTime = 0;
//                //Log.d("MainThread", "averageFPS: " + averageFPS);
//            }
//        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
