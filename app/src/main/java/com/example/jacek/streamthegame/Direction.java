package com.example.jacek.streamthegame;

/**
 * Created by jacek on 12/10/2015.
 */
public enum Direction {
    RIGTH, DOWN, LEFT, UP;

    public Direction next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
