package com.example.asus.home;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class TableLayout extends AppCompatActivity {

    PaintBoard drawingView;
    LinearLayout rect;
    LinearLayout circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        LinearLayout layout=(LinearLayout) findViewById(R.id.drawing_area);
        drawingView = new PaintBoard(this);
        drawingView.invalidate();
        layout.addView(drawingView);
        rect = (LinearLayout) findViewById(R.id.rect);
        circle = (LinearLayout) findViewById(R.id.circle);
        chooseTable();
    }

    private void chooseTable() {
        rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rect.setBackgroundResource(R.drawable.rectangle_table_selected);
                circle.setBackgroundResource(R.drawable.circle_table);
                drawingView.setTableType(PaintBoard.TableType.RECTANGE);
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle.setBackgroundResource(R.drawable.circle_table_selected);
                rect.setBackgroundResource(R.drawable.rectangle_table);
                drawingView.setTableType(PaintBoard.TableType.ROUND);
            }
        });
    }

    public void clearAllSelections() {
        circle.setBackgroundResource(R.drawable.circle_table);
        rect.setBackgroundResource(R.drawable.rectangle_table);
    }
}


