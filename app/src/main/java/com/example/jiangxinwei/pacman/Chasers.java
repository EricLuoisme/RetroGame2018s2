package com.example.jiangxinwei.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Chasers extends ArrayList<Chaser> {

    private static final float CHASERSTEPX = 0.1f;
    private static final float CHASERSTEPY = 0.2f;
    private static final int CHASERNUM = 2;
    private static final float CHASERMINX = 0.25f;
    private static final float CHASERMAXX = 0.95f;
    private static final float CHASERMINY = 0.1f;
    private static final float CHASERMAXY = 0.9f;

    Random random = new Random();

    /**
     * This step() helps the chasers to take a random walk every step
     */
    public void step() {

        if (this.size() < CHASERNUM) {
            this.add(new Chaser(new Pos(0.55f, 0.5f)));
        } else {
            for(Chaser chaser: this){
                ArrayList<String> validMoves = validMove(chaser);

                int num = validMoves.size();
                int index = random.nextInt(num);
                switch (validMoves.get(index)){
                    case "Left":
                        chaser.pos.x = chaser.pos.x - CHASERSTEPX;
                        //Log.d("Move", "Left");
                        break;

                    case "Right":
                        chaser.pos.x = chaser.pos.x + CHASERSTEPX;
                        //Log.d("Move", "Right");
                        break;

                    case "Up":
                        chaser.pos.y = chaser.pos.y - CHASERSTEPY;
                        //Log.d("Move", "Up");
                        break;

                    case "Down":
                        chaser.pos.y = chaser.pos.y + CHASERSTEPY;
                        //Log.d("Move", "Down");
                }
            }
        }

    }

    public void draw(Canvas canvas, Paint paint, Bitmap b) {
        for (Chaser c : this) c.draw(canvas, paint, b);
    }

    /**
     * This helps to judge the next step of each chaser
     * it will first sign the four direction as possible move
     * and judge whether they are next valid move
     * it will return the list of the next valid move
     * @param chaser
     * @return A list of the next valid move of each chaser
     */
    private ArrayList<String> validMove(Chaser chaser){
        ArrayList<String> position = new ArrayList<>();
        position.add("Left");
        position.add("Right");
        position.add("Up");
        position.add("Down");

        Pos current = new Pos(chaser.pos.x, chaser.pos.y);
        Pos after;
        after = new Pos(chaser.pos.x - CHASERSTEPX / 2, chaser.pos.y);
        if(outBoundary(after) || hitVerticWalls(after)){
            position.remove("Left");
        }

        after = new Pos(chaser.pos.x + CHASERSTEPX / 2, chaser.pos.y);
        if(outBoundary(after) || hitVerticWalls(after)){
            position.remove("Right");
        }

        after = new Pos(chaser.pos.x, chaser.pos.y - CHASERSTEPY / 2);
        if(outBoundary(after) || hitHorizonWalls(after)){
            position.remove("Up");
        }

        after = new Pos(chaser.pos.x, chaser.pos.y + CHASERSTEPY / 2);
        if(outBoundary(after) || hitHorizonWalls(after)){
            position.remove("Down");
        }

        return position;
    }


    /**
     * The chaser gives the next possible move position to this function
     * it return whether the position is out boundary
     * @param p
     * @return true/false
     */
    private boolean outBoundary(Pos p){
        if(p.x < CHASERMINX || p.x > CHASERMAXX){
            return true;
        }
        if(p.y < CHASERMINY || p.y > CHASERMAXY){
            return true;
        }
        return false;
    }

    /**
     * The chaser gives the next possible up/down move position to this function
     * it return whether there is a Vertical wall between the current position and the next possible position
     * @param p
     * @return true/false
     */
    private boolean hitVerticWalls(Pos p){
        for(Wall wall: Game.wallsVertic){
            if(Math.abs(p.x - wall.pos.x) <= 0.01 && Math.abs(p.y - wall.pos.y - 0.1) <= 0.01 ){
                return true;
            }
        }
        return false;
    }

    /**
     * he chaser gives the next possible left/right move position to this function
     * it return whether there is a Horizon wall between the current position and the next possible position
     * @param p
     * @return true/false
     */
    private boolean hitHorizonWalls(Pos p){
        for(Wall wall: Game.wallsHorizon){
            if(Math.abs(p.y - wall.pos.y) <=0.01 && Math.abs(p.x - wall.pos.x - 0.05) <= 0.01){
                return true;
            }
        }
        return false;
    }
}