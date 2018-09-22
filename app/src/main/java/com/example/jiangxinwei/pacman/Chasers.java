package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Chasers extends ArrayList<Chaser> {

    private static final float CHASERSTEPX = 0.1f;
    private static final float CHASERSTEPY = 0.2f;
    private static final int CHASERNUM = 4;
    private static final float CHASERMINX = 0.05f;
    private static final float CHASERMAXX = 0.95f;
    private static final float CHASERMINY = 0.1f;
    private static final float CHASERMAXY = 0.9f;
    private static final float DISWALLX = 0.05f;
    private static final float DISWALLY = 0.1f;


    Random random = new Random();

    Walls walls = new Walls();

    public void step() {

        System.out.println(walls.createWallsHorizon().size());
        if (this.size() < CHASERNUM) {
            this.add(new Chaser(new Pos(0.55f, 0.5f)));
        } else {
            for(Chaser chaser: this){
                ArrayList<String> validMoves = validMove(chaser, walls);
                System.out.println(validMoves);
                int num = validMoves.size();
                int index = random.nextInt(num);
                switch (validMoves.get(index)){
                    case "Left":
                        chaser.pos.x = chaser.pos.x - CHASERSTEPX;
                        Log.d("Move", "Left");
                        break;

                    case "Right":
                        chaser.pos.x = chaser.pos.x + CHASERSTEPX;
                        Log.d("Move", "Right");
                        break;

                    case "Up":
                        chaser.pos.y = chaser.pos.y - CHASERSTEPY;
                        Log.d("Move", "Up");
                        break;

                    case "Down":
                        chaser.pos.y = chaser.pos.y + CHASERSTEPY;
                        Log.d("Move", "Down");
                }
            }
        }

    }

    public void draw(Canvas canvas, Paint paint) {
        Log.d("chasers", String.valueOf(this.size()));
        for (Chaser c : this) c.draw(canvas, paint);
    }

    private ArrayList<String> validMove(Chaser chaser, Walls walls){
        ArrayList<String> position = new ArrayList<String>();
        position.add("Left");
        position.add("Right");
        position.add("Up");
        position.add("Down");

        Pos p;
        p = new Pos(chaser.pos.x - CHASERSTEPX, chaser.pos.y);
        if(outBoundary(p) || hitWalls(p)){
            position.remove("Left");
            //System.out.println("Remove Left");
        }

        p = new Pos(chaser.pos.x + CHASERSTEPX, chaser.pos.y);
        if(outBoundary(p) || hitWalls(p)){
            position.remove("Right");
            //System.out.println("Remove Right");
        }

        p = new Pos(chaser.pos.x, chaser.pos.y - CHASERSTEPY);
        if(outBoundary(p) || hitWalls(p)){
            position.remove("Up");
            //System.out.println("Remove Up");
        }

        p = new Pos(chaser.pos.x, chaser.pos.y + CHASERSTEPY);
        if(outBoundary(p) || hitWalls(p)){
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

    private boolean hitWalls(Pos p){
        for(Wall wall: walls.createWallsHorizon()){
//            if(Math.abs(p.x - wall.pos.x) <= DISWALLX && p.y == wall.pos.y + DISWALLY){
//                System.out.println("Horizon X: " + Math.abs(p.x - wall.pos.x) +"   "+ p.y +"   "+ (wall.pos.y + DISWALLY));
//                return true;
//            }
            if(Math.abs(p.y - wall.pos.y) <= DISWALLY && p.x == wall.pos.x + DISWALLX){
                //System.out.println("Horizon Y: " + Math.abs(p.y - wall.pos.y) +  "   "+p.x + "   "+ (wall.pos.x + DISWALLX));
                return true;
            }
        }

        for(Wall wall: walls.createWallsVertic()){
            if(Math.abs(p.x - wall.pos.x) <= DISWALLX && p.y == wall.pos.y + DISWALLY){
                //System.out.println("Vertic X: " + Math.abs(p.x - wall.pos.x) + "   "+ p.y +"   "+ (wall.pos.y + DISWALLY));
                return true;
            }
//            if(Math.abs(p.y - wall.pos.y) <= DISWALLY && p.x == wall.pos.x + DISWALLX){
//                System.out.println("Vertic Y: " + Math.abs(p.y - wall.pos.y) +"   "+ p.x +"   "+ (wall.pos.x + DISWALLX));
//                return true;
//            }
        }
        return false;
    }
}

/*
//            Iterator<Chaser> ch = this.iterator();
//            while (ch.hasNext()) {
//                Chaser c = ch.next();
//                c.pos.x += (random.nextInt(2)-1)*0.1f;
//                c.pos.y += (random.nextInt(2)-1)*0.1f;
//            }
            for (Chaser c : this) {
                    int xi = random.nextInt(3) - 1;
                    c.pos.x += (xi * CHASERSTEPX);
                    int yi = 0;
                    if (xi == 0) {
                    yi = random.nextInt(3) - 1;
                    while (yi == 0) {
                    yi = random.nextInt(3) - 1;
                    }
                    c.pos.y += (yi * CHASERSTEPY);
                    }

                    if (c.pos.x < CHASERMINX) {
        c.pos.x = CHASERMINX;
        yi = random.nextInt(3) - 1;
        while (yi == 0) {
        yi = random.nextInt(3) - 1;
        }
        c.pos.y += (yi * CHASERSTEPY);
        Log.d("signal", "min x");
        } else if (c.pos.x > CHASERMAXX) {
        c.pos.x = CHASERMAXX;
        yi = random.nextInt(3) - 1;
        while (yi == 0) {
        yi = random.nextInt(3) - 1;
        }
        c.pos.y += (yi * CHASERSTEPY);
        Log.d("signal", "max x");
        }

        if (c.pos.y < CHASERMINY) {
        c.pos.y = CHASERMINY;
        xi = random.nextInt(3) - 1;
        while (xi == 0) {
        xi = random.nextInt(3) - 1;
        }
        c.pos.x += (xi * CHASERSTEPX);
        Log.d("signal", "min y");
        } else if (c.pos.y > CHASERMAXY) {
        c.pos.y = CHASERMAXY;
        xi = random.nextInt(3) - 1;
        while (xi == 0) {
        xi = random.nextInt(3) - 1;
        }
        c.pos.x += (xi * CHASERSTEPY);
        Log.d("signal", "max y");
        }

        for (Wall w : walls) {
        if (xi != 0) {
        if (xi == 1 && (w.pos.x - c.pos.x) <= DISWALLX && w.pos.y + DISWALLY == c.pos.y){
        c.pos.x = w.pos.x - DISWALLX;
        while (yi == 0) {
        yi = random.nextInt(3) - 1;
        }
        c.pos.y += (yi * CHASERSTEPY);
        Log.d("signal", "wall x 1");
        }else if (xi == -1 && (c.pos.x - w.pos.x) <= DISWALLX && w.pos.y + DISWALLY == c.pos.y) {
        c.pos.x = w.pos.x + DISWALLX;
        while (yi == 0) {
        yi = random.nextInt(3) - 1;
        }
        c.pos.y += (yi * CHASERSTEPY);
        Log.d("signal", "wall x -1");
        }
        } else if (yi != 0) {
        if (yi == 1 && (w.pos.y - c.pos.y) <= DISWALLY && w.pos.x + DISWALLX == c.pos.x){
        c.pos.y = w.pos.y + DISWALLY;
        while (xi == 0) {
        xi = random.nextInt(3) - 1;
        }
        c.pos.x += (xi * CHASERSTEPY);
        Log.d("signal", "wall y 1");
        }else if (yi == -1 && (c.pos.y - w.pos.y) <= DISWALLY && w.pos.x + DISWALLX == c.pos.x) {
        c.pos.y = w.pos.y - DISWALLY;
        while (xi == 0) {
        xi = random.nextInt(3) - 1;
        }
        c.pos.x += (xi * CHASERSTEPY);
        Log.d("signal", "wall y -1");
        }
        }
        }
        }
        }
        */
