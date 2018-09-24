package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

class Game {
    private Walls wallsHorizon;
    private Walls wallsVertic;
    private Beans beans;
    private Chasers chasers;
    private Computer computer;

    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;


    public Game()
    {
        wallsHorizon = Walls.createWallsHorizon();
        wallsVertic = Walls.createWallsVertic();
        beans = Beans.createBeans();
        chasers = new Chasers();
        computer =  new Computer();
    }

    public void draw(Canvas canvas, Paint paint) {
        wallsHorizon.drawH(canvas, paint);
        wallsVertic.drawV(canvas, paint);
        beans.draw(canvas, paint);
        chasers.draw(canvas, paint);
        computer.draw(canvas, paint);
    }

    public void step(){
        //computer.step();
        chasers.step();

        //Find the closest bean
        Float closeDis = 2f;
        Float dis;
        Float closeX = 0f;
        Float closeY = 0f;
        for (Bean b: beans){
            dis = computer.pos.distance(b.pos);
            if(dis < closeDis){
                closeDis = dis;
                closeX = b.pos.x;
                closeY = b.pos.y;
            }
        }
        //Log.d("close", String.valueOf(closeX) + " " + String.valueOf(closeY));

        //Let computer move;
        computer.step(closeX, closeY);

        //Remove bean which has been eaten
        beans.removeEat(computer);
        //Log.d("size", String.valueOf(beans.size()));
    }
}
