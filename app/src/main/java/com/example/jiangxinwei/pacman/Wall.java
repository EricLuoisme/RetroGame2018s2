package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Wall{
    int brown = Color.rgb(139,69,19);
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
//        p.setColor(brown);
        p.setColor(Color.rgb(0,255,255));
        c.drawRect(left, top, right, bottom, p);
    }
    public void drawVertic(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        float left = pos.x * w;
        float top = pos.y * h;
        float right = (pos.x + 0.01f) * w;
        float bottom = (pos.y + 0.2f) * h;
//        p.setColor(brown);
        p.setColor(Color.rgb(0,255,255));
        c.drawRect(left, top, right, bottom, p);
    }
}
