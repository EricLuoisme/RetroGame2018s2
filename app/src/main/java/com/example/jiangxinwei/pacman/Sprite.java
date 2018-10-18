package com.example.jiangxinwei.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

// The author of this class file is Xinwei Jiang

public abstract class Sprite {

    Pos pos;

    public abstract void draw(Canvas c, Paint p, Bitmap b);
}
