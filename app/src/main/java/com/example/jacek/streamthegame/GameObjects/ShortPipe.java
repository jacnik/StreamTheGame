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

//    @Override
//    public boolean hasExitAt(int row, int col, Direction dir) {
//        Point corners = this.getCornersFromCoords(row, col);
//        int corner = this.exit1.getCorner();
//        if (this.exit1.getDir() == dir) {
//            if (corner == corners.x || corner == corners.y) return true;
//        }
//        corner = this.exit2.getCorner();
//        if (this.exit2.getDir() == dir) {
//           if (corner == corners.x || corner == corners.y) return true;
//        }
//        return false;
//    }

//    @Override
//    public Exit getExitAt(int row, int col, Direction dir) {
//        Point corners = this.getCornersFromCoords(row, col);
//        int corner = this.exit1.getCorner();
//        if (this.exit1.getDir() == dir) {
//            if (corner == corners.x || corner == corners.y) return this.exit1;
//        }
//        corner = this.exit2.getCorner();
//        if (this.exit2.getDir() == dir) {
//            if (corner == corners.x || corner == corners.y) return this.exit2;
//        }
//        return null;
//    }
//
//    /*
//    * values of the corners in which the exit appears
//    * when vertical: corners (0 and 1) are the same and (2 and 3) are the same
//    * when horizontal: corners (1 and 2) are the same and (0 and 3) are the same
//    * */
//    private Point getCornersFromCoords(int row, int col) {
//        /* when vertical */
//        if (this.widthCells == 0) {
//            if (row == 0) return new Point(1, 2);
//            else return new Point(0, 3);
//        } else {
//            /* horizontal */
//            if (col == 0) return new Point(0, 1);
//            else return new Point(2, 3);
//        }
//    }
}
