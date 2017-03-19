package com.example.asus.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.google.zxing.integration.android.IntentIntegrator;
import java.util.ArrayList;

public class PaintBoardToScan extends View {

    private Paint paint;
    ArrayList<Table> allTables = new ArrayList<Table>();
    TableEditor tableEditor;

    public PaintBoardToScan(TableEditor tableEditor) {
        super(tableEditor);
        new GetAllTables(allTables, this).execute();
        this.tableEditor = tableEditor;
        paint = new Paint();
        int tableColor = tableEditor.getResources().getColor(R.color.yello);
        paint.setColor(tableColor);
    }

    public PaintBoardToScan(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.argb(10, 10, 10, 10));
        for (int i = 0; i < allTables.size(); i++) {
            allTables.get(i).draw(canvas, paint);
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        int left = (int) ev.getX();
        int top = (int) ev.getY();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                for (int i = allTables.size() - 1; i >= 0; i--) {
                    if (allTables.get(i).isInside(left, top)) {
                        IntentIntegrator integrator = new IntentIntegrator(tableEditor);
                        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                        integrator.setPrompt("Scan");
                        integrator.setCameraId(0);
                        integrator.setBeepEnabled(false);
                        integrator.setBarcodeImageEnabled(false);
                        integrator.initiateScan();
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
