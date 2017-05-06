package com.example.asus.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.ArrayList;


public class PaintBoard extends View {

    public enum TableType {
        ROUND, RECTANGE, DELETE, NONE
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
        new GetAllTables(allTables, this).execute();
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
        Table table = new RectangleTable(left, top);
        table.setTableType("R");
        allTables.add(table);
        new CreateNewTable(table).execute();
    }

    public void addRoundTable(int left, int top) {
        Table table = new OvalTable(left, top);
        table.setTableType("O");
        allTables.add(table);
        new CreateNewTable(table).execute();
    }

    int selectedIndex = -1;
    int xOffSet = 0;
    int yOffSet = 0;
    int widthOffSet = 0;
    int heightOffset = 0;
    int originalWidth;
    int originalHeight;
    int actionDownLeft = 0;
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        int left = (int) ev.getX();
        int top = (int) ev.getY();
        final int MIN_DISTANCE = 10;
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                actionDownLeft = (int) ev.getX();
                clearSelectedTable();
                if (tableType == TableType.NONE) {
                    actionDownWithNone(left, top);
                    invalidate();
                    return true;
                }
                if (tableType == TableType.RECTANGE)
                    addRectangleTable(left, top);
                else if (tableType == TableType.ROUND)
                    addRoundTable(left, top);
                else if (tableType == TableType.DELETE)
                    deleteTable(left, top);
                tableLayout.clearAllSelections();
                invalidate();
                tableType = TableType.NONE;
                selectedIndex = -1;
                pressPoint = PressPoint.NONE;
                return true;

            case MotionEvent.ACTION_MOVE:
                if (pressPoint == PressPoint.CONTROL_POINT) {
                    allTables.get(selectedIndex).setSize(left - originalWidth + widthOffSet, top - originalHeight + heightOffset);
                    new UpdateTable(allTables.get(selectedIndex)).execute();
                    invalidate();
                }
                else if (pressPoint == PressPoint.SHAPE) {
                    allTables.get(selectedIndex).setTable(left - xOffSet, top - yOffSet);
                    new UpdateTable(allTables.get(selectedIndex)).execute();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (ev.getEventTime() - ev.getDownTime() > 500 && Math.abs(ev.getX() - actionDownLeft) < MIN_DISTANCE) {
                    if (selectedIndex != -1)
                        showDialog();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
        }
        return true;
    }

    AlertDialog alertDialog;
    EditText tableNumber;
    public void showDialog() {
        alertDialog = new AlertDialog.Builder(tableLayout).create();
        alertDialog.setTitle("請輸入桌號");
        LinearLayout layout = new LinearLayout(tableLayout);
        layout.setOrientation(LinearLayout.VERTICAL);
        tableNumber = new EditText(tableLayout);
        tableNumber.setHint("");
        layout.addView(tableNumber);
        final Button enterText = new Button(tableLayout);
        enterText.setText("確認");
        layout.addView(enterText);
        alertDialog.setView(layout);
        alertDialog.show();
        enterText.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                allTables.get(selectedIndex).setTableNumber(tableNumber.getText().toString());
                new UpdateTable(allTables.get(selectedIndex)).execute();
                alertDialog.dismiss();
            }
        });
        invalidate();
    }

    public void deleteTable(int left, int top) {
        for (int i = allTables.size() - 1; i >= 0; i--) {
            if (allTables.get(i).isInside(left, top)) {
                allTables.get(i).setIsSelected(true);
                selectedIndex = i;
                new DeleteTable(allTables.get(selectedIndex), this).execute();
                allTables.remove(selectedIndex);
                break;
            }
        }
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