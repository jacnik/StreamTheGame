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
public class EnterAnimation extends Animation {

    public EnterAnimation(Context context) {
        super(context, 1, 1);
        this.startCorner = new Exit(0, Direction.LEFT);
    }

    @Override
    public void setup(Exit exit) {
        int rotations = this.startCorner.getDir().getDiffFrom(exit.getDir());
        Matrix m = new Matrix();
        m.postRotate(90 * rotations);

        this.createFramesFromImage(BitmapFactory.decodeResource(
                this.context.getResources(),
                R.drawable.enter_valve_spritesheet), m);
    }
}
