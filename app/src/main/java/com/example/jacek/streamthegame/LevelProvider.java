package com.example.jacek.streamthegame;

import java.util.ArrayList;

/**
 * Created by jacek on 1/5/2016.
 */
public class LevelProvider {
    public LevelDefinition getLevel(int level) {
        switch (level) {
            case 1:
                return defaulLevel();
                //break;
            default: return defaulLevel();
        }
    }

    private LevelDefinition defaulLevel() {
        int nRows = 7;
        int nCols = 10;
        GameObjectDefinition exit = new GameObjectDefinition(Sprite.exit, 1, 2);
        GameObjectDefinition enter = new GameObjectDefinition(Sprite.enter, 5, 6, 1);
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 2, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 3, 4));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 4, 5));
        objects.add(new GameObjectDefinition(Sprite.bend2, 5, 7));
        return new LevelDefinition(nRows, nCols, exit, enter, objects);
    }
}
