package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jacek.streamthegame.Animations.Animation;
import com.example.jacek.streamthegame.Animations.Bend2Animation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.R;
import com.example.jacek.streamthegame.Sprite;

/**
 * Created by jacek on 1/2/2016.
 */
public class Bend2 extends GameObject {
    public Bend2(Context context, int cellWidth, int cellHeight) {
        super(cellWidth, cellHeight);
        this.isStatic = false;
        this.widthCells = 2;
        this.heightCells = 2;

        this.image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.bend2),
                widthCells * cellWidth,
                heightCells * cellHeight,
                false);
        this.animation = new Bend2Animation(context);
        this.exit1 = new Exit(1, Direction.UP);
        this.exit2 = new Exit(3, Direction.LEFT);
    }

    @Override
    public Sprite getType() {
        return Sprite.bend2;
    }
}
