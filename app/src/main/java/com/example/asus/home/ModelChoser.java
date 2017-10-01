package com.example.asus.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import org.rajawali3d.renderer.Renderer;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

public class ModelChoser extends ToolbarActivity {

    Renderer renderer;
    Renderer renderer2;
    Renderer renderer3;
    Renderer renderer4;
    Renderer renderer5;
    Renderer renderer6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_choser);

        final SurfaceView surface = new SurfaceView(this);
        surface.setFrameRate(60.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        LinearLayout one = (LinearLayout)findViewById(R.id.one);
        one.addView(surface);
        renderer = new ChooserRenderer(this, R.raw.bowling);
        surface.setSurfaceRenderer(renderer);

        final SurfaceView surface2 = new SurfaceView(this);
        surface2.setFrameRate(60.0);
        surface2.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        LinearLayout two = (LinearLayout)findViewById(R.id.two);
        two.addView(surface2);
        renderer2 = new ChooserRenderer(this, R.raw.spider_man_modern);
        surface2.setSurfaceRenderer(renderer2);

        final SurfaceView surface3 = new SurfaceView(this);
        surface3.setFrameRate(60.0);
        surface3.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        LinearLayout three = (LinearLayout)findViewById(R.id.three);
        three.addView(surface3);
        renderer3 = new ChooserRenderer(this, R.raw.minion);
        surface3.setSurfaceRenderer(renderer3);

        final SurfaceView surface4 = new SurfaceView(this);
        surface4.setFrameRate(60.0);
        surface4.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        LinearLayout four = (LinearLayout)findViewById(R.id.four);
        four.addView(surface4);
        renderer4 = new ChooserRenderer(this, R.raw.stormtrooper);
        surface4.setSurfaceRenderer(renderer4);

        final SurfaceView surface5 = new SurfaceView(this);
        surface5.setFrameRate(60.0);
        surface5.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        LinearLayout five = (LinearLayout)findViewById(R.id.five);
        five.addView(surface5);
        renderer5 = new ChooserRenderer(this, R.raw.mickey_mouse);
        surface5.setSurfaceRenderer(renderer5);

        final SurfaceView surface6 = new SurfaceView(this);
        surface6.setFrameRate(60.0);
        surface6.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);
        LinearLayout six = (LinearLayout)findViewById(R.id.six);
        six.addView(surface6);
        renderer6 = new ChooserRenderer(this, R.raw.skull);
        surface6.setSurfaceRenderer(renderer6);
    }

    //產生back arrow
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
