package com.example.jiangxinwei.pacman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GameActivity extends AppCompatActivity implements GameOver {

    private PacmanView pacmanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        pacmanView = (PacmanView) findViewById(R.id.pacmanView);
        pacmanView.registerGameOver(this);
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
        d.getBackground().setAlpha(70);
        Button l = findViewById(R.id.buttonLeft);
        l.getBackground().setAlpha(70);
        Button r = findViewById(R.id.buttonRight);
        r.getBackground().setAlpha(70);
    }

    public PacmanView getPacmanView() {
        return pacmanView;
    }

    @Override
    public void gameOver() {
        setResult(AppCompatActivity.RESULT_OK);
        finish();
    }
}
