package com.example.asus.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class TableLayout extends NavigationbarActivity {

    PaintBoard drawingView;
    LinearLayout rect;
    LinearLayout circle;
    LinearLayout circleBack;
    LinearLayout background;
    LinearLayout chair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_table_layout);
        //產生sliding menu
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_table_layout, null, false);
        drawer.addView(contentView, 0);

        LinearLayout layout = (LinearLayout) findViewById(R.id.drawing_area);
        drawingView = new PaintBoard(this);
        drawingView.invalidate();
        layout.addView(drawingView);
        rect = (LinearLayout) findViewById(R.id.rect);
        circle = (LinearLayout) findViewById(R.id.circle);
        circleBack = (LinearLayout) findViewById(R.id.circleBack);
        background = (LinearLayout) findViewById(R.id.background);
        chair = (LinearLayout) findViewById(R.id.chair);
        chooseTable();
        setBackground();

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

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
        chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setTableType(PaintBoard.TableType.CHAIR);
            }
        });
    }

    private static final int SELECT_PICTURE = 1;
    public void setBackground() {
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent pickIntent = new Intent();
                pickIntent.setType("image/*");
                pickIntent.setAction(Intent.ACTION_GET_CONTENT);
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String pickTitle = "Select or take a new Picture";
                Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
                chooserIntent.putExtra (
                        Intent.EXTRA_INITIAL_INTENTS,
                        new Intent[] { takePhotoIntent}
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
        circleBack.setBackgroundResource(R.drawable.circle_table);
    }
}


