package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;

class Game {
    private Walls wallsHorizon;
    private Walls wallsVertic;
    private Beans beans;

    public Game()
    {
        wallsHorizon = Walls.createWallsHorizon();
        wallsVertic = Walls.createWallsVertic();
        beans = Beans.createBeans();
    }

    public void draw(Canvas canvas, Paint paint) {
        wallsHorizon.drawH(canvas, paint);
        wallsVertic.drawV(canvas, paint);
        beans.draw(canvas, paint);
    }
}
