package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

// The author of this class file is Xinwei Jiang

public class Walls extends ArrayList<Wall> {

    public static Walls createWallsHorizon() {
        Walls  wallsH = new Walls();
        //each rows
        for(float x = 0.3f; x < 0.95f; x += 0.3f)
        {
            Pos p = new Pos(x, 0.4f);
            wallsH.add(new Wall(p));
        }
        for(float x = 0.2f; x < 0.95f; x += 0.2f)
        {
            Pos p = new Pos(x, 0.6f);
            wallsH.add(new Wall(p));
        }
        for(float x = 0.2f; x < 0.95f; x += 0.4f)
        {
            Pos p = new Pos(x, 0.8f);
            wallsH.add(new Wall(p));
        }
        return wallsH;
    }

    public static Walls createWallsVertic() {
        Walls  wallsV = new Walls();
        for(float y = 0.0f; y < 0.95f; y += 0.4f)
        {
            Pos p = new Pos(0.2f, y);
            wallsV.add(new Wall(p));
        }
        for(float y = 0.4f; y < 0.95f; y += 0.2f)
        {
            Pos p = new Pos(0.4f, y);
            wallsV.add(new Wall(p));
        }
        for(float y = 0.4f; y < 0.75f; y += 0.2f)
        {
            Pos p = new Pos(0.6f, y);
            wallsV.add(new Wall(p));
        }
        for(float y = 0.0f; y < 0.95f; y += 0.4f)
        {
            Pos p = new Pos(0.8f, y);
            wallsV.add(new Wall(p));
        }
        return wallsV;
    }

    public void drawH(Canvas canvas, Paint paint) {
        for (Wall w: this) w.drawHorizon(canvas, paint);
    }

    public void drawV(Canvas canvas, Paint paint) {
        for (Wall w: this) w.drawVertic(canvas, paint);
    }
}
