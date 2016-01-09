package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jacek.streamthegame.Animations.LbendAnimation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.R;
import com.example.jacek.streamthegame.Sprite;

/**
 * Created by jacek on 1/9/2016.
 */
public class Lbend extends GameObject {
    public Lbend(Context context, int cellWidth, int cellHeight) {
        super(cellWidth, cellHeight);
        this.widthCells = 1;
        this.heightCells = 2;

        this.image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.l_bend),
                widthCells * cellWidth,
                heightCells * cellHeight,
                true);
        this.animation = new LbendAnimation(context);

        this.exit1 = new Exit(1, Direction.UP);
        this.exit2 = new Exit(3,Direction.LEFT);
    }

    @Override
    public Sprite getType() {
        return Sprite.l_bend;
    }
}
