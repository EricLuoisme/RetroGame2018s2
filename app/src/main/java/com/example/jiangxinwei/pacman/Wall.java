package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

// The author of this class file is Xinwei Jiang

public class Wall{
    Pos pos;

    public Wall(Pos p){
        pos = new Pos(p);
    }

    public void drawHorizon(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        float left = pos.x * w;
        float top = pos.y * h;
        float right = (pos.x + 0.1f) * w;
        float bottom = (pos.y + 0.02f) * h;
        p.setColor(Color.WHITE);
        c.drawRect(left, top, right, bottom, p);
    }
    public void drawVertic(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        float left = pos.x * w;
        float top = pos.y * h;
        float right = (pos.x + 0.01f) * w;
        float bottom = (pos.y + 0.2f) * h;
        p.setColor(Color.WHITE);
        c.drawRect(left, top, right, bottom, p);
    }
}
