package com.example.jiangxinwei.pacman;

import android.content.Context;
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

    public static final int STEPDELAY = 100;

    Paint paint;
    Handler repaintHandler;
    Game game;
    ArrayList<GameOver> observers;



    public PacmanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();

//        this.setOnClickListener(this);
//        this.setButtonSettings();

        observers = new ArrayList<GameOver>();
        game = new Game();
        repaintHandler = new Handler();
        repaintHandler.postDelayed(this, 10);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        paint.setColor(Color.BLUE);
        //canvas.drawRect(0.0f*w, 0.25f*h, 1.0f*w, 0.55f*h, paint);
        game.draw(canvas, paint);
    }

    @Override
    public void run() {
        if (step()) {
            repaintHandler.postDelayed(this, PacmanView.STEPDELAY);
        }
    }

    public boolean step(){
        this.invalidate();
        return true;
    }
}
