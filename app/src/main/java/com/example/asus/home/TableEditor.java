package com.example.asus.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

public class TableEditor extends ToolbarActivity {

    PaintBoardToScan drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_editor);

        LinearLayout layout = (LinearLayout) findViewById(R.id.drawing_area);
        drawingView = new PaintBoardToScan(this);
        drawingView.invalidate();
        layout.addView(drawingView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent date){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,date);
        if(result != null){
            if(result.getContents() == null)
                Toast.makeText(this,"You cancelled the scanning",Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                drawingView.setCondition(result.getContents());
            }
        }
        else {
            //MainActivity.super.onActivityResult(requestCode, resultCode, date);
        }
    }
    //產生back arrow
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
