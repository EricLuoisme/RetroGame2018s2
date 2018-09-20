package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

public class Beans extends ArrayList<Bean> {

    public static Beans createBeans() {
        Beans  beans = new Beans();
        //each rows
        for(float x = 0.05f; x < 0.99f; x += 0.1f)
        {
            //Log.d("beans", String.valueOf(x));
            Pos p = new Pos(x, 0.1f);
            beans.add(new Bean(p));
        }
        for(float x = 0.05f; x < 1f; x += 0.1f)
        {
            Pos p = new Pos(x, 0.3f);
            beans.add(new Bean(p));
        }
        for(float x = 0.05f; x < 1f; x += 0.1f)
        {
            Pos p = new Pos(x, 0.5f);
            beans.add(new Bean(p));
        }
        for(float x = 0.05f; x < 1f; x += 0.1f)
        {
            Pos p = new Pos(x, 0.7f);
            beans.add(new Bean(p));
        }
        for(float x = 0.05f; x < 1f; x += 0.1f)
        {
            Pos p = new Pos(x, 0.9f);
            beans.add(new Bean(p));
        }
        return beans;
    }

    public void draw(Canvas canvas, Paint paint) {
        Log.d("beans", String.valueOf(this.size()));
        paint.setColor(Color.BLUE);
        for (Bean b: this) b.draw(canvas, paint);
//        Pos p = new Pos(0.5f, 0.1f);
//        Bean b = new Bean(p);
//        b.draw(canvas, paint);
    }
}
