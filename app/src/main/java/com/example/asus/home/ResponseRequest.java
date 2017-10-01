package com.example.asus.home;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.rajawali3d.renderer.Renderer;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ResponseRequest extends ToolbarActivity {

    Renderer renderer;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_request);

        final SurfaceView surface = new SurfaceView(this);
        surface.setFrameRate(60.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);

        LinearLayout rajawali = (LinearLayout)findViewById(R.id.rajawali);
        rajawali.addView(surface);
        renderer = new BasicRenderer(this);
        surface.setSurfaceRenderer(renderer);

        textView = (TextView)findViewById(R.id.textView2);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText("已確認併桌");            }
        });
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
