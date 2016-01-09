package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jacek.streamthegame.Animations.Animation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.R;
import com.example.jacek.streamthegame.Sprite;

/**
 * Created by jacek on 1/8/2016.
 */
public class Cbend extends GameObject {
    public Cbend(Context context, int cellWidth, int cellHeight) {
        super(cellWidth, cellHeight);
        this.widthCells = 1;
        this.heightCells = 2;

        this.image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.c_bend),
                widthCells * cellWidth,
                heightCells * cellHeight,
                false);
        this.animation = null;

        this.exit1 = new Exit(0,Direction.LEFT);
        this.exit2 = new Exit(3,Direction.LEFT);
    }

    @Override
    public Sprite getType() {
        return Sprite.c_bend;
    }
}
