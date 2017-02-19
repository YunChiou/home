package com.example.asus.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class StorePage extends NavigationbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_store_page);
        //產生sliding menu
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_store_page, null, false);
        drawer.addView(contentView, 0);

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
