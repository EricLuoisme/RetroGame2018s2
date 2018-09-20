package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Bean extends Sprite{

    public Bean(Pos p){
        pos = new Pos(p);
    }

    @Override
    public void draw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();
        c.drawCircle(pos.x * w, pos.y * h, 30, p);
    }
}
