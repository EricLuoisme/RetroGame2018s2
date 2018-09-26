package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Computer extends Sprite{

    public Computer(){
        pos = new Pos(0.95f, 0.1f);
    }
    @Override
    public void draw(Canvas c, Paint p) {
        int h = c.getHeight();
        int w = c.getWidth();

        float xc = pos.x * w;
        float yc = pos.y * h;

        p.setColor(Color.GREEN);
        c.drawCircle(xc, yc,30, p);
    }

    /*The rule for computer move:
      In order to live longer, the computer first should avoid chaser,
      then computer always move the closest bean in order to get higher mark.
    */
    public void step(Float closeX, Float closeY, ArrayList<String> moveAvoidChaser) {
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        String x = decimalFormat.format(pos.x);
        String y = decimalFormat.format(pos.y);
        pos.x = Float.parseFloat(x);
        pos.y = Float.parseFloat(y);
        x = decimalFormat.format(closeX);
        y = decimalFormat.format(closeY);
        closeX = Float.parseFloat(x);
        closeY = Float.parseFloat(y);
        if(closeX < pos.x && closeY == pos.y){   //in the left
            if(moveAvoidChaser.contains("left")) {    //left is a valid move
                pos.x = pos.x - 0.1f;
                //Log.d("move", "left");
            }
            else if(moveAvoidChaser.contains("up"))
            {
                pos.y = pos.y - 0.2f;
                //Log.d("move", "up");
            }
            else if(moveAvoidChaser.contains("down")){
                pos.y = pos.y + 0.2f;
                //Log.d("move", "down");
            }
            else if(moveAvoidChaser.contains("right")){   //this is the worst case
                pos.x = pos.x + 0.1f;
                //Log.d("move", "right");
            }
            //Log.d("left", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX < pos.x && closeY < pos.y){    //in the left top
            //these two are better choices of direction
            if(moveAvoidChaser.contains("left")) {
                pos.x = pos.x - 0.1f;
                //Log.d("move", "left");
            }
            else if(moveAvoidChaser.contains("up"))
            {
                pos.y = pos.y - 0.2f;
                //Log.d("move", "up");
            }
            //these two are worse choices of direction
            else if(moveAvoidChaser.contains("down")){
                pos.y = pos.y + 0.2f;
                //Log.d("move", "down");
            }
            else if(moveAvoidChaser.contains("right")){
                pos.x = pos.x + 0.1f;
                //Log.d("move", "right");
            }
            //Log.d("left top", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX < pos.x && closeY > pos.y){    //in the left bottom
            //these two are better choices of direction
            if(moveAvoidChaser.contains("left")) {
                pos.x = pos.x - 0.1f;
                //Log.d("move", "left");
            }
            else if(moveAvoidChaser.contains("down"))
            {
                pos.y = pos.y + 0.2f;
                //Log.d("move", "down");
            }
            //these two are worse choices of direction
            else if(moveAvoidChaser.contains("up")){
                pos.y = pos.y - 0.2f;
                //Log.d("move", "up");
            }
            else if(moveAvoidChaser.contains("right")){
                pos.x = pos.x + 0.1f;
                //Log.d("move", "right");
            }
            //Log.d("left bottom", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX > pos.x && closeY == pos.y){   //in the right
            if(moveAvoidChaser.contains("right")) {
                pos.x = pos.x + 0.1f;
                //Log.d("move", "right");
            }
            else if(moveAvoidChaser.contains("up"))
            {
                pos.y = pos.y - 0.2f;
                //Log.d("move", "up");
            }
            else if(moveAvoidChaser.contains("down")){
                pos.y = pos.y + 0.2f;
                //Log.d("move", "down");
            }
            else if(moveAvoidChaser.contains("left")){ //the worst choice
                pos.x = pos.x + 0.1f;
                //Log.d("move", "left");
            }
            //Log.d("right", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX > pos.x && closeY < pos.y){    //in the right top
            //these two are better choices of direction
            if(moveAvoidChaser.contains("right")) {
                pos.x = pos.x + 0.1f;
                //Log.d("move", "right");
            }
            else if(moveAvoidChaser.contains("up"))
            {
                pos.y = pos.y - 0.2f;
                //Log.d("move", "up");
            }
            //these two are worse choices of direction
            else if(moveAvoidChaser.contains("down")){
                pos.y = pos.y + 0.2f;
                //Log.d("move", "down");
            }
            else if(moveAvoidChaser.contains("left")){
                pos.x = pos.x - 0.1f;
                //Log.d("move", "left");
            }
            //Log.d("right top", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX > pos.x && closeY > pos.y){    //in the right bottom
            //these two are better choices of direction
            if(moveAvoidChaser.contains("right")) {
                pos.x = pos.x + 0.1f;
                //Log.d("move", "right");
            }
            else if(moveAvoidChaser.contains("down"))
            {
                pos.y = pos.y + 0.2f;
                //Log.d("move", "down");
            }
            //these two are worse choices of direction
            else if(moveAvoidChaser.contains("up")){
                pos.y = pos.y - 0.2f;
                //Log.d("move", "up");
            }
            else if(moveAvoidChaser.contains("left")){
                pos.x = pos.x - 0.1f;
                //Log.d("move", "left");
            }
            //Log.d("right bottom", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX == pos.x && closeY > pos.y){   //on the bottom
            if(moveAvoidChaser.contains("down")) {
                pos.y = pos.y + 0.2f;
                //Log.d("move", "down");
            }
            else if(moveAvoidChaser.contains("left"))
            {
                pos.x = pos.x - 0.1f;
                //Log.d("move", "left");
            }
            else if(moveAvoidChaser.contains("right")){
                pos.x = pos.x + 0.1f;
                //Log.d("move", "right");
            }
            else if(moveAvoidChaser.contains("up")){ //the worst choice
                pos.y = pos.y - 0.2f;
                //Log.d("move", "up");
            }
            //Log.d("bottom", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        else if(closeX == pos.x && closeY < pos.y){   //on the top
            if(moveAvoidChaser.contains("up")) {
                pos.y = pos.y - 0.2f;
                //Log.d("move", "up");
            }
            else if(moveAvoidChaser.contains("left"))
            {
                pos.x = pos.x - 0.1f;
                //Log.d("move", "left");
            }
            else if(moveAvoidChaser.contains("right")){
                pos.x = pos.x + 0.1f;
                //Log.d("move", "right");
            }
            else if(moveAvoidChaser.contains("down")){ //the worst choice
                pos.y = pos.y + 0.2f;
                //Log.d("move", "down");
            }
            //Log.d("up", String.valueOf(closeX) + " " + String.valueOf(closeY));
        }
        //Log.d("position", String.valueOf(pos.x) + " " + String.valueOf(pos.y));
    }
}
