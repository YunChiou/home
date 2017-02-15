package com.example.asus.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class HomePage extends AppCompatActivity {
Button layoutsettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        layoutsettings=(Button)findViewById(R.id.button_layoutsettings);
        layoutsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePage.this, android.widget.TableLayout.class);
                startActivity(intent);
            }
        });
    }
}
