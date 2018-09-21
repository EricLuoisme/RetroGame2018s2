package com.example.jiangxinwei.pacman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    private PacmanView pacmanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        pacmanView = (PacmanView) findViewById(R.id.pacmanView);
        buttonsSettings();
    }

    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.buttonUp:
                pacmanView.game.touch("u");
                break;
            case R.id.buttonDown:
                pacmanView.game.touch("d");
                break;
            case R.id.buttonLeft:
                pacmanView.game.touch("l");
                break;
            default:
                pacmanView.game.touch("r");
                break;

        }
    }

    private void buttonsSettings() {
        Button u = findViewById(R.id.buttonUp);
        u.getBackground().setAlpha(70);
        Button d = findViewById(R.id.buttonDown);
        u.getBackground().setAlpha(70);
        Button l = findViewById(R.id.buttonLeft);
        u.getBackground().setAlpha(70);
        Button r = findViewById(R.id.buttonRight);
        u.getBackground().setAlpha(70);
    }
}
