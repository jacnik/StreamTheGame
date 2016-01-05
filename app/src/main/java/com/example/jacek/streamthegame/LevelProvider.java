package com.example.jacek.streamthegame;

import java.util.ArrayList;

/**
 * Created by jacek on 1/5/2016.
 */
public class LevelProvider {
    public LevelDefinition getLevel(int level) {
        switch (level) {
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
}
