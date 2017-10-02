package com.example.asus.home;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

public class RestaurantModel extends ToolbarActivity {

    RestaurantModelRenderer renderer;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_model);

        final SurfaceView surface = new SurfaceView(this);
        surface.setFrameRate(60.0);
        //surface.setTransparent(true);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        LinearLayout rajawali = (LinearLayout)findViewById(R.id.rajawali);
        rajawali.addView(surface);
        renderer = new RestaurantModelRenderer(this);
        surface.setSurfaceRenderer(renderer);

        tv = (TextView) this.findViewById(R.id.textView);
        tv.setSelected(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN) { //Called once at the start of the touch
            //renderer.getCurrentCamera().setZ(renderer.getCurrentCamera().getZ()-3);
            tv.setText("曉青，21歲，喜歡CNBlue");
        }
        return true;
    }

    //產生back arrow
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
