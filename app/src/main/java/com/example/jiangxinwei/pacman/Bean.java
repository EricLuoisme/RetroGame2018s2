package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.text.DecimalFormat;

public class Bean {

    public float BEANWIDTH = 20;

    Pos pos;
    public Bean(Pos p) {
        pos = new Pos(p);
    }

    public void draw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();
        p.setColor(Color.YELLOW);
        c.drawCircle(pos.x * w, pos.y * h, 17, p);
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
    
    // This function will be called when computer needs to find the closest bean 
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
                rightWall = true;
            }
            if (diffY == 0.1f && diffX == 0.05f) {
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
            if (diffY == -0.1f && diffX == 0.05f) {    //wall is on the bottom
                bottomWall = true;
            }
            if (diffY == 0.1f && diffX == 0.05f) {     //wall is on the top
                upWall = true;
            }
        }
        if (computerX > beanX && computerY == beanY) {   //bean is in the left of computer
            if (rightWall == true) {
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
