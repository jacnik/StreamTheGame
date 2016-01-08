package com.example.jacek.streamthegame;

import java.util.EventObject;

/**
 * Created by jacek on 1/8/2016.
 */
public class AnimationFinishedEvent extends EventObject {

    private boolean success;

    public AnimationFinishedEvent(Object source, boolean isSuccess) {
        super(source);
        this.success = isSuccess;
    }

    public boolean isSuccess() {
        return success;
    }
}
