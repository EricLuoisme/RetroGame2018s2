package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;

class Game {
    private Walls wallsHorizon;
    private Walls wallsVertic;
    private Beans beans;
    private Chasers chasers;
    private Computer computer;
    private boolean computerHitByChaser = false;

    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;


    public Game()
    {
        wallsHorizon = Walls.createWallsHorizon();
        wallsVertic = Walls.createWallsVertic();
        beans = Beans.createBeans();
        chasers = new Chasers();
        computer =  new Computer();
    }

    public void draw(Canvas canvas, Paint paint) {
        wallsHorizon.drawH(canvas, paint);
        wallsVertic.drawV(canvas, paint);
        beans.draw(canvas, paint);
        chasers.draw(canvas, paint);
        computer.draw(canvas, paint);
    }

    public void step(){
        //computer.step();
        chasers.step();

        if(!computerHitByChaser) {   //computer can move if it has not eaten by chaser
            //Find the closest bean
            Float closeDis = 2f;
            Float dis;
            Float closeX = 0f;
            Float closeY = 0f;
            for (Bean b : beans) {
                dis = computer.pos.distance(b.pos);
                if (dis < closeDis) {
                    closeDis = dis;
                    closeX = b.pos.x;
                    closeY = b.pos.y;
                }
            }
            //Log.d("close", String.valueOf(closeX) + " " + String.valueOf(closeY));

            ArrayList<String> moveAvoidChaser = avoidChaser();   //store the valid move for computer to avoid chaser
            //Log.d("move", String.valueOf(moveAvoidChaser.size()));

            //Let computer move;
            computer.step(closeX, closeY, moveAvoidChaser);

            //Remove bean which has been eaten
            beans.removeEat(computer);
            //Log.d("size", String.valueOf(beans.size()));
        }
    }

    public ArrayList<String> avoidChaser(){      //the computer should avoid chasers
        ArrayList<String> validMove = new ArrayList<String>();
        validMove.add("left");
        validMove.add("right");
        validMove.add("up");
        validMove.add("down");
        //Log.d("before move", String.valueOf(validMove.size()));
        for(Chaser c: chasers){
            if(c.pos.distance(computer.pos) <= 0.2f){    //computer should not move to the direction of that chaser
                DecimalFormat decimalFormat=new DecimalFormat(".00");
                String x = decimalFormat.format(computer.pos.x);
                String y = decimalFormat.format(computer.pos.y);
                float computerX = Float.parseFloat(x);
                float computerY = Float.parseFloat(y);
                x = decimalFormat.format(c.pos.x);
                y = decimalFormat.format(c.pos.y);
                float chaserX = Float.parseFloat(x);
                float chaserY = Float.parseFloat(y);
                if(chaserX < computerX && chaserY == computerY){   //in the left
                    validMove.remove("left");
                }
                else if(chaserX < computerX && chaserY < computerY){    //in the left top
                    validMove.remove("left");
                    validMove.remove("up");
                }
                else if(chaserX < computerX && chaserY > computerY){    //in the left bottom
                    validMove.remove("left");
                    validMove.remove("down");
                }
                else if(chaserX > computerX && chaserY == computerY){   //in the right
                    validMove.remove("right");
                }
                else if(chaserX > computerX && chaserY < computerY){    //in the right top
                    validMove.remove("right");
                    validMove.remove("up");
                }
                else if(chaserX > computerX && chaserY > computerY){    //in the right bottom
                    validMove.remove("right");
                    validMove.remove("down");
                }
                else if(chaserX == computerX && chaserY > computerY){   //on the bottom
                    validMove.remove("down");
                }
                else if(chaserX ==computerX && chaserY < computerY){   //on the top
                    validMove.remove("up");
                }
            }
        }
        return validMove;
    }
}
