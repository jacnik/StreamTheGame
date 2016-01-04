package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jacek.streamthegame.Animation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.Exits;
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
        this.animation = new Animation(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.exit_valve_spritesheet),
                this.heightCells, this.widthCells, Direction.LEFT);
        this.exits = new Exits(
                0, 0,
                0, 0,
                Direction.RIGHT, Direction.RIGHT,
                this.widthCells, this.heightCells);
        this.exit1 = new Exit(1,Direction.RIGHT);
        this.exit2 = new Exit(1,Direction.RIGHT);
    }

    @Override
    public Sprite getType() {
        return Sprite.exit;
    }

// todo probably not needed
//    /* Overridden because Exit here will be null */
//    @Override
//    public void startAnimation(Exit exit) {
//        this.isAnimating = true;
//    }
}