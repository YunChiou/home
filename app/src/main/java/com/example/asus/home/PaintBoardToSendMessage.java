package com.example.asus.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

/**
 * Created by User on 2017/5/22.
 */

public class PaintBoardToSendMessage extends View {

    private Paint paint;
    ArrayList<Table> allTables = new ArrayList<Table>();
    TableMessaging tableMessaging;

    public PaintBoardToSendMessage(TableMessaging tableMessaging) {
        super(tableMessaging);
        new GetAllTables(allTables, this).execute();
        this.tableMessaging = tableMessaging;
        paint = new Paint();
        int tableColor = tableMessaging.getResources().getColor(R.color.yello);
        paint.setColor(tableColor);
    }

    public PaintBoardToSendMessage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.argb(10, 10, 10, 10));
        for (int i = 0; i < allTables.size(); i++) {
            allTables.get(i).draw(canvas, paint);
            allTables.get(i).setCondition(allTables.get(i).getCondition());
        }
    }

    int selectedIndex = -1;
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        int left = (int) ev.getX();
        int top = (int) ev.getY();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                for (int i = allTables.size() - 1; i >= 0; i--) {
                    if (allTables.get(i).isInside(left, top)) {
                        selectedIndex = i;
                        Intent intent = new Intent();
                        intent.setClass(tableMessaging, ActivitySendPushNotification.class);
                        intent.putExtra("tableId", allTables.get(i).getCondition());
                        tableMessaging.startActivity(intent);
                        break;
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_CANCEL:
        }
        return true;
    }

}
