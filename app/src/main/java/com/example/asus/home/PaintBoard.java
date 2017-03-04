package com.example.asus.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Handler;


public class PaintBoard extends View {

    public enum TableType {
        ROUND, RECTANGE, TEXT, NONE
    }

    public enum PressPoint {
        SHAPE, CONTROL_POINT, NONE
    }

    private Paint paint;
    ArrayList<Table> allTables = new ArrayList<Table>();
    TableType tableType = TableType.NONE;
    PressPoint pressPoint = PressPoint.NONE;
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
        allTables.add(new OvalTable(left, top, left + 250, top + 250));
    }

    public void addTextOnTable(int left, int top) {
        allTables.add(new TextOnTable(left, top, left + 250, top + 250));
    }

    int selectedIndex = -1;
    int xOffSet = 0;
    int yOffSet = 0;
    int widthOffSet = 0;
    int heightOffset = 0;
    int originalWidth;
    int originalHeight;
    public boolean onTouchEvent(MotionEvent ev) {
        int index = ev.getActionIndex();
        int action = ev.getActionMasked();
        int left = (int) ev.getX();
        int top = (int) ev.getY();
        boolean longClick = false;
        final int MIN_DISTANCE = 1;
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                clearSelectedTable();
                if (tableType == TableType.NONE) {
                    actionDownWithNone(left, top);
                    invalidate();
                    return true;
                }
                if (tableType == TableType.RECTANGE) {
                    addRectangleTable(left, top);
                }
                else if (tableType == TableType.ROUND) {
                    addRoundTable(left, top);
                }
                else if (tableType == TableType.TEXT) {
                    addTextOnTable(left, top);
                }
                tableLayout.clearAllSelections();
                invalidate();
                tableType = TableType.NONE;
                selectedIndex = -1;
                pressPoint = PressPoint.NONE;

                longClick = false;
                return true;

            case MotionEvent.ACTION_MOVE:
                if (ev.getEventTime() - ev.getDownTime() > 500 && Math.abs(ev.getX() - left) < MIN_DISTANCE) {
                    longClick = true;
                }
                if (pressPoint == PressPoint.CONTROL_POINT) {
                    allTables.get(selectedIndex).setSize(left - originalWidth + widthOffSet, top - originalHeight + heightOffset);
                    invalidate();
                }
                else if (pressPoint == PressPoint.SHAPE) {
                   allTables.get(selectedIndex).setTable(left - xOffSet, top - yOffSet);
                   invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (longClick) {
                    addRectangleTable(left, top);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
        }
        return true;
    }

    public void actionDownWithNone(int left, int top) {
        selectedIndex = -1;
        pressPoint = PressPoint.NONE;
        for (int i = allTables.size() - 1; i >= 0; i--) {
            if (allTables.get(i).isInsideControlPoint(left, top)) {
                allTables.get(i).setIsSelected(true);
                originalWidth = allTables.get(i).getWidth();
                originalHeight = allTables.get(i).getHeight();
                widthOffSet = left - allTables.get(i).getLeft() - originalWidth;
                heightOffset = top - allTables.get(i).getTop() - originalHeight;
                selectedIndex = i;
                pressPoint = PressPoint.CONTROL_POINT;
                break;
            }
            if (allTables.get(i).isInside(left, top)) {
                allTables.get(i).setIsSelected(true);
                xOffSet = left - allTables.get(i).getLeft();
                yOffSet = top - allTables.get(i).getTop();
                selectedIndex = i;
                pressPoint = PressPoint.SHAPE;
                break;
            }
        }
    }

    public void clearSelectedTable() {
        for (int i = 0; i < allTables.size(); i++) {
            allTables.get(i).setIsSelected(false);
        }
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
        clearSelectedTable();
        invalidate();
    }

}