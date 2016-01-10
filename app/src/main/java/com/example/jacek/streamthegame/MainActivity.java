package com.example.jacek.streamthegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private GamePanel gamePanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // set full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.gamePanel = new GamePanel(this);
        setContentView(this.gamePanel);
    }

    @Override
    public void onBackPressed() {
        if (this.gamePanel.backPressed()) {
            super.onBackPressed();
        }
    }
}
