package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class All_Restaurants extends NavigationbarActivity {

    private TextView textViewJSON;
    private Button buttonGet;

    public static final String MY_JSON ="MY_JSON";

    private static String url_get_all_restaurant = "http://163.14.68.37/android_connect/get_all_restaurant_details.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_all__restaurants, null, false);
        drawer.addView(contentView, 0);

        textViewJSON = (TextView) findViewById(R.id.textViewJSON);
        textViewJSON.setMovementMethod(new ScrollingMovementMethod());
        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJSON(url_get_all_restaurant);
            }
        });
    }
    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(All_Restaurants.this, "Please Wait...",null,true,true);
            }
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch(Exception e){
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                textViewJSON.setText(s);
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);
    }
}
