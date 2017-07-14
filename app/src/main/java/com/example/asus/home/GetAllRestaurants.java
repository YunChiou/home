package com.example.asus.home;
import android.graphics.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

public class GetAllRestaurants extends ToolbarActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_restaurants);


        String result = null;
        InputStream is = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://163.14.68.37/android_connect/get_all_restaurant.php");
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            Log.e("log_tag", "connection success");

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection" + e.toString());
            Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");

            }
            is.close();

            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result" + e.toString());
            Toast.makeText(getApplicationContext(), "Input reading fail", Toast.LENGTH_SHORT).show();

        }


        try {
            JSONArray jArray = new JSONArray(result);
            TableLayout tv = (TableLayout) findViewById(R.id.table);
            tv.removeAllViewsInLayout();
            int flag = 1;
            for (int i = -1; i < jArray.length() - 1; i++) {
                TableRow tr = new TableRow(GetAllRestaurants.this);
                tr.setLayoutParams(new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.FILL_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                if (flag == 1) {
                    TextView b6 = new TextView(GetAllRestaurants.this);
                    b6.setText("ID");
                    b6.setTextColor(Color.BLUE);
                    b6.setTextSize(15);
                    tr.addView(b6);
                    TextView b19 = new TextView(GetAllRestaurants.this);
                    b19.setPadding(10, 0, 0, 0);
                    b19.setTextSize(15);
                    b19.setText("storeame");
                    b19.setTextColor(Color.BLUE);
                    tr.addView(b19);
                    TextView b29 = new TextView(GetAllRestaurants.this);
                    b29.setPadding(10, 0, 0, 0);
                    b29.setText("phone");
                    b29.setTextColor(Color.BLUE);
                    b29.setTextSize(15);
                    tr.addView(b29);
                    TextView b39 = new TextView(GetAllRestaurants.this);
                    b39.setPadding(10, 0, 0, 0);
                    b39.setText("address");
                    b39.setTextColor(Color.BLUE);
                    b39.setTextSize(15);
                    tr.addView(b39);
                    tv.addView(tr);
                    final View vline = new View(GetAllRestaurants.this);
                    vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
                    vline.setBackgroundColor(Color.BLUE);
                    tv.addView(vline);
                    flag = 0;
                } else {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Log.i("log_tag", "id: " + json_data.getInt("ID") + ", storename: " + json_data.getString("storename") + ",phone:" + json_data.getString("phone") + ",address:" + json_data.getString("adress"));
                    TextView b = new TextView(GetAllRestaurants.this);
                    String stime = String.valueOf(json_data.getInt("ID"));
                    b.setText(stime);
                    b.setTextColor(Color.RED);
                    b.setTextSize(15);
                    tr.addView(b);
                    TextView b1 = new TextView(GetAllRestaurants.this);
                    b1.setPadding(10, 0, 0, 0);
                    b1.setTextSize(15);
                    String stime1 = json_data.getString("storename");
                    b1.setText(stime1);
                    b1.setTextColor(Color.BLACK);
                    tr.addView(b1);
                    TextView b2 = new TextView(GetAllRestaurants.this);
                    b2.setPadding(10, 0, 0, 0);
                    String stime2 = json_data.getString("phone");
                    b2.setText(stime2);
                    b2.setTextColor(Color.BLACK);
                    b2.setTextSize(15);
                    tr.addView(b2);
                    TextView b3 = new TextView(GetAllRestaurants.this);
                    b2.setPadding(10, 0, 0, 0);
                    String stime3 = json_data.getString("address");
                    b2.setText(stime3);
                    b3.setTextColor(Color.BLACK);
                    b3.setTextSize(15);
                    tr.addView(b3);
                    tv.addView(tr);
                    final View vline1 = new View(GetAllRestaurants.this);
                    vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                    vline1.setBackgroundColor(Color.WHITE);
                    tv.addView(vline1);
                }

            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data" + e.toString());
            Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
        }

    }
    //產生back arrow
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}