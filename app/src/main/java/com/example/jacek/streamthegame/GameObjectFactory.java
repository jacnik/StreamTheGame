package com.example.jacek.streamthegame;

import android.content.Context;

import com.example.jacek.streamthegame.GameObjects.Bend2;
import com.example.jacek.streamthegame.GameObjects.Cbend;
import com.example.jacek.streamthegame.GameObjects.Enter;
import com.example.jacek.streamthegame.GameObjects.ExitObject;
import com.example.jacek.streamthegame.GameObjects.GameObject;
import com.example.jacek.streamthegame.GameObjects.Lbend;
import com.example.jacek.streamthegame.GameObjects.Sbend;
import com.example.jacek.streamthegame.GameObjects.ShortPipe;

/**
 * Created by jacek on 1/2/2016.
 */
public class GameObjectFactory {

    private Context context;

    public GameObjectFactory(Context context) {
        this.context = context;
    }

    public GameObject getObject(Sprite sprite, int cellWidth, int cellHeight) {
        GameObject obj = null;
        switch (sprite) {
            case exit:
                obj = new ExitObject(this.context, cellWidth, cellHeight);
                break;
            case enter:
                obj = new Enter(this.context, cellWidth, cellHeight);
                break;
            case short_pipe:
                obj = new ShortPipe(this.context, cellWidth, cellHeight);
                break;
            case bend2:
                obj = new Bend2(this.context, cellWidth, cellHeight);
                break;
            case c_bend:
                obj = new Cbend(this.context, cellWidth, cellHeight);
                break;
            case s_bend:
                obj = new Sbend(this.context, cellWidth, cellHeight);
                break;
            case l_bend:
                obj = new Lbend(this.context, cellWidth, cellHeight);
                break;
            default:
                break;
        }
        return obj;
    }

}
