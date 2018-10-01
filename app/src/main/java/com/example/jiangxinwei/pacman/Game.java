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
    private Player player;
    private boolean computerHitByChaser = false;
    private boolean beansEmpty = false;   //when there is no beans, then game finished
    private String computerScore = "0";
    private boolean removed;

    public static final float MAXXY = 1.0f;
    public static final float MINXY = 0.0f;
    public static final int SCOREINCREASE = 10;  //eat one bean, score increases 10


    public Game() {
        wallsHorizon = Walls.createWallsHorizon();
        wallsVertic = Walls.createWallsVertic();
        beans = Beans.createBeans();
        chasers = new Chasers();
        computer = new Computer();
        player = Player.createPlayer();
    }

    public void draw(Canvas canvas, Paint paint) {
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        paint.setTextSize(50.0f);
        canvas.drawText(computerScore, 0.07f * w, 0.2f * h, paint);
        wallsHorizon.drawH(canvas, paint);
        wallsVertic.drawV(canvas, paint);
        beans.draw(canvas, paint);
        chasers.draw(canvas, paint);
        player.draw(canvas, paint);
        if (!computerHitByChaser) {
            computer.draw(canvas, paint);
        }
    }

    public void step() {
        //computer.step();
        chasers.step();

        if(!computerHitByChaser) {   //computer can move if it has not eaten by chaser
            //Find the closest bean
            Float smallestSteps = 50f;
            Float steps;
            Float closeX = 0f;
            Float closeY = 0f;
            boolean accepted;
            //Log.d("computer position", String.valueOf(computer.pos.x) + " " + String.valueOf(computer.pos.y));
            for (Bean b : beans) {
                steps = computer.pos.stepCount(b.pos);
                //Log.d("close", String.valueOf(b.pos.x) + " " + String.valueOf(b.pos.y));
                accepted = b.noWall(computer, wallsHorizon, wallsVertic);
                //Log.d("dis", String.valueOf(steps) + " " + String.valueOf(smallestSteps));
                if (steps < smallestSteps && accepted) {
                    smallestSteps = steps;
                    closeX = b.pos.x;
                    closeY = b.pos.y;
                }
            }
            //Log.d("final close", String.valueOf(closeX) + " " + String.valueOf(closeY));

            //store the valid move for computer to avoid chaser
            ArrayList<String> moveAvoidChaser = computer.avoidChaser(chasers);
            ArrayList<String> moveAvoidWall = computer.avoidWall(wallsHorizon, wallsVertic);

            //Let computer move;
            computer.step(closeX, closeY, moveAvoidChaser, moveAvoidWall);

            //Remove bean which has been eaten
            removed = beans.removeEat(computer);
            if(removed == true){
                computerScore = String.valueOf(Integer.parseInt(computerScore) + SCOREINCREASE);
            }

            //check if computer is hit by chasers
            computerHitByChaser = computer.hitByChaser(chasers);
        }
    }

    public ArrayList<String> avoidChaser() {      //the computer should avoid chasers
        ArrayList<String> validMove = new ArrayList<String>();
        validMove.add("left");
        validMove.add("right");
        validMove.add("up");
        validMove.add("down");
        //Log.d("before move", String.valueOf(validMove.size()));
        for (Chaser c : chasers) {
            if (c.pos.distance(computer.pos) <= 0.2f) {    //computer should not move to the direction of that chaser
                DecimalFormat decimalFormat = new DecimalFormat(".00");
                String x = decimalFormat.format(computer.pos.x);
                String y = decimalFormat.format(computer.pos.y);
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

    public void touch(String direction) {
        switch (direction) {
            case "u":
                if (!hitBoundary(player.pos, 'u', player.STEPY) &&
                        !hitWalls(player.pos, 'u', player.PWIDTH, player.STEPY)) {
                    player.pos.y -= player.STEPY;
//                    Log.d("Player pos", "y " + player.pos.y + "  x " + player.pos.x);
                }
                break;
            case "d":
                if (!hitBoundary(player.pos, 'd', player.STEPY) &&
                        !hitWalls(player.pos, 'd', player.PWIDTH, player.STEPY)) {
                    player.pos.y += player.STEPY;
//                    Log.d("Player pos", "y " + player.pos.y + "  x " + player.pos.x);
                }
                break;
            case "l":
                if (!hitBoundary(player.pos, 'l', player.STEPX) &&
                        !hitWalls(player.pos, 'l', player.PWIDTH, player.STEPX)) {
                    player.pos.x -= player.STEPX;
//                    Log.d("Player pos", "y " + player.pos.y + "  x " + player.pos.x);
                }
                break;
            case "r":
                if (!hitBoundary(player.pos, 'r', player.STEPX) &&
                        !hitWalls(player.pos, 'r', player.PWIDTH, player.STEPX)) {
                    player.pos.x += player.STEPX;
//                    Log.d("Player pos", "y " + player.pos.y + "  x " + player.pos.x);
                }
                break;
        }
    }

    /**
     * Return whether the input item's next position would hit the wall or not
     *
     * @param pos           item's pos
     * @param direction     item's moving direction
     * @param width         item's own width
     * @param stepLength    item's step length
     *
     * @return  true if item's next step would hit the wall
     */

    public boolean hitWalls(Pos pos, char direction, float width, float stepLength) {

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
                    if (pos.y + width> wall.pos.y && pos.y - width < wall.pos.y + 0.2f)
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
                                return true;
                }
                break;
        }

        return false;
    }

    /**
     * check whether input item's next position would hit the boundary or not
     *
     * @param pos
     * @param direction
     * @param stepLength
     *
     * @return true it next position would hit the boundary
     */

    public boolean hitBoundary(Pos pos, char direction, float stepLength) {
        switch (direction) {
            case 'u':
                if (pos.y - stepLength < 0.1)
                    return true;
                break;
            case 'd':
                if (pos.y + stepLength > 0.9)
                    return true;
                break;
            case 'l':
                if (pos.x - stepLength < 0.05)
                    return true;
                break;
            case 'r':
                if (pos.x + stepLength > 0.95)
                    return true;
                break;
        }
        return false;
    }

}
