package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

        player_use = new Paint(Color.GREEN);
        player.draw(canvas, player_use);
    }

    public void touch(String direction) {
        switch (direction){
            case "u":
                player.pos.y -= 0.05f;
                break;
            case "d":
                player.pos.y += 0.05f;
                break;
            case "l":
                player.pos.x -= 0.05f;
                break;
            case "r":
                player.pos.x += 0.05f;
                break;
        }
    }
}
