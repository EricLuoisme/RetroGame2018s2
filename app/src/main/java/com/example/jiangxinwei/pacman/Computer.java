package com.example.jiangxinwei.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;

// The author of this class file is Xinwei Jiang

public class Computer extends Sprite {

    public static final float PWIDTH = (1.0f / 55.0f);

    public static int height;
    public static int width;
    public static float sweepAngle = 270;

    public float startAngle = 45;
    public int timemer = 1;

    public Computer() {
        pos = new Pos(0.95f, 0.1f);
    }

    @Override
    public void draw(Canvas c, Paint p, Bitmap b) {
        int h = c.getHeight();
        int w = c.getWidth();

        float xc = pos.x * w;
        float yc = pos.y * h;


        height = c.getHeight();
        width = c.getWidth();

        // draw body
        p.setAlpha(255);
        p.setColor(Color.RED);
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
            } else if (startAngle == 225) {
                c.drawLine(pos.x * width, pos.y * height, pos.x * width - PWIDTH * width, pos.y * height, p);
            } else {
                c.drawLine(pos.x * width, pos.y * height, pos.x * width, pos.y * height - PWIDTH * width, p);
            }
            timemer += 1;
        }

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

    /*
      The rule for computer move:
      In order to live longer, the computer first should avoid chaser,
      then computer always move the closest bean in order to get higher mark.
    */
    public void step(Float closeX, Float closeY, ArrayList<String> moveAvoidChaser, ArrayList<String> moveAvoidWall) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String x = decimalFormat.format(pos.x);
        String y = decimalFormat.format(pos.y);
        pos.x = Float.parseFloat(x);
        pos.y = Float.parseFloat(y);
        x = decimalFormat.format(closeX);
        y = decimalFormat.format(closeY);
        closeX = Float.parseFloat(x);
        closeY = Float.parseFloat(y);
        if (closeX < pos.x && closeY == pos.y) {   //in the left
            if (moveAvoidChaser.contains("left") && moveAvoidWall.contains("left")) {    //left is a valid move
                pos.x = pos.x - 0.1f;
                startAngle = 225;
            } else if (moveAvoidChaser.contains("up") && moveAvoidWall.contains("up")) {
                pos.y = pos.y - 0.2f;
                startAngle = 315;
            } else if (moveAvoidChaser.contains("down") && moveAvoidWall.contains("down")) {
                pos.y = pos.y + 0.2f;
                startAngle = 135;
            } else if (moveAvoidChaser.contains("right") && moveAvoidWall.contains("right")) {   //this is the worst case
                pos.x = pos.x + 0.1f;
                startAngle = 45;
            }
        } else if (closeX < pos.x && closeY < pos.y) {    //in the left top
            //these two are better choices of direction
            if (moveAvoidChaser.contains("left") && moveAvoidWall.contains("left")) {
                pos.x = pos.x - 0.1f;
                startAngle = 225;
            } else if (moveAvoidChaser.contains("up") && moveAvoidWall.contains("up")) {
                pos.y = pos.y - 0.2f;
                startAngle = 315;
            }
            //these two are worse choices of direction
            else if (moveAvoidChaser.contains("down") && moveAvoidWall.contains("down")) {
                pos.y = pos.y + 0.2f;
                startAngle = 135;
            } else if (moveAvoidChaser.contains("right") && moveAvoidWall.contains("right")) {
                pos.x = pos.x + 0.1f;
                startAngle = 45;
            }
        } else if (closeX < pos.x && closeY > pos.y) {    //in the left bottom
            //these two are better choices of direction
            if (moveAvoidChaser.contains("left") && moveAvoidWall.contains("left")) {
                pos.x = pos.x - 0.1f;
                startAngle = 225;
            } else if (moveAvoidChaser.contains("down") && moveAvoidWall.contains("down")) {
                pos.y = pos.y + 0.2f;
                startAngle = 135;
            }
            //these two are worse choices of direction
            else if (moveAvoidChaser.contains("up") && moveAvoidWall.contains("up")) {
                pos.y = pos.y - 0.2f;
                startAngle = 315;
            } else if (moveAvoidChaser.contains("right") && moveAvoidWall.contains("right")) {
                pos.x = pos.x + 0.1f;
                startAngle = 45;
            }
        } else if (closeX > pos.x && closeY == pos.y) {   //in the right
            if (moveAvoidChaser.contains("right") && moveAvoidWall.contains("right")) {
                pos.x = pos.x + 0.1f;
                startAngle = 45;
            } else if (moveAvoidChaser.contains("up") && moveAvoidWall.contains("up")) {
                pos.y = pos.y - 0.2f;
                startAngle = 315;
            } else if (moveAvoidChaser.contains("down") && moveAvoidWall.contains("down")) {
                pos.y = pos.y + 0.2f;
                startAngle = 135;
            } else if (moveAvoidChaser.contains("left") && moveAvoidWall.contains("left")) { //the worst choice
                pos.x = pos.x + 0.1f;
                startAngle = 45;
            }
        } else if (closeX > pos.x && closeY < pos.y) {    //in the right top
            //these two are better choices of direction
            if (moveAvoidChaser.contains("right") && moveAvoidWall.contains("right")) {
                pos.x = pos.x + 0.1f;
                startAngle = 45;
            } else if (moveAvoidChaser.contains("up") && moveAvoidWall.contains("up")) {
                pos.y = pos.y - 0.2f;
                startAngle = 315;
            }
            //these two are worse choices of direction
            else if (moveAvoidChaser.contains("down") && moveAvoidWall.contains("down")) {
                pos.y = pos.y + 0.2f;
                startAngle = 135;
            } else if (moveAvoidChaser.contains("left") && moveAvoidWall.contains("left")) {
                pos.x = pos.x - 0.1f;
                startAngle = 225;
            }
            //Log.d("right top", String.valueOf(closeX) + " " + String.valueOf(closeY));
        } else if (closeX > pos.x && closeY > pos.y) {    //in the right bottom
            //these two are better choices of direction
            if (moveAvoidChaser.contains("right") && moveAvoidWall.contains("right")) {
                pos.x = pos.x + 0.1f;
                startAngle = 45;
            } else if (moveAvoidChaser.contains("down") && moveAvoidWall.contains("down")) {
                pos.y = pos.y + 0.2f;
                startAngle = 135;
            }
            //these two are worse choices of direction
            else if (moveAvoidChaser.contains("up") && moveAvoidWall.contains("up")) {
                pos.y = pos.y - 0.2f;
                startAngle = 315;
            } else if (moveAvoidChaser.contains("left") && moveAvoidWall.contains("left")) {
                pos.x = pos.x - 0.1f;
                startAngle = 225;
            }
        } else if (closeX == pos.x && closeY > pos.y) {   //on the bottom
            if (moveAvoidChaser.contains("down") && moveAvoidWall.contains("down")) {
                pos.y = pos.y + 0.2f;
                startAngle = 135;
            } else if (moveAvoidChaser.contains("left") && moveAvoidWall.contains("left")) {
                pos.x = pos.x - 0.1f;
                startAngle = 225;
            } else if (moveAvoidChaser.contains("right") && moveAvoidWall.contains("right")) {
                pos.x = pos.x + 0.1f;
                startAngle = 45;
            } else if (moveAvoidChaser.contains("up") && moveAvoidWall.contains("up")) { //the worst choice
                pos.y = pos.y - 0.2f;
                startAngle = 315;
            }
        } else if (closeX == pos.x && closeY < pos.y) {   //on the top
            if (moveAvoidChaser.contains("up") && moveAvoidWall.contains("up")) {
                pos.y = pos.y - 0.2f;
                startAngle = 315;
            } else if (moveAvoidChaser.contains("left") && moveAvoidWall.contains("left")) {
                pos.x = pos.x - 0.1f;
                startAngle = 225;
            } else if (moveAvoidChaser.contains("right") && moveAvoidWall.contains("right")) {
                pos.x = pos.x + 0.1f;
                startAngle = 45;
            } else if (moveAvoidChaser.contains("down") && moveAvoidWall.contains("down")) { //the worst choice
                pos.y = pos.y + 0.2f;
                startAngle = 135;
            }
        }
    }

    public ArrayList<String> avoidWall(Walls wallsHorizon, Walls wallsVertic) {
        ArrayList<String> validMove = new ArrayList<String>();
        validMove.add("left");
        validMove.add("right");
        validMove.add("up");
        validMove.add("down");
        for (Wall w : wallsHorizon) {   //horizon wall in on the top or bottom of the computer
            DecimalFormat decimalFormat = new DecimalFormat(".00");
            String x = decimalFormat.format(pos.x);
            float computerX = Float.parseFloat(x);
            x = decimalFormat.format(w.pos.x);
            float wallX = Float.parseFloat(x);
            String y = decimalFormat.format(pos.y);
            float computerY = Float.parseFloat(y);
            y = decimalFormat.format(w.pos.y);
            float wallY = Float.parseFloat(y);
            x = decimalFormat.format(computerX - wallX);
            float diffX = Float.parseFloat(x);
            y = decimalFormat.format(computerY - wallY);
            float diffY = Float.parseFloat(y);
            if ((diffY == -0.1f && diffX == 0.05f) || computerY + 0.1 >= 0.999f) {    //wall is on the bottom
                validMove.remove("down");
            }
            if ((diffY == 0.1f && diffX == 0.05f) || computerY - 0.1 <= 0.001f) {  //wall is on the top
                validMove.remove("up");
            }
        }
        for (Wall w : wallsVertic) {   //horizon wall in on the top or bottom of the computer
            DecimalFormat decimalFormat = new DecimalFormat(".00");
            String x = decimalFormat.format(pos.x);
            float computerX = Float.parseFloat(x);
            x = decimalFormat.format(w.pos.x);
            float wallX = Float.parseFloat(x);
            String y = decimalFormat.format(pos.y);
            float computerY = Float.parseFloat(y);
            y = decimalFormat.format(w.pos.y);
            float wallY = Float.parseFloat(y);
            x = decimalFormat.format(computerX - wallX);
            float diffX = Float.parseFloat(x);
            y = decimalFormat.format(computerY - wallY);
            float diffY = Float.parseFloat(y);
            if ((diffX == -0.05f && diffY == 0.1f) || computerX + 0.05f >= 0.999f) {    //wall is on the right
                validMove.remove("right");
            }
            if ((diffX == 0.05f && diffY == 0.1f) || computerY - 0.05f <= 0.201f) {   //wall is on the left
                validMove.remove("left");
            }
        }
        return validMove;
    }

    public ArrayList<String> avoidChaser(Chasers chasers) {
        ArrayList<String> validMove = new ArrayList<String>();
        validMove.add("left");
        validMove.add("right");
        validMove.add("up");
        validMove.add("down");
        for (Chaser c : chasers) {
            if (c.pos.distance(pos) <= 0.3f) {    //computer should not move to the direction of that chaser
                DecimalFormat decimalFormat = new DecimalFormat(".00");
                String x = decimalFormat.format(pos.x);
                String y = decimalFormat.format(pos.y);
                float computerX = Float.parseFloat(x);
                float computerY = Float.parseFloat(y);
                x = decimalFormat.format(c.pos.x);
                y = decimalFormat.format(c.pos.y);
                float chaserX = Float.parseFloat(x);
                float chaserY = Float.parseFloat(y);
                if (chaserX < computerX && chaserY == computerY) {   //in the left
                    validMove.remove("left");
                } else if (chaserX < computerX && chaserY < computerY) {    //in the left top
                    validMove.remove("left");
                    validMove.remove("up");
                } else if (chaserX < computerX && chaserY > computerY) {    //in the left bottom
                    validMove.remove("left");
                    validMove.remove("down");
                } else if (chaserX > computerX && chaserY == computerY) {   //in the right
                    validMove.remove("right");
                } else if (chaserX > computerX && chaserY < computerY) {    //in the right top
                    validMove.remove("right");
                    validMove.remove("up");
                } else if (chaserX > computerX && chaserY > computerY) {    //in the right bottom
                    validMove.remove("right");
                    validMove.remove("down");
                } else if (chaserX == computerX && chaserY > computerY) {   //on the bottom
                    validMove.remove("down");
                } else if (chaserX == computerX && chaserY < computerY) {   //on the top
                    validMove.remove("up");
                }
            }
        }
        return validMove;
    }
}
