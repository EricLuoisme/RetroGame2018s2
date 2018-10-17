package com.example.jiangxinwei.pacman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class PacmanView extends View implements Runnable {
    Paint paint;
    Handler repaintHandler;
    Game game;
    ArrayList<GameOver> observers;
    public static final int STEPDELAY = 500;
    Bitmap computerImage, chaserImage;


    public PacmanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        observers = new ArrayList<GameOver>();
        game = new Game();

        this.setBackgroundColor(Color.rgb(41, 36, 33));
        repaintHandler = new Handler();
        repaintHandler.postDelayed(this, 500);
        computerImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.laptop), 90, 90, true);
        chaserImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.chaser), 120, 120, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        paint.setColor(Color.WHITE);
        paint.setTextSize(37.0f);
        paint.setFakeBoldText(true);
        canvas.drawText("Computer score :", 0.015f * w, 0.1f * h, paint);
        canvas.drawText("  Player score :", 0.015f * w, 0.6f * h, paint);
        List<Bitmap> images = new ArrayList<>();
        images.add(chaserImage);
        images.add(computerImage);
        images.add(computerImage);
        game.draw(canvas, paint, images);
    }

    public boolean step() {
        game.step();
        if (game.playerWon()) {
            notifyGameOver();
            Intent intent = new Intent(this.getContext(), WonActivity.class);
            intent.putExtra("score", Game.playerScore);
            Game.playerScore = "0";
            this.getContext().startActivity(intent);
            return false;
        } else if (game.playerLose()) {
            notifyGameOver();
            Intent intent = new Intent(this.getContext(), LoseActivity.class);
            intent.putExtra("score", Game.playerScore);
            Game.playerScore = "0";
            this.getContext().startActivity(intent);
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
