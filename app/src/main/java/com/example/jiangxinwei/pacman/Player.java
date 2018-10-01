package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Player extends Sprite {

    public static final float PWIDTH = (1.0f / 45.0f);
    public static final float STARTX = 0.5f;
    public static final float STARTY = 0.5f;

    public static int height;
    public static int width;

    public Player() {
        pos = new Pos(STARTX, STARTY);
    }

    @Override
    public void draw(Canvas c, Paint p) {
        height = c.getHeight();
        width = c.getWidth();
        p.setColor(Color.GREEN);
        c.drawCircle(pos.x * width, pos.y * height, PWIDTH * width, p);
    }

    public static Player createPlayer() {
        return new Player();
    }
}
