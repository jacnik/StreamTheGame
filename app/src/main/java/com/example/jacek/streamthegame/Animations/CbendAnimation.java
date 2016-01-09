package com.example.jacek.streamthegame.Animations;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.R;

/**
 * Created by jacek on 1/9/2016.
 */
public class CbendAnimation extends Animation {
    public CbendAnimation(Context context) {
        super(context, 2, 1);
        this.startCorner = new Exit(1, Direction.LEFT);
    }

    @Override
    public void setup(Exit exit) {
        Matrix transform  = new Matrix();
        switch (exit.getCorner()) {
            case 0:
                if (exit.getDir() == Direction.UP) {
                    transform.postRotate(90);
                    transform.postScale(-1, 1);
                } else if (exit.getDir() == Direction.DOWN) {
                    transform.postRotate(90 * 3);
                }
                break;
            case 1:
                if (exit.getDir() == Direction.RIGHT) {
                    transform.postScale(-1, 1);
                } else if (exit.getDir() == Direction.UP) {
                    transform.postRotate(90);
                }
                break;
            case 2:
                if (exit.getDir() == Direction.UP) {
                    transform.postRotate(90);
                } else if (exit.getDir() == Direction.RIGHT) {
                    transform.postRotate(90 * 2);
                } else if (exit.getDir() == Direction.DOWN) {
                    transform.postRotate(90 * 3);
                    transform.postScale(-1, 1);
                }
                break;
            case 3:
                if (exit.getDir() == Direction.RIGHT) {
                    transform.postRotate(90 * 2);
                } else if (exit.getDir() == Direction.LEFT) {
                    transform.postScale(1, -1);
                } else if (exit.getDir() == Direction.DOWN) {
                    transform.postRotate(90 * 3);
                }
                break;
        }

        this.createFramesFromImage(BitmapFactory.decodeResource(
                this.context.getResources(),
                R.drawable.c_bend_spritesheet), transform);
    }
}
