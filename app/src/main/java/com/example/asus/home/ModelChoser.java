package com.example.asus.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import org.rajawali3d.renderer.Renderer;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

public class ModelChoser extends ToolbarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_choser);

        //setModel(R.id.one, R.raw.spider_man_modern);
        setModel(R.id.two, R.raw.bowling);
        setModel(R.id.three, R.raw.minion);
        //setModel(R.id.four, R.raw.stormtrooper);
        //setModel(R.id.five, R.raw.mickey_mouse);
        setModel(R.id.six, R.raw.skull);
    }

    public void setModel(int number, int model) {
        SurfaceView surface = new SurfaceView(this);
        surface.setFrameRate(30.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        //surface.setRenderMode(ISurface.RENDERMODE_CONTINUOUSLY);
        LinearLayout one = (LinearLayout)findViewById(number);
        one.addView(surface);
        Renderer renderer = new ChooserRenderer(this, model);
        surface.setSurfaceRenderer(renderer);
    }

    //產生back arrow
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
