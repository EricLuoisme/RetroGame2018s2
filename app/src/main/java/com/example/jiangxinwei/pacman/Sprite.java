package com.example.jiangxinwei.pacman;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Sprite {

    Pos pos;

    public abstract void draw(Canvas c, Paint p);
}
