package com.example.asus.home;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TextOnTable extends Table {

    public TextOnTable(int left, int top, int right, int bottom) {
        this.boundingBoxLeft = left;
        this.boundingBoxTop = top;
        this.boundingBoxRight = left + width;
        this.boundingBoxBottom = top + height;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        canvas.drawText("1", boundingBoxLeft, boundingBoxTop, paint);
        canvas.drawRect(boundingBoxLeft + BORDER_WIDTH, boundingBoxTop + BORDER_WIDTH, boundingBoxLeft + width - BORDER_WIDTH, boundingBoxTop + height - BORDER_WIDTH, paint);
        super.draw(canvas, paint);
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
