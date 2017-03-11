package com.example.asus.home;


import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateTable extends AsyncTask<String, String, String> {

    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    // url to update product
    private static final String url_update_table = "http://163.14.68.37/android_connect/update_table.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    int tid;
    Table table;

    UpdateTable(Table table) {
        this.table = table;
        tid = table.getID();
    };

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //Saving product
    protected String doInBackground(String... args) {

        int int_leftIndex = table.getLeft();
        int int_topIndex = table.getTop();
        int int_width = table.getWidth();
        int int_height = table.getHeight();
        String String_text = table.getText();

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tid", Integer.toString(tid)));
        params.add(new BasicNameValuePair("leftIndex", Integer.toString(int_leftIndex)));
        params.add(new BasicNameValuePair("topIndex", Integer.toString(int_topIndex)));
        params.add(new BasicNameValuePair("width", Integer.toString(int_width)));
        params.add(new BasicNameValuePair("height", Integer.toString(int_height)));
        params.add(new BasicNameValuePair("text", String_text));

        try {
            JSONObject json = jsonParser.makeHttpRequest(url_update_table, "POST", params);
            int success = json.getInt(TAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String file_url) {

    }
}
