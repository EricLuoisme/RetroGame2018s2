package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Chaser extends Sprite {

    private static final float CHASERRADIUS = (1.0f / 55.0f);

    public Chaser(Pos p){
        pos = new Pos(p);
    }

    @Override
    public void draw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        float xc = pos.x * w;
        float yc = pos.y * h;
        float cd = CHASERRADIUS * w;
        p.setColor(Color.BLACK);
        c.drawCircle(xc, yc, cd, p);
    }
}
