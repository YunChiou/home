package com.example.asus.home;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class CircleTable extends Table {

    int x;
    int y;
    int radius;

    public CircleTable(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, 150, paint);
    }
}
