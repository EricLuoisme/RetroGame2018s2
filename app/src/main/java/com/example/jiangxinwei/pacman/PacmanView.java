package com.example.jiangxinwei.pacman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class PacmanView extends View implements Runnable {
    Paint paint;
    Handler repaintHandler;
    Game game;
    ArrayList<GameOver> observers;
    public static final int STEPDELAY = 1000;

    public PacmanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        observers = new ArrayList<GameOver>();
        game = new Game();

        repaintHandler = new Handler();
        repaintHandler.postDelayed(this, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        paint.setColor(Color.BLACK);
        paint.setTextSize(37.0f);
        paint.setFakeBoldText(true);
        canvas.drawText("Computer score :", 0.015f * w, 0.1f * h, paint);
        canvas.drawText("Player score :", 0.015f * w, 0.6f * h, paint);
        game.draw(canvas, paint);
    }

    public boolean step() {
        game.step();
        if (game.playerWon()) {
            notifyGameOver();
            this.getContext().startActivity(new Intent(this.getContext(), WonActivity.class));
            return false;
        } else if (game.playerLose()) {
            notifyGameOver();
            this.getContext().startActivity(new Intent(this.getContext(), LoseActivity.class));
            return false;
        }
        this.invalidate();
        return true;
    }

    private void notifyGameOver() {
        for (GameOver o : observers) o.gameOver();
    }

    @Override
    public void run() {
        if (step()) {
            repaintHandler.postDelayed(this, PacmanView.STEPDELAY);
        }
    }

    public void registerGameOver(GameOver gameover) {
        observers.add(gameover);
    }

}
