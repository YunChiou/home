package com.example.asus.home;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StorePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page2);

        ImageButton fb = (ImageButton)findViewById(R.id.fb);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri fb = Uri.parse("https://www.facebook.com/newseoul/?rf=271866176233316");
                Intent intent = new Intent(Intent.ACTION_VIEW,fb);
                startActivity(intent);
            }
        });
    }
}
