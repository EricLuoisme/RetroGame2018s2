package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Toast;

class Game {
    private Walls wallsHorizon;
    private Walls wallsVertic;
    private Beans beans;

    Player player;

    Paint player_use;

    public Game() {
        wallsHorizon = Walls.createWallsHorizon();
        wallsVertic = Walls.createWallsVertic();
        beans = Beans.createBeans();
        player = Player.createPlayer();
    }

    public void draw(Canvas canvas, Paint paint) {
        wallsHorizon.drawH(canvas, paint);
        wallsVertic.drawV(canvas, paint);
        beans.draw(canvas, paint);
        player.draw(canvas, paint);
    }

    public void touch(String direction) {

        switch (direction) {
            case "u":
                player.pos.y -= 0.05f;
//                for (Wall w : wallsHorizon) {
//
//                }
                break;

            case "d":
                player.pos.y += 0.05f;
//                for (Wall w : wallsHorizon) {
//                    if (w.pos.y - player.pos.y <= 0){
//                        player.pos.y -= 0.05f;
//                        break;
//                    }
//                }
                break;

            case "l":
                player.pos.x -= 0.05f;
//                for (Wall w : wallsVertic) {
//                    if (w.pos.x + 0.01f - player.pos.x >= 0){
//                        player.pos.x += 0.05f;
//                        break;
//                    }
//                }
                break;

            case "r":
                player.pos.x += 0.05f;
//                for (Wall w : wallsVertic) {
//                    if (w.pos.x - player.pos.x <= 0){
//                        player.pos.x -= 0.05f;
//                        break;
//                    }
//                }
                break;
        }
    }
}
