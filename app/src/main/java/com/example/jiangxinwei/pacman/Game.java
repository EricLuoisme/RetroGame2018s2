package com.example.jiangxinwei.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// The author of this class file is Xinwei Jiang, Wenjing Xue, Yikai Luo
// If not specifically mention, the author is three members

class Game {

    public static Walls wallsHorizon;
    public static Walls wallsVertic;
    public static final int SCOREINCREASE = 10;  //eat one bean, score increases 10

    private Beans beans;
    private Chasers chasers;
    private Computer computer;
    private Player player;
    private boolean computerHitByChaser = false;
    private boolean playerHitByChaser = false;
    private String computerScore = "0";
    public static String playerScore = "0";
    private char removedBeans; // 'c' is by computer, 'p' is by player, 'e' is still exist
    public int adder = 0;


    public Game() {
        wallsHorizon = Walls.createWallsHorizon();
        wallsVertic = Walls.createWallsVertic();
        beans = Beans.createBeans();
        chasers = new Chasers();
        player = Player.createPlayer();
        computer = new Computer();
    }

    public void draw(Canvas canvas, Paint paint, List<Bitmap> images) {
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        paint.setTextSize(50.0f);
        paint.setColor(Color.rgb(255,153,18));
        canvas.drawText(computerScore, 0.07f * w, 0.2f * h, paint);
        canvas.drawText(playerScore, 0.07f * w, 0.7f * h, paint);
        wallsHorizon.drawH(canvas, paint);
        wallsVertic.drawV(canvas, paint);
        beans.draw(canvas, paint);
        chasers.draw(canvas, paint, images.get(0));
        player.draw(canvas, paint, images.get(1));
        if (!computerHitByChaser) {
            computer.draw(canvas, paint, images.get(2));
        }

    }

    public void step() {
        //computer.step();
        chasers.step();

        if (!computerHitByChaser) {   //computer can move if it has not eaten by chaser
            computerHitByChaser = computer.hitByChaser(chasers);
        }
        playerHitByChaser = player.hitByChaser(chasers);
    }

    // The author of this function is Xinwei Jiang
    /*
       this function will be call when player moves.
       for the fairness, player moves one step computer moves one step
     */
    public void computerStep() {
        if (!computerHitByChaser) {   //computer can move if it has not eaten by chaser
            //Find the closest bean
            Float smallestSteps = 50f;
            Float steps;
            Float closeX = 0f;
            Float closeY = 0f;
            boolean accepted;
            for (Bean b : beans) {
                steps = computer.pos.stepCount(b.pos);
                accepted = b.noWall(computer, wallsHorizon, wallsVertic);
                if (steps < smallestSteps && accepted) {
                    smallestSteps = steps;
                    closeX = b.pos.x;
                    closeY = b.pos.y;
                }
            }

            //store the valid move for computer to avoid chaser
            ArrayList<String> moveAvoidChaser = computer.avoidChaser(chasers);
            ArrayList<String> moveAvoidWall = computer.avoidWall(wallsHorizon, wallsVertic);

            //Let computer move;
            computer.step(closeX, closeY, moveAvoidChaser, moveAvoidWall);


            removedBeans = beans.removeEatJudge(computer);
            if (removedBeans == 'c') {
                computerScore = String.valueOf(Integer.parseInt(computerScore) + SCOREINCREASE);
                computer.timemer++;
            }
        }
    }

    // The author of this function is Yikai Luo
    /**
     * responds for pressing moving button
     * -input only legal with "u", "d", "l", "r"
     *
     * @param direction
     */
    public void touch(String direction) {
        Log.d("Player pos", "y " + player.pos.y + "  x " + player.pos.x);
        switch (direction) {
            case "u":
                if (!hitBoundary(player.pos, 'u', player.STEPY) &&
                        !hitWalls(player.pos, 'u', player.PWIDTH, player.STEPY)) {
                    player.pos.y -= player.STEPY;
                    player.startAngle = 315;
                }
                if (!playerHitByChaser) {
                    removedBeans = beans.removeEatJudge(player);
                    if (removedBeans == 'p') {
                        playerScore = String.valueOf(Integer.parseInt(playerScore) + SCOREINCREASE);
                        player.timemer += 1;
                    }
                }
                computerStep();
                break;
            case "d":
                if (!hitBoundary(player.pos, 'd', player.STEPY) &&
                        !hitWalls(player.pos, 'd', player.PWIDTH, player.STEPY)) {
                    player.pos.y += player.STEPY;
                    player.startAngle = 135;
                }
                if (!playerHitByChaser) {
                    removedBeans = beans.removeEatJudge(player);
                    if (removedBeans == 'p') {
                        playerScore = String.valueOf(Integer.parseInt(playerScore) + SCOREINCREASE);
                        player.timemer += 1;
                    }
                }
                computerStep();
                break;
            case "l":
                if (!hitBoundary(player.pos, 'l', player.STEPX) &&
                        !hitWalls(player.pos, 'l', player.PWIDTH, player.STEPX)) {
                    player.pos.x -= player.STEPX;
                    player.startAngle = 225;
                }
                if (!playerHitByChaser) {
                    removedBeans = beans.removeEatJudge(player);
                    if (removedBeans == 'p') {
                        playerScore = String.valueOf(Integer.parseInt(playerScore) + SCOREINCREASE);
                        player.timemer += 1;
                    }
                }
                computerStep();
                break;
            case "r":
                if (!hitBoundary(player.pos, 'r', player.STEPX) &&
                        !hitWalls(player.pos, 'r', player.PWIDTH, player.STEPX)) {
                    player.pos.x += player.STEPX;
                    player.startAngle = 45;
                }
                if (!playerHitByChaser) {
                    removedBeans = beans.removeEatJudge(player);
                    if (removedBeans == 'p') {
                        playerScore = String.valueOf(Integer.parseInt(playerScore) + SCOREINCREASE);
                        player.timemer += 1;
                    }
                }
                computerStep();
                break;
        }
    }

    // The author of this function is Yikai Luo
    /**
     * Return whether the input item's next position would hit the wall or not
     *
     * @param pos        item's pos
     * @param direction  item's moving direction
     * @param width      item's own width
     * @param stepLength item's step length
     * @return true if item's next step would hit the wall
     */

    public static boolean hitWalls(Pos pos, char direction, float width, float stepLength) {

        switch (direction) {
            case 'u':
                for (Wall wall : wallsHorizon) {
                    // up situation
                    if (pos.x + width > wall.pos.x && pos.x - width < wall.pos.x + 0.1f)
                        // player's x_pos is between the left and right wall
                        if (pos.y - width > wall.pos.y + 0.02f)
                            // first, player's current position is below the wall
                            if (pos.y - width - stepLength < wall.pos.y + 0.02f)
                                // second, player's next position would above the wall
                                return true;
                }
                break;

            case 'd':
                for (Wall wall : wallsHorizon) {
                    // down situation
                    if (pos.x + width > wall.pos.x && pos.x - width < wall.pos.x + 0.1f) {
                        if (pos.y + width < wall.pos.y)
                            if (pos.y + width + stepLength > wall.pos.y)
                                return true;
                    }
                }
                break;

            case 'l':
                for (Wall wall : wallsVertic) {
                    // left situation
                    if (pos.y + width > wall.pos.y && pos.y - width < wall.pos.y + 0.2f)
                        //player y_pos is between the top and bottom wall
                        if (pos.x - width > wall.pos.x + 0.01f)
                            // first, player's position is on the right of this wall
                            if (pos.x - width - stepLength < wall.pos.x + 0.01f)
                                return true;
                }
                break;

            case 'r':
                for (Wall wall : wallsVertic) {
                    // right situation
                    if (pos.y - width > wall.pos.y && pos.y - width < wall.pos.y + 0.2f)
                        if (pos.x - width < wall.pos.x)
                            if (pos.x + width + stepLength > wall.pos.x)
                            {
                                return true;
                            }

                }
                break;
        }

        return false;
    }

    // The author of this function is Yikai Luo
    /**
     * check whether input item's next position would hit the boundary or not
     *
     * @param pos
     * @param direction
     * @param stepLength
     * @return true it next position would hit the boundary
     */

    public boolean hitBoundary(Pos pos, char direction, float stepLength) {
        switch (direction) {
            case 'u':
                if (pos.y - stepLength < 0.09)
                    return true;
                break;
            case 'd':
                if (pos.y + stepLength > 0.91)
                    return true;
                break;
            case 'l':
                if (pos.x - stepLength < 0.16)
                    return true;
                break;
            case 'r':
                if (pos.x + stepLength > 0.96){
                    return true;
                }

                break;
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean playerWon() {
        if (!playerHitByChaser)
            if (gameFinished() && Integer.parseInt(playerScore) - Integer.parseInt(computerScore) > 0)
                return true;
        return false;
    }

    public boolean playerLose() {
        if (playerHitByChaser)
            return true;
        else if (gameFinished() && Integer.parseInt(playerScore) - Integer.parseInt(computerScore) < 0) {
            return true;
        }
        return false;
    }

    public boolean gameFinished() {
        if (beans.size() == 0)
            return true;
        else
            return false;
    }

}
