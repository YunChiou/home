package com.example.asus.home;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;


public class PaintBoard extends View {

    public enum TableType {
        ROUND, RECTANGE, NONE
    }

    private Paint paint;
    ArrayList<Table> allTables = new ArrayList<Table>();
    TableType tableType = TableType.NONE;
    TableLayout tableLayout;

    public PaintBoard(TableLayout tableLayout) {
        super(tableLayout);
        this.tableLayout = tableLayout;
        paint = new Paint();
        int tableColor = tableLayout.getResources().getColor(R.color.yello);
        paint.setColor(tableColor);
    }

    public PaintBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.argb(10, 10, 10, 10));
        for (int i = 0; i < allTables.size(); i++) {
            allTables.get(i).draw(canvas, paint);
        }
    }

    public void addRectangleTable(int left, int top)
    {
        allTables.add(new RectangleTable(left, top, left + 250, top + 250));
    }

    public void addRoundTable(int left, int top)
    {
        allTables.add(new CircleTable(left, top, 150));
    }

    public boolean onTouchEvent(MotionEvent ev) {
        int left = (int) ev.getX();
        int top = (int) ev.getY();
        if (tableType == TableType.NONE)
            return true;
        if (tableType == TableType.RECTANGE)
            addRectangleTable(left, top);
        else if (tableType == TableType.ROUND)
            addRoundTable(left, top);
        tableLayout.clearAllSelections();
        invalidate();
        tableType = TableType.NONE;
        return true;
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }

}