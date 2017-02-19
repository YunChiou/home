package com.example.asus.home;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class OvalTable extends Table {

    public OvalTable(int left, int top, int right, int bottom) {
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
        RectF oval = new RectF(boundingBoxLeft, boundingBoxTop, boundingBoxLeft + width, boundingBoxTop + height);
        canvas.drawOval(oval, paint);
        super.draw(canvas, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.YELLOW);
        RectF ovalBox = new RectF(boundingBoxLeft + BORDER_WIDTH, boundingBoxTop + BORDER_WIDTH, boundingBoxLeft + width - BORDER_WIDTH, boundingBoxTop + height - BORDER_WIDTH);
        canvas.drawOval(ovalBox, paint);
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
