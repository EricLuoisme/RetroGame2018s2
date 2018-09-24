package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.text.DecimalFormat;


public class Computer extends Sprite{

    public Computer(){
        pos = new Pos(0.95f, 0.1f);
    }
    @Override
    public void draw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        float xc = pos.x * w;
        float yc = pos.y * h;

        p.setColor(Color.GREEN);
        c.drawCircle(xc, yc,30, p);
    }

    public void step(Float closeX, Float closeY) {
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        String x = decimalFormat.format(pos.x);
        String y = decimalFormat.format(pos.y);
        pos.x = Float.parseFloat(x);
        pos.y = Float.parseFloat(y);
        x = decimalFormat.format(closeX);
        y = decimalFormat.format(closeY);
        closeX = Float.parseFloat(x);
        closeY = Float.parseFloat(y);
        if(closeX < pos.x && closeY == pos.y){   //in the left
            pos.x = pos.x - 0.1f;
            //Log.d("left", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX < pos.x && closeY < pos.y){    //in the left top
            pos.x = pos.x - 0.1f;
            //Log.d("left top", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX < pos.x && closeY > pos.y){    //in the left bottom
            pos.x = pos.x - 0.1f;
            //Log.d("left bottom", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX > pos.x && closeY == pos.y){   //in the right
            pos.x = pos.x + 0.1f;
            //Log.d("right", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX > pos.x && closeY < pos.y){    //in the right top
            pos.x = pos.x + 0.1f;
            //Log.d("right top", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX > pos.x && closeY > pos.y){    //in the right bottom
            pos.x = pos.x + 0.1f;
            Log.d("right bottom", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX == pos.x && closeY > pos.y){   //on the bottom
            pos.y = pos.y + 0.2f;
            //Log.d("bottom", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX == pos.x && closeY < pos.y){   //on the top
            pos.y = pos.y - 0.2f;
            //Log.d("up", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
    }
}
