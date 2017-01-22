package com.example.asus.home;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class RectangleTable extends Table {

    int left;
    int top;
    int right;
    int bottom;

    public RectangleTable(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(new Rect(left, top, right, bottom), paint);
    }
}
