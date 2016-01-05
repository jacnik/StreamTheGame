package com.example.jacek.streamthegame;

import java.util.ArrayList;

/**
 * Created by jacek on 1/5/2016.
 */
public class LevelDefinition {
    private int height, width;
    private ArrayList<GameObjectDefinition> objects = new ArrayList<>();

    public LevelDefinition(int height, int width, ArrayList<GameObjectDefinition> objects) {
        this.height = height;
        this.width = width;
        this.objects = objects;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<GameObjectDefinition> getObjects() {
        return objects;
    }
}
