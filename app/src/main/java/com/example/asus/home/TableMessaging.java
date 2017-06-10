package com.example.asus.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class TableMessaging extends NavigationbarActivity {

    PaintBoardToSendMessage drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_table_messaging, null, false);
        drawer.addView(contentView, 0);

        LinearLayout layout = (LinearLayout) findViewById(R.id.drawing_area);
        drawingView = new PaintBoardToSendMessage(this);
        drawingView.invalidate();
        layout.addView(drawingView);
    }

}
