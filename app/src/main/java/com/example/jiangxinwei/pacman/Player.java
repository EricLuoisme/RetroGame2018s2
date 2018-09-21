package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Player extends Sprite {

    public static final float WIDTH = 30.0f;
    public static final float STARTX = 0.5f;
    public static final float STARTY = 0.5f;


    public Player() {
        pos = new Pos(STARTX, STARTY);
    }

    @Override
    public void draw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();
        c.drawCircle(pos.x * w, pos.y * h, WIDTH, p);
    }


    public static Player createPlayer() {
        return new Player();
    }

}
