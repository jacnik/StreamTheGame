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

// todo implement this functionality in the future
public class FailedLevelAnimator extends GameObject {
    public FailedLevelAnimator(Context context, int cellWidth, int cellHeight) {
        super(cellWidth, cellHeight);
        this.isStatic = true;
        this.widthCells = 1;
        this.heightCells = 1;

        this.image = null; // todo make null
        this.animation = null;
        this.exit1 = new Exit(2,Direction.RIGHT);
        this.exit2 = new Exit(2,Direction.RIGHT);
    }

    @Override
    public Sprite getType() {
        return Sprite.failed_level;
    }
}
