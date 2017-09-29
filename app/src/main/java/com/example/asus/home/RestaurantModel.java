package com.example.asus.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.rajawali3d.renderer.Renderer;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

public class RestaurantModel extends ToolbarActivity {

    RestaurantModelRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_model);

        final SurfaceView surface = new SurfaceView(this);
        surface.setFrameRate(60.0);
        surface.setTransparent(true);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);

        addContentView(surface, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT));
        renderer = new RestaurantModelRenderer(this);
        surface.setSurfaceRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN) { //Called once at the start of the touch
            //renderer.getCurrentCamera().setZ(renderer.getCurrentCamera().getZ()-3);
            //renderer.getCurrentCamera().setLookAt(0, renderer.getCurrentCamera().getLookAt().y + 5, 0);
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
