package com.example.jiangxinwei.pacman;

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

        public float getY(){
            return y;
        }
}
