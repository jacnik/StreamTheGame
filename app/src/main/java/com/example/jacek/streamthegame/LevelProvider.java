package com.example.jacek.streamthegame;

import java.util.ArrayList;

/**
 * Created by jacek on 1/5/2016.
 */
public class LevelProvider {
    public LevelDefinition getLevel(int level) {
        switch (level) {
            case 0:
                return this.debugLevel_2();
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
        int nRows = 7;
        int nCols = 10;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        objects.add(new GameObjectDefinition(Sprite.exit, 3, 1, 4));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 8, 4));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 3, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 5));
        objects.add(new GameObjectDefinition(Sprite.bend2, 5, 7));
        return new LevelDefinition(nRows, nCols, objects);
    }

    private LevelDefinition debugLevel_2() {
        int nRows = 7;
        int nCols = 10;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        objects.add(new GameObjectDefinition(Sprite.exit, 0, 0));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 0, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 3));

        objects.add(new GameObjectDefinition(Sprite.exit, 1, 0, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 2, 0));
        objects.add(new GameObjectDefinition(Sprite.enter, 4, 0, 1));

        objects.add(new GameObjectDefinition(Sprite.exit, 2, 4, 2));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 2, 2, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 2, 1, 2));

        objects.add(new GameObjectDefinition(Sprite.exit, 5, 5, 3));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 3, 5, 2));
        objects.add(new GameObjectDefinition(Sprite.enter, 2, 5, 3));

        return new LevelDefinition(nRows, nCols, objects);
    }
}
