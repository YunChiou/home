package com.example.asus.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class TableLayout extends NavigationbarActivity {

    PaintBoard drawingView;
    LinearLayout rect;
    LinearLayout circle;
    LinearLayout circleBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_table_layout);
        //產生sliding menu
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_table_layout, null, false);
        drawer.addView(contentView, 0);

        LinearLayout layout=(LinearLayout) findViewById(R.id.drawing_area);
        drawingView = new PaintBoard(this);
        drawingView.invalidate();
        layout.addView(drawingView);
        rect = (LinearLayout) findViewById(R.id.rect);
        circle = (LinearLayout) findViewById(R.id.circle);
        circleBack = (LinearLayout) findViewById(R.id.circleBack);
        chooseTable();
    }

    private void chooseTable() {
        rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rect.setBackgroundResource(R.drawable.rectangle_table_selected);
                circle.setBackgroundResource(R.drawable.circle_table);
                circleBack.setBackgroundResource(R.drawable.circle_table);
                drawingView.setTableType(PaintBoard.TableType.RECTANGE);
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle.setBackgroundResource(R.drawable.circle_table_selected);
                rect.setBackgroundResource(R.drawable.rectangle_table);
                circleBack.setBackgroundResource(R.drawable.circle_table);
                drawingView.setTableType(PaintBoard.TableType.ROUND);
            }
        });
        circleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleBack.setBackgroundResource(R.drawable.circle_table_selected);
                circle.setBackgroundResource(R.drawable.circle_table);
                rect.setBackgroundResource(R.drawable.rectangle_table);
                drawingView.setTableType(PaintBoard.TableType.DELETE);
            }
        });
    }

    public void clearAllSelections() {
        circle.setBackgroundResource(R.drawable.circle_table);
        rect.setBackgroundResource(R.drawable.rectangle_table);
        circleBack.setBackgroundResource(R.drawable.circle_table);
    }
}


