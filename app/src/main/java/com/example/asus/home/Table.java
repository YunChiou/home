package com.example.asus.home;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Table {

    int id;
    int height = 250;
    int width = 250;
    int boundingBoxLeft;
    int boundingBoxTop;
    String tableType;
    boolean isSelected;
    final int CONTROL_POINT_SIZE = 30;
    final int BORDER_WIDTH = 3;
    String tableNumber = "";

    public Table() {
        isSelected = false;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setID (int id) {
        this.id = id;
    }

    public void setTableType (String tableType) {
        this.tableType = tableType;
    }

    public int getID() {
        return id;
    }

    public int getLeft() {
        return this.boundingBoxLeft;
    }

    public int getTop() {
        return this.boundingBoxTop;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return tableNumber;
    }

    public String getTableType() {
        return tableType;
    }

    public void draw(Canvas canvas, Paint paint) {
        if (isSelected) {
            //邊框
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(6);
            canvas.drawRect(boundingBoxLeft, boundingBoxTop, boundingBoxLeft + width, boundingBoxTop + height, paint);
            // 控制點
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(Color.RED);
            canvas.drawRect(boundingBoxLeft + width - CONTROL_POINT_SIZE, boundingBoxTop + height -CONTROL_POINT_SIZE, boundingBoxLeft + width + CONTROL_POINT_SIZE, boundingBoxTop + height + CONTROL_POINT_SIZE, paint);

        }
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public boolean isInsideControlPoint(int left, int top) {
        if (boundingBoxLeft + width - CONTROL_POINT_SIZE < left && boundingBoxLeft + width + CONTROL_POINT_SIZE > left && boundingBoxTop + height  -CONTROL_POINT_SIZE < top && boundingBoxTop + height  + CONTROL_POINT_SIZE > top) {
            return true;
        }
        return false;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    abstract public boolean isInside(int left, int top);

    abstract public void setTable(int left, int top);

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
