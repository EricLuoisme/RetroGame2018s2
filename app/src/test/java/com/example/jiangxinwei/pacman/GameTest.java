package com.example.jiangxinwei.pacman;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    Game gamTest = new Game();
    Player p = new Player();

    @Test
    public void hitWalls() {

        p.pos.y = 0.7f;
        p.pos.x = 0.66f;
        assertTrue(gamTest.hitWalls(p.pos,'l', p.PWIDTH, p.STEPX));
        p.pos.x = 0.76f;
        assertTrue(!gamTest.hitWalls(p.pos,'l', p.PWIDTH, p.STEPX));

        p.pos.y = 0.5f;
        p.pos.x = 0.35f;
        assertTrue(gamTest.hitWalls(p.pos,'r', p.PWIDTH, p.STEPX));
        p.pos.x = 0.26f;
        assertTrue(!gamTest.hitWalls(p.pos,'r', p.PWIDTH, p.STEPX));

        p.pos.y = 0.7f;
        p.pos.x = 0.25f;
        assertTrue(gamTest.hitWalls(p.pos, 'u', p.PWIDTH, p.STEPY));
        p.pos.y = 0.5f;
        assertTrue(!gamTest.hitWalls(p.pos, 'u', p.PWIDTH, p.STEPY));


        p.pos.x = 0.46f;
        assertTrue(gamTest.hitWalls(p.pos, 'd', p.PWIDTH, p.STEPY));
        p.pos.y = 0.3f;
        assertTrue(!gamTest.hitWalls(p.pos, 'd', p.PWIDTH, p.STEPY));

    }

    @Test
    public void hitBoundary() {

        p.pos.y = 0.2f;
        p.pos.x = 0.5f;
        assertTrue(gamTest.hitBoundary(p.pos, 'u', p.STEPY));
        p.pos.y = 0.5f;
        assertTrue(!gamTest.hitBoundary(p.pos, 'u', p.STEPY));

        p.pos.x = 0.25f;
        assertTrue(gamTest.hitBoundary(p.pos, 'l', p.STEPX));
        p.pos.x = 0.55f;
        assertTrue(!gamTest.hitBoundary(p.pos, 'l', p.STEPX));

        p.pos.x = 0.35f;
        p.pos.y = 0.9f;
        assertTrue(gamTest.hitBoundary(p.pos, 'd', p.STEPY));
        p.pos.y = 0.7f;
        assertTrue(!gamTest.hitBoundary(p.pos, 'd', p.STEPY));

        p.pos.x = 0.96f;
        assertTrue(gamTest.hitBoundary(p.pos, 'r', p.STEPX));
        p.pos.x = 0.7f;
        assertTrue(!gamTest.hitBoundary(p.pos, 'r', p.STEPX));

    }

    @Test
    public void hitByChaser(){
        Pos p1 = new Pos(0.1f, 0.2f);
        Chaser c1 = new Chaser(p1);
        Pos p2 = new Pos(0.5f, 0.9f);
        Chaser c2 = new Chaser(p2);
        Pos p3 = new Pos(0.23f, 0.5f);
        Chaser c3 = new Chaser(p3);
        Pos p4 = new Pos(0.78f, 0.76f);
        Chaser c4 = new Chaser(p4);

        Chasers cs = new Chasers();
        cs.add(c1);
        cs.add(c2);
        cs.add(c3);
        cs.add(c4);

        p.pos.x = 0.78f;
        p.pos.y = 0.77f;
        assertTrue(p.hitByChaser(cs));

        p.pos.x = 0.5f;
        p.pos.y = 0.5f;
        assertTrue(!p.hitByChaser(cs));

    }


}