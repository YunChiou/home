package com.example.asus.home;
import android.graphics.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

public class GetAllRestaurants extends ToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView text;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_restaurants);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        connect();
    }
    private void connect() {
        String data;
        List<String> r = new ArrayList<String>();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,r);
        ListView list=(ListView)findViewById(R.id.listView1);
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://163.14.68.37/android_connect/get_all_restaurant.php");
            HttpResponse response = client.execute(request);
            HttpEntity entity=response.getEntity();
            data= EntityUtils.toString(entity);
            Log.e("STRING", data);

            try {

                JSONArray json=new JSONArray(data);
                for(int i=0;i<json.length(); i++)
                {
                    JSONObject obj=json.getJSONObject(i);
                    String rid = String.valueOf(obj.getInt("rid"));
                    String name=obj.getString("name");
                    String phone=obj.getString("phone");
                    String address=obj.getString("address");
                    Log.e("STRING", rid);
                    r.add(rid);
                    r.add(name);
                    r.add(phone);
                    r.add(address);
                    list.setAdapter(adapter);

                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (ClientProtocolException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
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