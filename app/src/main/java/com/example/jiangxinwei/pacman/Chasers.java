package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Chasers extends ArrayList<Chaser>{

    private static final float CHASERSTEPX = 0.1f;
    private static final float CHASERSTEPY = 0.2f;
    private static final int CHASERNUM = 4;
    private static final float CHASERMINX = 0.05f;
    private static final float CHASERMAXX = 0.95f;
    private static final float CHASERMINY = 0.1f;
    private static final float CHASERMAXY = 0.9f;


    Random random = new Random();

    public void step(){

        if(this.size() < CHASERNUM){
            this.add(new Chaser(new Pos(0.5f,0.5f)));
        }else {
//            Iterator<Chaser> ch = this.iterator();
//            while (ch.hasNext()) {
//                Chaser c = ch.next();
//                c.pos.x += (random.nextInt(2)-1)*0.1f;
//                c.pos.y += (random.nextInt(2)-1)*0.1f;
//            }
            for(Chaser c: this){
                int xi = random.nextInt(3) - 1;
                c.pos.x += (xi * CHASERSTEPX);
                if(xi == 0){
                    int yi = random.nextInt(3) - 1;
                    while(yi == 0){
                        yi = random.nextInt(3) - 1;
                    }
                    c.pos.y += (yi * CHASERSTEPY);
                }

                if(c.pos.x < CHASERMINX){
                    c.pos.x = CHASERMINX;
                    int yi = random.nextInt(3) - 1;
                    while(yi == 0){
                        yi = random.nextInt(3) - 1;
                    }
                    c.pos.y += (yi * CHASERSTEPY);
                }else if(c.pos.x > CHASERMAXX){
                    c.pos.x = CHASERMAXX;
                    int yi = random.nextInt(3) - 1;
                    while(yi == 0){
                        yi = random.nextInt(3) - 1;
                    }
                    c.pos.y += (yi * CHASERSTEPY);
                }

                if(c.pos.y < CHASERMINY){
                    c.pos.y = CHASERMINY;
                    xi = random.nextInt(3) - 1;
                    while(xi == 0){
                        xi = random.nextInt(3) - 1;
                    }
                    c.pos.x += (xi * CHASERSTEPX);
                }else if(c.pos.y > CHASERMAXY){
                    c.pos.y = CHASERMAXY;
                    xi = random.nextInt(3) - 1;
                    while(xi == 0){
                        xi = random.nextInt(3) - 1;
                    }
                    c.pos.y += (xi * CHASERSTEPY);
                }
            }
        }

    }

    public void draw(Canvas canvas, Paint paint){
        Log.d("chasers", String.valueOf(this.size()));
        for (Chaser c: this) c.draw(canvas, paint);
    }
}
