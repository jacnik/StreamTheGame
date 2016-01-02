package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jacek.streamthegame.Animation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exits;
import com.example.jacek.streamthegame.R;

/**
 * Created by jacek on 1/2/2016.
 */
public class Exit extends GameObject {

    public Exit(Context context, int cellWidth, int cellHeight) {
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
        this.animation = new Animation(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.exit_valve_spritesheet),
                this.heightCells, this.widthCells);
        this.exits = new Exits(
                0, 0,
                0, 0,
                Direction.RIGHT, Direction.RIGHT,
                this.widthCells, this.heightCells);
    }
}
