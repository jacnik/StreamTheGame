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
                return this.debugLevel_lbendAnimations();
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
        int nCols = 14;
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

    private LevelDefinition debugLevel_4() { // for testing Animation orientations with pipes

        int nRows = 7;
        int nCols = 10;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        objects.add(new GameObjectDefinition(Sprite.exit, 0, 0));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 0, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 3));

        objects.add(new GameObjectDefinition(Sprite.exit, 0, 4, 1));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 1, 4));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 4, 1));

        objects.add(new GameObjectDefinition(Sprite.exit, 2, 3, 2));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 2, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 2, 0, 2));

        objects.add(new GameObjectDefinition(Sprite.exit, 6, 6, 3));
        objects.add(new GameObjectDefinition(Sprite.short_pipe, 4, 6));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 6, 3));

        return new LevelDefinition(nRows, nCols, objects);
    }

    private LevelDefinition debugLevel_cbendAnimations() {
        int nRows = 7;
        int nCols = 10;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        objects.add(new GameObjectDefinition(Sprite.exit, 0, 0));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 0, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 1, 0, 2));

        objects.add(new GameObjectDefinition(Sprite.exit, 1, 2));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 0, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 2, 2));

        objects.add(new GameObjectDefinition(Sprite.exit, 0, 5, 2));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 0, 4, 2));
        objects.add(new GameObjectDefinition(Sprite.enter, 1, 5));

        objects.add(new GameObjectDefinition(Sprite.exit, 1, 7, 2));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 0, 6, 2));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 7));

        objects.add(new GameObjectDefinition(Sprite.exit, 2, 0, 1));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 3, 0, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 2, 1, 3));

        objects.add(new GameObjectDefinition(Sprite.exit, 2, 3, 1));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 3, 2, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 2, 2, 3));

        objects.add(new GameObjectDefinition(Sprite.exit, 3, 4, 3));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 2, 4, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 5, 1));

        objects.add(new GameObjectDefinition(Sprite.exit, 3, 7, 3));
        objects.add(new GameObjectDefinition(Sprite.c_bend, 2, 6, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 6, 1));

        return new LevelDefinition(nRows, nCols, objects);
    }

    private LevelDefinition debugLevel_bend2Animations() {
        int nRows = 7;
        int nCols = 12;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        // bend rotation- 0
        objects.add(new GameObjectDefinition(Sprite.exit, 2, 0));
        objects.add(new GameObjectDefinition(Sprite.bend2, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 2, 3));

        objects.add(new GameObjectDefinition(Sprite.exit, 0, 5, 1));
        objects.add(new GameObjectDefinition(Sprite.bend2, 1, 4));
        objects.add(new GameObjectDefinition(Sprite.enter, 2, 3, 2));

        // bend rotation- 1
        objects.add(new GameObjectDefinition(Sprite.exit, 0, 6, 1));
        objects.add(new GameObjectDefinition(Sprite.bend2, 1, 6, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 2, 8));

        objects.add(new GameObjectDefinition(Sprite.exit, 2, 11, 2));
        objects.add(new GameObjectDefinition(Sprite.bend2, 1, 9, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 9, 3));

        // bend rotation- 2
        objects.add(new GameObjectDefinition(Sprite.bend2, 3, 0, 2));
        objects.add(new GameObjectDefinition(Sprite.exit, 5, 0, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 2));

        objects.add(new GameObjectDefinition(Sprite.bend2, 3, 3, 2));
        objects.add(new GameObjectDefinition(Sprite.exit, 3, 5, 2));
        objects.add(new GameObjectDefinition(Sprite.enter, 5, 3, 1));

        // bend rotation- 3
        objects.add(new GameObjectDefinition(Sprite.bend2, 3, 7, 3));
        objects.add(new GameObjectDefinition(Sprite.exit, 5, 8, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 6, 2));

        objects.add(new GameObjectDefinition(Sprite.exit, 3, 9));
        objects.add(new GameObjectDefinition(Sprite.bend2, 3, 10, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 5, 11, 1));

        return new LevelDefinition(nRows, nCols, objects);
    }

    private LevelDefinition debugLevel_sbendAnimations() {
        int nRows = 10;
        int nCols = 12;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();
        // bend rotation- 0
        objects.add(new GameObjectDefinition(Sprite.exit, 1, 0));
        objects.add(new GameObjectDefinition(Sprite.s_bend, 0, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 3));

        objects.add(new GameObjectDefinition(Sprite.exit, 0, 7, 2));
        objects.add(new GameObjectDefinition(Sprite.s_bend, 1, 5));
        objects.add(new GameObjectDefinition(Sprite.enter, 1, 4, 2));

        // bend rotation- 1
        objects.add(new GameObjectDefinition(Sprite.exit, 0, 7, 1));
        objects.add(new GameObjectDefinition(Sprite.s_bend, 1, 7, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 8, 1));

        objects.add(new GameObjectDefinition(Sprite.exit, 3, 10, 3));
        objects.add(new GameObjectDefinition(Sprite.s_bend, 1, 9, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 9, 3));

        // bend rotation- 2
        objects.add(new GameObjectDefinition(Sprite.exit, 3, 0));
        objects.add(new GameObjectDefinition(Sprite.s_bend, 2, 1, 2));
        objects.add(new GameObjectDefinition(Sprite.enter, 2, 3));

        objects.add(new GameObjectDefinition(Sprite.exit, 2, 7, 2));
        objects.add(new GameObjectDefinition(Sprite.s_bend, 3, 5, 2));
        objects.add(new GameObjectDefinition(Sprite.enter, 4, 4, 2));

        // bend rotation- 3
        objects.add(new GameObjectDefinition(Sprite.exit, 4, 7, 1));
        objects.add(new GameObjectDefinition(Sprite.s_bend, 5, 7, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 7, 8, 1));

        objects.add(new GameObjectDefinition(Sprite.exit, 7, 10, 3));
        objects.add(new GameObjectDefinition(Sprite.s_bend, 5, 9, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 4, 9, 3));

        return new LevelDefinition(nRows, nCols, objects);
    }
    private LevelDefinition debugLevel_lbendAnimations() {
        int nRows = 7;
        int nCols = 10;
        ArrayList<GameObjectDefinition> objects = new ArrayList<>();

        objects.add(new GameObjectDefinition(Sprite.exit, 0, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.exit, 2, 2));
        objects.add(new GameObjectDefinition(Sprite.exit, 0, 4, 1));
        objects.add(new GameObjectDefinition(Sprite.exit, 1, 9, 2));
        objects.add(new GameObjectDefinition(Sprite.exit, 3, 1, 2));
        objects.add(new GameObjectDefinition(Sprite.exit, 5, 2, 3));
        objects.add(new GameObjectDefinition(Sprite.exit, 3, 4));
        objects.add(new GameObjectDefinition(Sprite.exit, 4, 9, 3));

        objects.add(new GameObjectDefinition(Sprite.enter, 2, 0, 2));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 3, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 1, 6));
        objects.add(new GameObjectDefinition(Sprite.enter, 0, 7, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 5, 0, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 3));
        objects.add(new GameObjectDefinition(Sprite.enter, 4, 6, 1));
        objects.add(new GameObjectDefinition(Sprite.enter, 3, 7, 2));

        objects.add(new GameObjectDefinition(Sprite.l_bend, 1, 1));
        objects.add(new GameObjectDefinition(Sprite.l_bend, 1, 3));
        objects.add(new GameObjectDefinition(Sprite.l_bend, 1, 4, 1));
        objects.add(new GameObjectDefinition(Sprite.l_bend, 1, 7, 1));
        objects.add(new GameObjectDefinition(Sprite.l_bend, 3, 0, 2));
        objects.add(new GameObjectDefinition(Sprite.l_bend, 3, 2, 2));
        objects.add(new GameObjectDefinition(Sprite.l_bend, 3, 5, 3));
        objects.add(new GameObjectDefinition(Sprite.l_bend, 3, 8, 3));

        return new LevelDefinition(nRows, nCols, objects);
    }
}
