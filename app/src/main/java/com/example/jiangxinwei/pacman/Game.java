package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;

class Game {
    private Walls wallsHorizon;
    private Walls wallsVertic;
    private Beans beans;
    private Chasers chasers;

    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;


    public Game()
    {
        wallsHorizon = Walls.createWallsHorizon();
        wallsVertic = Walls.createWallsVertic();
        beans = Beans.createBeans();
        chasers = new Chasers();
    }

    public void draw(Canvas canvas, Paint paint) {
        wallsHorizon.drawH(canvas, paint);
        wallsVertic.drawV(canvas, paint);
        beans.draw(canvas, paint);
        chasers.draw(canvas, paint);
    }

    public void step(){
        chasers.step();
    }
}
