package com.example.asus.home;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectangleTable extends Table {

    public RectangleTable(int left, int top, int right, int bottom) {
        this.boundingBoxLeft = left;
        this.boundingBoxTop = top;
        this.boundingBoxRight = left + width;
        this.boundingBoxBottom = top + height;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        //內圍
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(boundingBoxLeft, boundingBoxTop, boundingBoxLeft + width, boundingBoxTop + height, paint);
        super.draw(canvas, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(boundingBoxLeft + BORDER_WIDTH, boundingBoxTop + BORDER_WIDTH, boundingBoxLeft + width - BORDER_WIDTH, boundingBoxTop + height - BORDER_WIDTH, paint);
    }

    @Override
    public boolean isInside(int left, int top) {
        if (this.boundingBoxLeft < left && boundingBoxLeft + width > left && this.boundingBoxTop < top && boundingBoxTop + height > top) {
            return true;
        }
        return false;
    }

    @Override
    public void setTable(int left, int top) {
        this.boundingBoxLeft = left;
        this.boundingBoxTop = top;
        this.boundingBoxRight = left + width;
        this.boundingBoxBottom = top  + height;
    }

}