package com.example.jiangxinwei.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Player extends Sprite {

    public static final float PWIDTH = (1.0f / 55.0f);
    public static final float STARTX = 0.25f;
    public static final float STARTY = 0.1f;
    public static final float STEPX = 0.1f;
    public static final float STEPY = 0.2f;

    public static int height;
    public static int width;
    public static float sweepAngle = 270;

    public float startAngle = 45;
    public int timemer = 1;


    public Player() {
        pos = new Pos(STARTX, STARTY);
    }

    @Override
    public void draw(Canvas c, Paint p, Bitmap b) {
        height = c.getHeight();
        width = c.getWidth();

        // draw body
        p.setAlpha(255);
        p.setColor(Color.YELLOW);
        if (timemer % 2 == 1) {
            RectF rectF = new RectF((pos.x - PWIDTH) * width, pos.y * height - PWIDTH * width, (pos.x + PWIDTH) * width, pos.y * height + PWIDTH * width);
            c.drawArc(rectF, startAngle, sweepAngle, true, p);
        } else {
            c.drawCircle(pos.x * width, pos.y * height, PWIDTH * width, p);
        }

        // draw eye
        Float eye;
        p.setColor(Color.BLACK);
        if (startAngle == 315) {
            eye = (float) (pos.y + 0.75 * PWIDTH) * height;
        } else {
            eye = (float) (pos.y - 0.75 * PWIDTH) * height;
        }
        c.drawCircle(pos.x * width, eye, 5, p);

        // draw mouth
        if (timemer % 2 == 0) {
            p.setStrokeWidth(3);
            if (startAngle == 45) {
                c.drawLine(pos.x * width, pos.y * height, pos.x * width + PWIDTH * width, pos.y * height, p);
            } else if (startAngle == 135) {
                c.drawLine(pos.x * width, pos.y * height, pos.x * width, pos.y * height + PWIDTH * width, p);
            } else if (startAngle == 225){
                c.drawLine(pos.x * width, pos.y * height, pos.x * width - PWIDTH * width, pos.y * height, p);
            } else {
                c.drawLine(pos.x * width, pos.y * height, pos.x * width, pos.y * height - PWIDTH * width, p);
            }
            timemer += 1;
        }

    }

    public static Player createPlayer() {
        return new Player();
    }

    public boolean hitByChaser(Chasers chasers) {
        boolean hit = false;
        for (Chaser c : chasers) {
            if (c.pos.distance(pos) <= 1.0f / 30.0f) {
                hit = true;
            }
        }
        return hit;
    }

}

