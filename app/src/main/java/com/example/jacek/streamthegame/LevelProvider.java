package com.example.jacek.streamthegame;

import java.util.ArrayList;

/**
 * Created by jacek on 1/5/2016.
 */
public class LevelProvider {

    public int getLevelCount() {
        return 10;
    }

    public LevelDefinition getLevel(int level) {
        switch (level) {
            case 0:
                return this.debugLevel_1();
            case 1:
                return defaultLevel();
                //break;
            default: return defaultLevel();
        }
    }

    private LevelDefinition defaultLevel() {
        int nRows = 7;
        int nCols = 10;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        objects.add(new GameObjectDefinition(Sprite.exit, 3, 8, 2));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 1, 2));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 3, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 5));
        objects.add(new GameObjectDefinition(Sprite.bend2, 5, 7));
        return new LevelDefinition(nRows, nCols, objects);
    }

    // section of levels for debugging purposes
    private LevelDefinition debugLevel_1() {
        int nRows = 10;
        int nCols = 20;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        objects.add(new GameObjectDefinition(Sprite.exit, 5, 5, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 5, 6, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 3, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 5));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 5, 7));
        //objects.add(new GameObjectDefinition(Sprite.bend2, 5, 7));
        return new LevelDefinition(nRows, nCols, objects);
    }

    private LevelDefinition debugLevel_2() {
        int nRows = 7;
        int nCols = 10;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        objects.add(new GameObjectDefinition(Sprite.exit, 3, 3));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 0, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 0, 3));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 0, 5));
        objects.add(new GameObjectDefinition(Sprite.enter, 4, 3, 2));


        return new LevelDefinition(nRows, nCols, objects);
    }

    private LevelDefinition debugLevel_3() {
        int nRows = 7;
        int nCols = 10;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 2, 3, 1));

        objects.add(new GameObjectDefinition(Sprite.exit, 3, 2));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 7));

        return new LevelDefinition(nRows, nCols, objects);
    }
}
