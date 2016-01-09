package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jacek.streamthegame.Animations.CbendAnimation;
import com.example.jacek.streamthegame.Animations.SbendAnimation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.R;
import com.example.jacek.streamthegame.Sprite;

/**
 * Created by jacek on 1/9/2016.
 */
public class Sbend extends GameObject {
    public Sbend(Context context, int cellWidth, int cellHeight) {
        super(cellWidth, cellHeight);
        this.widthCells = 2;
        this.heightCells = 2;

        this.image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.s_bend),
                widthCells * cellWidth,
                heightCells * cellHeight,
                true);
        this.animation = new SbendAnimation(context);

        this.exit1 = new Exit(1, Direction.RIGHT);
        this.exit2 = new Exit(3,Direction.LEFT);
    }

    @Override
    public Sprite getType() {
        return Sprite.s_bend;
    }
}
