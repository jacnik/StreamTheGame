package com.example.jacek.streamthegame;

/**
 * Created by jacek on 12/10/2015.
 */
public enum Direction {
    RIGHT, DOWN, LEFT, UP;

    public Direction next() {
        return values()[(ordinal() + 1) % values().length];
    }

    public Direction opposite() { return values()[(ordinal() + 2) % values().length]; }
}
