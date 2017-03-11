package com.example.asus.home;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class CreateNewTable extends AsyncTask<String, String, String> {

    private ProgressDialog cDialog;
    JSONParser jsonParser = new JSONParser();
    // url to create new product
    private static String url_create_table = "http://163.14.68.37/android_connect/create_table.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID = "id";
    Table table;
    int id = -1;

    CreateNewTable(Table table) {
        this.table = table;
    };

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // Creating table
    protected String doInBackground(String... args) {

        String String_tableType = table.getTableType();
        int int_leftIndex = table.getLeft();
        int int_topIndex = table.getTop();
        int int_width = table.getWidth();
        int int_height = table.getHeight();
        String String_text = table.getText();

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tableType", String_tableType));
        params.add(new BasicNameValuePair("leftIndex", Integer.toString(int_leftIndex)));
        params.add(new BasicNameValuePair("topIndex", Integer.toString(int_topIndex)));
        params.add(new BasicNameValuePair("width", Integer.toString(int_width)));
        params.add(new BasicNameValuePair("height", Integer.toString(int_height)));
        params.add(new BasicNameValuePair("text", String_text));

        // check for success tag
        try {
            JSONObject json = jsonParser.makeHttpRequest(url_create_table, "POST", params);
            int success = json.getInt(TAG_SUCCESS);
            if (success == 1)
                id = json.getInt(TAG_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // After completing background task Dismiss the progress dialog
    protected void onPostExecute(String file_url) {
        if (id >= 0)
            table.setID(id);
        else {
            cDialog.setMessage("儲存失敗");
            cDialog.setIndeterminate(false);
            cDialog.setCancelable(true);
            cDialog.show();
        }
    }
}
