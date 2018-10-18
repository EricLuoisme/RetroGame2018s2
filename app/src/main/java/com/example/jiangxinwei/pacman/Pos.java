package com.example.jiangxinwei.pacman;

import java.text.DecimalFormat;

// The author of this class file is Xinwei Jiang

public class Pos {
        float x;
        float y;

        public Pos(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Pos(Pos p) {
            this.x = p.x;
            this.y = p.y;
        }

        public float distance(Pos p) {
            float dx = x - p.x;
            float dy = y - p.y;
            return (float) Math.sqrt(dx*dx + dy*dy);
        }

        public float stepCount(Pos p) {  //This function counts how many steps the computer can eat a bean
            float step;
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            String tmp_x = decimalFormat.format(p.x);
            String tmp_y = decimalFormat.format(p.y);
            p.x = Float.parseFloat(tmp_x);
            p.y = Float.parseFloat(tmp_y);
            tmp_x = decimalFormat.format(x);
            tmp_y = decimalFormat.format(y);
            x = Float.parseFloat(tmp_x);
            y = Float.parseFloat(tmp_y);
            step = Math.abs(x -p.x)/0.1f + Math.abs(y -p.y)/0.2f;
            tmp_x = decimalFormat.format(step);
            step = Float.parseFloat(tmp_x);
            return step;
        }

        public float getY(){
            return y;
        }
}
