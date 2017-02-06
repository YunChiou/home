package com.example.asus.home;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class RectangleTable extends Table {

    public RectangleTable(int left, int top, int right, int bottom) {
        this.boundinBoxLeft = left;
        this.boundinBoxTop = top;
        this.boundinBoxRight = right;
        this.boundinBoxBottom = bottom;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        //內圍
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(boundinBoxLeft, boundinBoxTop, boundinBoxRight, boundinBoxBottom, paint);
        super.draw(canvas, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(boundinBoxLeft + 3, boundinBoxTop + 3, boundinBoxRight - 3, boundinBoxBottom - 3, paint);

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
        this.boundinBoxRight = left + 250;
        this.boundinBoxBottom = top + 250;
    }

}
