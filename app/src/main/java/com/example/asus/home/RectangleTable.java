package com.example.asus.home;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectangleTable extends Table {

    public RectangleTable(int left, int top) {
        this.boundingBoxLeft = left;
        this.boundingBoxTop = top;
    }

    public RectangleTable(int id, String tableType, int boundingBoxLeft, int boundingBoxTop, int width, int height, String text) {
        this.id = id;
        this.tableType = tableType;
        this.boundingBoxLeft = boundingBoxLeft;
        this.boundingBoxTop = boundingBoxTop;
        this.width = width;
        this.height = height;
        this.tableNumber = text;
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
        //文字
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText(tableNumber, boundingBoxLeft + 10, boundingBoxTop + 50, paint);
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