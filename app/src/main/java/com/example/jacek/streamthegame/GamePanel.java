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
public class GamePanel extends SurfaceView
        implements SurfaceHolder.Callback, AnimationFinishedListener {

    private static final int HOME = 0;
    private static final int GAME = 1;
    private static final int CONGRATS = 2;

    private final long TAP_LENGTH = 300; // milliseconds
    private LevelProvider levelProvider = new LevelProvider();

    private MainThread thread;
    private Grid grid;

    private int lastRow = 0; // used for moving objects
    private int lastCol = 0; // used for moving objects
    private GameObject lastObj; // used for moving objects
    private long lastTap; // used for short tap events

    private boolean isAnimating;

    //private boolean isHome = true; // todo implement screen after beating the level
    private int currentScreen;
    private HomeScreen homeScreen;
    private CongratsScreen congratsScreen;

    public GamePanel(Context context) {
        super(context);
        // add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        this.thread = new MainThread(getHolder(), this);

        // make game panel focusable so it can handle events
        setFocusable(true);
        setClickable(true); // allows to handle ACTION_MOVE event
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /** If animation is in progress don't handle any events */
        if (this.isAnimating) return super.onTouchEvent(event);
        if (this.currentScreen == HOME) return this.handleHomeEvents(event);
        if (this.currentScreen == CONGRATS) return this.handleCongratEvents(event);

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
                } else if (this.lastObj != null && this.lastObj.getType() == Sprite.exit){
                    /* else if last obj is exit then start animation */
                    this.lastObj.startAnimation(null);
                    this.isAnimating = true;
                }
            }

            this.lastObj = null;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.grid = new Grid(this.getContext(), this.getHeight(), this.getWidth());
        this.grid.registerAnimationFinishedHandler(this);

        this.homeScreen = new HomeScreen(this.getContext(), this.levelProvider.getLevelCount());
        this.congratsScreen = new CongratsScreen();

        this.currentScreen = HOME;
        this.isAnimating = false;

        // we can safely start the game loop
        if (this.thread.getState() == Thread.State.TERMINATED) {
            this.thread = new MainThread(getHolder(), this);
        }
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
        if (this.currentScreen == HOME) {
            this.homeScreen.draw(canvas);
        } else if (this.currentScreen == CONGRATS) {
            this.grid.draw(canvas);
            this.congratsScreen.draw(canvas);
        } else {
            this.grid.draw(canvas);
        }
    }

    @Override
    public void animationFinished(AnimationFinishedEvent event) {
        this.isAnimating = false;
        if (event.isSuccess()) {
            this.grid.setBlur();
            this.currentScreen = CONGRATS;
        }
    }

    public synchronized void update() {
        if (this.isAnimating) {
            this.grid.update();
        }
    }

    public boolean backPressed() {
        if (this.currentScreen == HOME) {
            return true;
        } else {
            this.isAnimating = false;
            this.currentScreen = HOME;
        }
        return false;
    }

    private boolean handleHomeEvents(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.lastTap = System.nanoTime()/1000000;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            /* if tap event occurred */
            if (System.nanoTime()/1000000 - this.lastTap < TAP_LENGTH) {
                int clickedLevel = this.homeScreen.clicked(
                        Math.round(event.getX()), Math.round(event.getY()));
                if (clickedLevel > -1) {
                    this.grid.setLevel(
                            this.levelProvider.getLevel(clickedLevel));
                    this.currentScreen = GAME;
                }
            }
        }
        return true;
    }

    private boolean handleCongratEvents(MotionEvent event) {
        this.grid.resetBlur();
        this.currentScreen = HOME;
        return true;
    }
}
