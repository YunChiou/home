package com.example.asus.home;

import android.os.AsyncTask;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class UpdateCurrentCustomer extends AsyncTask<String, String, String> {

    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    // url to update product
    private static final String url_update_table = "http://163.14.68.37/android_connect/update_current_customer.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    int tid;
    Table table;
    String condition;

    UpdateCurrentCustomer(Table table, String condition) {
        this.condition = condition;
        this.table = table;
        tid = table.getID();
    };

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //Saving product
    protected String doInBackground(String... args) {

        String String_text = table.getText();

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tid", Integer.toString(tid)));
        params.add(new BasicNameValuePair("cid", condition));

        try {
            JSONObject json = jsonParser.makeHttpRequest(url_update_table, "POST", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String file_url) {

    }
}
