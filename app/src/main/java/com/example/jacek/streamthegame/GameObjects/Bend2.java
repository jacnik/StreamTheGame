package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exits;
import com.example.jacek.streamthegame.R;

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
//        this.animation = new Animation( // todo create spritesheet and instantiate this animation
//                BitmapFactory.decodeResource(
//                        context.getResources(),
//                        R.drawable.short_pipe_spritesheet),
//                this.heightCells, this.widthCells);
        this.exits = new Exits(
                0, 1,
                1, 0,
                Direction.UP, Direction.LEFT,
                this.widthCells, this.heightCells);
    }
}
