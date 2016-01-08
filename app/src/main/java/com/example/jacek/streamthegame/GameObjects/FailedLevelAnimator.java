package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jacek.streamthegame.Animation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.R;
import com.example.jacek.streamthegame.Sprite;

/**
 * Created by jacek on 1/8/2016.
 */
public class FailedLevelAnimator extends GameObject {
    public FailedLevelAnimator(Context context, int cellWidth, int cellHeight) {
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
                false); // todo make null
        this.animation = new Animation(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.failed_level_spritesheet),
                this.heightCells, this.widthCells, Direction.RIGHT);
        this.exit1 = new Exit(2,Direction.RIGHT);
        this.exit2 = new Exit(2,Direction.RIGHT);
    }

    @Override
    public Sprite getType() {
        return Sprite.failed_level;
    }
}
