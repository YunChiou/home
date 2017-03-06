package com.example.asus.home;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TextOnTable extends Table {

    public TextOnTable(int left, int top) {
        this.boundingBoxLeft = left;
        this.boundingBoxTop = top;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        canvas.drawText("1", boundingBoxLeft + width / 2, boundingBoxTop + height / 2, paint);
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
    }
}
