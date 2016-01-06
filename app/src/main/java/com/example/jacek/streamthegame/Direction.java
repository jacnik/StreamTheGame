package com.example.jacek.streamthegame;

/**
 * Created by jacek on 12/10/2015.
 */
public enum Direction {
    LEFT, UP, RIGHT, DOWN;

    public Direction next() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    public Direction opposite() { return values()[(this.ordinal() + 2) % values().length]; }

    public int getDiffFrom(Direction dir) {
        return dir.ordinal() - this.ordinal();
    }
}
