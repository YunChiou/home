package com.example.asus.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class TableLayout extends ToolbarActivity {

    PaintBoard drawingView;
    LinearLayout rect;
    LinearLayout circle;
    LinearLayout delete;
    LinearLayout background;
    LinearLayout chair1_1;
    LinearLayout chair1_2;
    LinearLayout table1_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        LinearLayout layout = (LinearLayout) findViewById(R.id.drawing_area);
        drawingView = new PaintBoard(this);
        drawingView.invalidate();
        layout.addView(drawingView);
        rect = (LinearLayout) findViewById(R.id.rect);
        circle = (LinearLayout) findViewById(R.id.circle);
        delete = (LinearLayout) findViewById(R.id.delete);
        background = (LinearLayout) findViewById(R.id.background);
        chair1_1 = (LinearLayout) findViewById(R.id.chair1_1);
        chair1_2 = (LinearLayout) findViewById(R.id.chair1_2);
        table1_1 = (LinearLayout) findViewById(R.id.table1_1);
        chooseTable();
        setBackground();
        setChairsNumber();
    }

    private void chooseTable() {
        rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rect.setBackgroundResource(R.drawable.rectangle_table_selected);
                circle.setBackgroundResource(R.drawable.circle_table);
                delete.setBackgroundResource(R.drawable.garbage);
                drawingView.setTableType(PaintBoard.TableType.RECTANGE);
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle.setBackgroundResource(R.drawable.circle_table_selected);
                rect.setBackgroundResource(R.drawable.rectangle_table);
                delete.setBackgroundResource(R.drawable.garbage);
                drawingView.setTableType(PaintBoard.TableType.ROUND);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete.setBackgroundResource(R.drawable.red_garbage);
                circle.setBackgroundResource(R.drawable.circle_table);
                rect.setBackgroundResource(R.drawable.rectangle_table);
                drawingView.setTableType(PaintBoard.TableType.DELETE);
            }
        });
        chair1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setTableType(PaintBoard.TableType.CHAIR);
                drawingView.setObjectNumber(1, 1);
            }
        });
        chair1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setTableType(PaintBoard.TableType.CHAIR);
                drawingView.setObjectNumber(1, 2);
            }
        });
        table1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setTableType(PaintBoard.TableType.TABLE);
                drawingView.setObjectNumber(1, 1);
            }
        });
    }

    private static final int SELECT_PICTURE = 1;

    public void setBackground() {
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickIntent = new Intent();
                pickIntent.setType("image/*");
                pickIntent.setAction(Intent.ACTION_GET_CONTENT);
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String pickTitle = "Select or take a new Picture";
                Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
                chooserIntent.putExtra(
                        Intent.EXTRA_INITIAL_INTENTS,
                        new Intent[]{takePhotoIntent}
                );
                startActivityForResult(chooserIntent, SELECT_PICTURE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            if (data == null)
                return;
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                drawingView.setBackground(bitmap);

            } catch (FileNotFoundException e) {
            }
        }
    }

    public void clearAllSelections() {
        circle.setBackgroundResource(R.drawable.circle_table);
        rect.setBackgroundResource(R.drawable.rectangle_table);
        delete.setBackgroundResource(R.drawable.garbage);
    }

    AlertDialog alertDialog;
    EditText tableNumber;
    public void setChairsNumber() {
        chair1_1.setOnLongClickListener(new
        View.OnLongClickListener() {
            @Override
            public boolean onLongClick (View v){
                alertDialog = new AlertDialog.Builder(TableLayout.this).create();
                alertDialog.setTitle("請輸入數量");
                LinearLayout layout = new LinearLayout(TableLayout.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                tableNumber = new EditText(TableLayout.this);
                tableNumber.setHint("");
                layout.addView(tableNumber);
                final Button enterText = new Button(TableLayout.this);
                enterText.setText("確認");
                layout.addView(enterText);
                alertDialog.setView(layout);
                alertDialog.show();
                return true;
            }

        });
    }
}