package com.example.jacek.streamthegame;

/**
 * Created by jacek on 1/4/2016.
 */
public class Exit {

    private static final int N_CORNERS = 4;
    /*
    * value of the corner in which the exit appears
    * 0 -> upper left   coordinates (row, column): (0,0)
    * 1 -> upper right   (0, lastCol)
    * 2 -> lower right   (lastRow, lastCol)
    * 3 -> lower left    (lastRow, 0)
    * */
    private int corner;
    private int row;
    private int col;
    private int lastRow;
    private int lastCol;
    private Direction dir;

    public Exit(int corner, Direction dir) {
        this.corner = corner;
        this.dir = dir;
    }

    public int getCorner() { return this.corner; }
    public Direction getDir() {return this.dir;}

    public void rotate() {
        this.dir = this.dir.next();
        this.corner = (this.corner + 1) % N_CORNERS;
    }
}
