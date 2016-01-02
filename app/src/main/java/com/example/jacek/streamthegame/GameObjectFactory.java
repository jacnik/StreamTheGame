package com.example.jacek.streamthegame;

import android.content.Context;

import com.example.jacek.streamthegame.GameObjects.Bend2;
import com.example.jacek.streamthegame.GameObjects.Enter;
import com.example.jacek.streamthegame.GameObjects.Exit;
import com.example.jacek.streamthegame.GameObjects.GameObject;
import com.example.jacek.streamthegame.GameObjects.ShortPipe;

/**
 * Created by jacek on 1/2/2016.
 */
public class GameObjectFactory {

    private Context context;
    private int cellWidth, cellHeight;

    public GameObjectFactory(Context context, int cellWidth, int cellHeight) {
        this.context = context;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    public GameObject getObject(Sprite sprite) {
        GameObject obj = null;
        switch (sprite) {
            case exit:
                obj = new Exit(this.context, this.cellWidth, this.cellHeight);
                break;
            case enter:
                obj = new Enter(this.context, this.cellWidth, this.cellHeight);
                break;
            case short_pipe:
                obj = new ShortPipe(this.context, this.cellWidth, this.cellHeight);
                break;
            case rotated_short_pipe:
//                obj = new GameObject(BitmapFactory.decodeResource(
//                        this.context.getResources(),
//                        R.drawable.short_pipe),
//                        1, 2,
//                        this.cellWidth, this.cellHeight);
//                obj.rotate();
                //this.objects.put(pipe, point);
                break;
            case bend2:
                obj = new Bend2(this.context, this.cellWidth, this.cellHeight);
                break;
            default:
                break;
        }
        return obj;
    }

}
