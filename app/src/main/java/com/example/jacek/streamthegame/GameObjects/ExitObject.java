package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jacek.streamthegame.Animations.ExitAnimation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.R;
import com.example.jacek.streamthegame.Sprite;

/**
 * Created by jacek on 1/2/2016.
 */
public class ExitObject extends GameObject {

    public ExitObject(Context context, int cellWidth, int cellHeight) {
        super(cellWidth, cellHeight);
        this.isStatic = true;
        this.widthCells = 1;
        this.heightCells = 1;

        this.image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.exit_valve_arrow),
                widthCells * cellWidth,
                heightCells * cellHeight,
                false);

        this.animation = new ExitAnimation(context);

        this.exit1 = new Exit(0,Direction.RIGHT);
        this.exit2 = new Exit(0,Direction.RIGHT);
    }

    @Override
    public Sprite getType() {
        return Sprite.exit;
    }

    /* Overridden because Exit parameter here will be null */
    @Override
    public void startAnimation(Exit exit) {
        if (!this.finishedAnimating()) {
            this.animation.setup(this.exit1);
            this.animationStartExit = this.exit1;
            this.isAnimating = true;
        }
    }
}
