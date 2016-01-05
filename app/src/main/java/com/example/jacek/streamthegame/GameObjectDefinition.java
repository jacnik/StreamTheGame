package com.example.jacek.streamthegame;

/**
 * Created by jacek on 1/5/2016.
 */
public class GameObjectDefinition {
    private Sprite sprite;
    private int insertionRow;
    private int insertionCol;
    private int rotations;

    public GameObjectDefinition(Sprite sprite, int insertionRow, int insertionCol, int rotations) {
        this.sprite = sprite;
        this.insertionRow = insertionRow;
        this.insertionCol = insertionCol;
        this.rotations = rotations;
    }

    /** Second constructor to make rotations a default parameter */
    public GameObjectDefinition(Sprite sprite, int insertionRow, int insertionCol) {
        this(sprite, insertionRow, insertionCol, 0);
    }

    public int getInsertionCol() {
        return insertionCol;
    }

    public int getInsertionRow() {
        return insertionRow;
    }

    public int getRotations() {
        return rotations;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
