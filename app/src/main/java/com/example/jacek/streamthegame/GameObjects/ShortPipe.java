package com.example.jacek.streamthegame.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import com.example.jacek.streamthegame.Animation;
import com.example.jacek.streamthegame.Direction;
import com.example.jacek.streamthegame.Exit;
import com.example.jacek.streamthegame.R;
import com.example.jacek.streamthegame.Sprite;

/**
 * Created by jacek on 1/2/2016.
 */
public class ShortPipe extends GameObject {
    public ShortPipe(Context context, int cellWidth, int cellHeight) {
        super(cellWidth, cellHeight);
        this.isStatic = false;
        this.widthCells = 1;
        this.heightCells = 2;

        this.image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.short_pipe),
                widthCells * cellWidth,
                heightCells * cellHeight,
                false);
        this.animation = new Animation(
                BitmapFactory.decodeResource(
                        context.getResources(),
                        R.drawable.short_pipe_spritesheet),
                this.heightCells, this.widthCells, Direction.UP);
        this.exit1 = new Exit(1, Direction.UP);
        this.exit2 = new Exit(3, Direction.DOWN);
    }

    @Override
    public Sprite getType() {
        return Sprite.short_pipe;
    }
}
