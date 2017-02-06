package com.example.asus.home;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public abstract class Table {

    int boundinBoxLeft;
    int boundinBoxTop;
    int boundinBoxRight;
    int boundinBoxBottom;
    boolean isSelected;

    public Table() {
        isSelected = false;
    }

    public void draw(Canvas canvas, Paint paint) {
        //邊框
        if (isSelected) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(6);
            canvas.drawRect(boundinBoxLeft, boundinBoxTop, boundinBoxRight, boundinBoxBottom, paint);
        }
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    abstract public boolean isInside(int left, int top);

    abstract public void moveTable(int left, int top);

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
