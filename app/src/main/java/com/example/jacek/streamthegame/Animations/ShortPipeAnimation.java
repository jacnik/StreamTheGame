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
public class ShortPipeAnimation extends Animation {
    public ShortPipeAnimation(Context context) {
        super(context, 2, 1);
        this.startCorner = new Exit(1, Direction.UP);
    }

    @Override
    public void setup(Exit exit) {
        int rotations = this.startCorner.getDir().getDiffFrom(exit.getDir());
        Matrix transform  = new Matrix();
        transform.postRotate(90 * rotations);

        this.createFramesFromImage(BitmapFactory.decodeResource(
                this.context.getResources(),
                R.drawable.short_pipe_spritesheet), transform);
    }
}
