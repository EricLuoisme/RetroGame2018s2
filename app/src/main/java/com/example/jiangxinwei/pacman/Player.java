package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Player extends Sprite {

    public static final float PWIDTH = (1.0f / 55.0f);
    public static final float STARTX = 0.25f;
    public static final float STARTY = 0.1f;
    public static final float STEPX = 0.1f;
    public static final float STEPY = 0.2f;

    public static int height;
    public static int width;

    public Player() {
        pos = new Pos(STARTX, STARTY);
    }

    @Override
    public void draw(Canvas c, Paint p) {
        height = c.getHeight();
        width = c.getWidth();
        p.setColor(Color.RED);
        c.drawCircle(pos.x * width, pos.y * height, PWIDTH * width, p);
    }

    public static Player createPlayer() {
        return new Player();
    }

    public boolean hitByChaser(Chasers chasers){
        boolean hit = false;
        for(Chaser c:chasers){
            if(c.pos.distance(pos) <= 1.0f/30.0f){
                hit = true;
            }
        }
        return hit;
    }

}

