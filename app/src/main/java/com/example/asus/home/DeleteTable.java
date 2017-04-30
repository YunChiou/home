package com.example.asus.home;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DeleteTable extends AsyncTask<String, String, String> {

    JSONParser jsonParser = new JSONParser();
    private static final String url_delete_tables = "http://163.14.68.37/android_connect/delete_table.php";
    private static final String TAG_SUCCESS = "success";
    int tid;
    Table table;
    PaintBoard paintBoard;

    DeleteTable(Table table, PaintBoard paintBoard) {
        this.table = table;
        this.paintBoard = paintBoard;
        tid = table.getID();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... args) {
        int success;
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("tid", Integer.toString(tid)));
            JSONObject json = jsonParser.makeHttpRequest(url_delete_tables, "POST", params);
            Log.d("Delete Product", json.toString());
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(String file_url) {
        paintBoard.invalidate();
    }
}
