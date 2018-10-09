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
    private static final float DISWALLX = 0.05f;
    private static final float DISWALLY = 0.1f;

    public static final float PWIDTH = (1.0f / 55.0f);

//    private static final String[] order = {"Left", "Up", "Right", "Down"};

    Random random = new Random();

    public void step() {

        if (this.size() < CHASERNUM) {
            this.add(new Chaser(new Pos(0.55f, 0.5f)));
        } else {
            for(Chaser chaser: this){
                ArrayList<String> validMoves = validMove(chaser);
                //System.out.println(validMoves + Integer.toString(validMoves.size()));

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
        //Log.d("chasers", String.valueOf(this.size()));
        for (Chaser c : this) c.draw(canvas, paint, b);
    }

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
        //if(outBoundary(after) || hitWalls(current, after)){
            position.remove("Left");
            //System.out.println("Remove Left");
        }

        after = new Pos(chaser.pos.x + CHASERSTEPX / 2, chaser.pos.y);
        //if(outBoundary(p) || hitWalls(p)){
        //if(outBoundary(after) || hitWalls(current, after)){
        if(outBoundary(after) || hitVerticWalls(after)){
            position.remove("Right");
            //System.out.println("Remove Right");
        }

        after = new Pos(chaser.pos.x, chaser.pos.y - CHASERSTEPY / 2);
        //if(outBoundary(p) || hitWalls(p)){
        //if(outBoundary(after) || hitWalls(current, after)){
        if(outBoundary(after) || hitHorizonWalls(after)){
            position.remove("Up");
            //System.out.println("Remove Up");
        }

        after = new Pos(chaser.pos.x, chaser.pos.y + CHASERSTEPY / 2);
//        if(outBoundary(p) || hitWalls(p)){
        //if(outBoundary(after) || hitWalls(current, after)){
        if(outBoundary(after) || hitHorizonWalls(after)){
            position.remove("Down");
            //System.out.println("Remove Down");
        }

        return position;
    }


    private boolean outBoundary(Pos p){
        if(p.x < CHASERMINX || p.x > CHASERMAXX){
            return true;
        }
        if(p.y < CHASERMINY || p.y > CHASERMAXY){
            return true;
        }
        return false;
    }

    private boolean hitVerticWalls(Pos p){
        for(Wall wall: Game.wallsVertic){
            if(Math.abs(p.x - wall.pos.x) <= 0.01 && Math.abs(p.y - wall.pos.y - 0.1) <= 0.01 ){
                return true;
            }
        }
        return false;
    }

    private boolean hitHorizonWalls(Pos p){
        for(Wall wall: Game.wallsHorizon){
            if(Math.abs(p.y - wall.pos.y) <=0.01 && Math.abs(p.x - wall.pos.x - 0.05) <= 0.01){
                return true;
            }
        }
        return false;
    }
}