package com.example.asus.home;

import android.graphics.Canvas;
import android.graphics.Color;
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
        this.boundinBoxLeft = x - radius;
        this.boundinBoxTop = y - radius;
        this.boundinBoxRight = x + radius;
        this.boundinBoxBottom = y + radius;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        //邊框
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawCircle(x, y, 150, paint);
        //內圍
        paint.setStrokeWidth(0);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(x, y, 147, paint );
    }

    @Override
    public boolean isInside(int left, int top) {
        if (this.boundinBoxLeft < left && boundinBoxRight > left && this.boundinBoxTop < top && boundinBoxBottom > top) {
            return true;
        }
        return false;
    }

    @Override
    public void moveTable(int left, int top) {
        this.boundinBoxLeft = left;
        this.boundinBoxTop = top;
        this.boundinBoxRight = left + 2 * radius;
        this.boundinBoxBottom = top + 2 * radius;
        x = left + radius;
        y = top + radius;
    }

}
