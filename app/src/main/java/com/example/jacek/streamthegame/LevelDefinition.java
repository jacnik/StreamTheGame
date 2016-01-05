package com.example.jacek.streamthegame;

import java.util.ArrayList;

/**
 * Created by jacek on 1/5/2016.
 */
public class LevelDefinition {
    private int height, width;
    private GameObjectDefinition exit, enter;
    private ArrayList<GameObjectDefinition> objects = new ArrayList<>();

    public LevelDefinition(
            int height, int width,
            GameObjectDefinition exit, GameObjectDefinition enter,
            ArrayList<GameObjectDefinition> objects) {
        this.height = height;
        this.width = width;
        this.exit = exit;
        this.enter = enter;
        this.objects = objects;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public GameObjectDefinition getExit() {
        return exit;
    }

    public GameObjectDefinition getEnter() {
        return enter;
    }

    public ArrayList<GameObjectDefinition> getObjects() {
        return objects;
    }
}
