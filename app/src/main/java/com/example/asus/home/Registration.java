package com.example.asus.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity {
    Button boss;
    Button cus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        boss = (Button)findViewById(R.id.button_boss);
        boss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Registration.this, Registration_boss.class);
                startActivity(intent);
            }
        });
        cus=(Button)findViewById(R.id.button_customer);
        cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Registration.this, Registration_customer.class);
                startActivity(intent);
            }
        });

}

}
