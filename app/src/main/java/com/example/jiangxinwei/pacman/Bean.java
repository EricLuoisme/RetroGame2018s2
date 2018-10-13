package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.text.DecimalFormat;

public class Bean {

    Pos pos;
    int pink = Color.rgb(219,112,147);
    public Bean(Pos p) {
        pos = new Pos(p);
    }

    public void draw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();
        p.setColor(pink);
        c.drawCircle(pos.x * w, pos.y * h, 30, p);
    }

    public boolean eatby(Computer c) {   //determin if any bean eats by computer
        boolean eat = false;
        if (c.pos.distance(pos) < 1.0f / 30.0f) {
            eat = true;
        }
        return eat;
    }

    public boolean eatby(Player p) {   //determin if any bean eats by player
        boolean eat = false;
        if (p.pos.distance(pos) < 1.0f / 30.0f) {
            eat = true;
        }
        return eat;
    }

    public boolean noWall(Computer computer, Walls wallsHorizon, Walls wallsVertic) {
        boolean accepted = true;
        boolean leftWall = false;
        boolean rightWall = false;
        boolean upWall = false;
        boolean bottomWall = false;

        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String x = decimalFormat.format(pos.x);
        String y = decimalFormat.format(pos.y);
        float beanX = Float.parseFloat(x);
        float beanY = Float.parseFloat(y);
        x = decimalFormat.format(computer.pos.x);
        y = decimalFormat.format(computer.pos.y);
        float computerX = Float.parseFloat(x);
        float computerY = Float.parseFloat(y);
        //Log.d("computer position", String.valueOf(computer.pos.x) + " " + String.valueOf(computer.pos.y));
        //Log.d("left", String.valueOf(beanX) + " " + String.valueOf(beanY));
        for (Wall w : wallsVertic) {
            x = decimalFormat.format(w.pos.x);
            float wallX = Float.parseFloat(x);
            y = decimalFormat.format(w.pos.y);
            float wallY = Float.parseFloat(y);
            x = decimalFormat.format(beanX - wallX);
            float diffX = Float.parseFloat(x);
            y = decimalFormat.format(beanY - wallY);
            float diffY = Float.parseFloat(y);
            if (diffY == 0.1f && diffX == -0.05f) {
                //Log.d("right", "true");
                rightWall = true;
            }
            if (diffY == 0.1f && diffX == 0.05f) {
                //Log.d("left", "true");
                leftWall = true;
            }
        }
        for (Wall w : wallsHorizon) {
            x = decimalFormat.format(w.pos.x);
            float wallX = Float.parseFloat(x);
            y = decimalFormat.format(w.pos.y);
            float wallY = Float.parseFloat(y);
            x = decimalFormat.format(beanX - wallX);
            float diffX = Float.parseFloat(x);
            y = decimalFormat.format(beanY - wallY);
            float diffY = Float.parseFloat(y);
            //Log.d("wall", String.valueOf(wallX) + " " + String.valueOf(wallY));
            //Log.d("diffY", String.valueOf(diffY));
            if (diffY == -0.1f && diffX == 0.05f) {    //wall is on the bottom
                //Log.d("bottom", "true");
                bottomWall = true;
            }
            if (diffY == 0.1f && diffX == 0.05f) {     //wall is on the top
                //Log.d("top", "true");
                upWall = true;
            }
        }
        if (computerX > beanX && computerY == beanY) {   //bean is in the left of computer
            //Log.d("left", String.valueOf(beanX) + " " + String.valueOf(beanY));
            if (rightWall == true) {
                //Log.d("rightwall", String.valueOf(beanX) + " " + String.valueOf(beanY));
                accepted = false;
            }
        } else if (computerX > beanX && computerY < beanY) {    //bean is in the left bottom of computer
            if (rightWall == true && upWall == true) {
                accepted = false;
            }
        } else if (computerX > beanX && computerY > beanY) {    //bean is in the left up of computer
            if (bottomWall == true && rightWall == true) {
                accepted = false;
            }
        } else if (computerX < beanX && computerY == beanY) {   //bean is in the right of computer
            if (leftWall == true) {
                accepted = false;
            }
        } else if (computerX < beanX && computerY > beanY) {    //bean is in the right top of computer
            if (leftWall == true && bottomWall == true) {
                accepted = false;
            }
        } else if (computerX < beanX && computerY < beanY) {    //bean is in the right bottom of computer
            if (leftWall == true && upWall == true) {
                accepted = false;
            }
        } else if (computerX == pos.x && computerY < beanY) {   //bean is on the bottom of computer
            if (upWall == true) {
                accepted = false;
            }
        } else if (computerX == pos.x && computerY > beanY) {   //bean is on the top of computer
            if (bottomWall == true) {
                accepted = false;
            }
        }
        return accepted;
    }
}
