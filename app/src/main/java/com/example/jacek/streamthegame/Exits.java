package com.example.jacek.streamthegame;

import java.security.InvalidParameterException;

/**
 * Created by jacek on 12/10/2015.
 * Class represents exits of each object.
 * most of them will have two,
 * special cases like entrance and exit object will have only one.
 * For now this is a naive implementation (assumes that exits can only appear in corners of an object)
 */
public class Exits {
    /*
    * value of the corner in which the exit appears
    * 0 -> upper left   coordinates (row, column): (0,0)
    * 1 -> upper right   (0, lastCol)
    * 2 -> lower right   (lastRow, lastCol)
    * 3 -> lower left    (lastRow, 0)
    * */

    private final int N_CORNERS = 4; // number of corners

    private int corner1, corner2;
    private Direction dir1, dir2;
    private int objLastRow, objLastCol;

    public Exits(int row1, int col1,
                 int row2, int col2,
                 Direction d1, Direction d2,
                 int objCols, int objRows) {

        this.objLastRow = objRows - 1;
        this.objLastCol = objCols - 1;

        this.corner1 = this.getStateFromCoordinates(row1, col1);
        this.corner2 = this.getStateFromCoordinates(row2, col2);

        this.dir1 = d1;
        this.dir2 = d2;
    }

    public void rotate() {
        /* object dimension have changed, mirror it here
        */
        int tmp = this.objLastRow;
        this.objLastRow = this.objLastCol;
        this.objLastCol = tmp;

        // update directions
        dir1 = dir1.next();
        dir2 = dir2.next();

        // update states
        this.corner1 = (this.corner1 + 1) % N_CORNERS;
        this.corner2 = (this.corner2 + 1) % N_CORNERS;
    }

    // assumes that this.objLastRow and this.objLastCol are set
    private int getStateFromCoordinates(int row, int col) {
        if(row == 0 && col == 0) {
            return 0;
        } else if (row == 0 && col == this.objLastCol) {
            return 1;
        } else if (row == this.objLastRow && col == this.objLastCol) {
            return 2;
        } else if (row == this.objLastRow && col == 0) {
            return 3;
        }

        throw new InvalidParameterException("Invalid row or column parameter");
    }

}
