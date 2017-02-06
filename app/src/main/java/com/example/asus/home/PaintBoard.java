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

    public void addRectangleTable(int left, int top) {
        allTables.add(new RectangleTable(left, top, left + 250, top + 250));
    }

    public void addRoundTable(int left, int top) {
        allTables.add(new CircleTable(left, top, 150));
    }

    int selectedIndex = -1;
    public boolean onTouchEvent(MotionEvent ev) {
        int index = ev.getActionIndex();
        int action = ev.getActionMasked();
        int left = (int) ev.getX();
        int top = (int) ev.getY();

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                clearSelectedTable();
                if (tableType == TableType.NONE) {
                    for (int i = allTables.size() - 1; i >= 0; i--) {
                        if (allTables.get(i).isInside(left, top)) {
                            allTables.get(i).setIsSelected(true);
                            selectedIndex = i;
                            break;
                        }
                    }
                    invalidate();
                    return true;
                }
                if (tableType == TableType.RECTANGE) {
                    addRectangleTable(left, top);
                }
                else if (tableType == TableType.ROUND) {
                    addRoundTable(left, top);
                }
                tableLayout.clearAllSelections();
                invalidate();
                tableType = TableType.NONE;
                return true;

            case MotionEvent.ACTION_MOVE:
                if (selectedIndex >= 0) {
                    allTables.get(selectedIndex).moveTable(left, top);
                    invalidate();
                }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
        }
        return true;
    }

    public void clearSelectedTable() {
        for (int i = 0; i < allTables.size(); i++) {
            allTables.get(i).setIsSelected(false);
        }
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
        selectedIndex = -1;
        clearSelectedTable();
        invalidate();
    }

}