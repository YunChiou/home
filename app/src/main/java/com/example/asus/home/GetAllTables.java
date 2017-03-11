package com.example.asus.home;


import android.os.AsyncTask;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class GetAllTables extends AsyncTask<String, String, String> {

    ArrayList allTables;

    GetAllTables(ArrayList<Table> allTables) {
        this.allTables = allTables;
    };

    JSONParser jsonParser = new JSONParser();
    // url to get all products list
    private static String url_all_tables = "http://163.14.68.37/android_connect/get_all_tables.php";
    // JSON Node names
    private static final String TAG_TABLES = "tables";
    private static final String TAG_TID = "tid";
    private static final String TAG_TABLETYPE = "tableType";
    private static final String TAG_LEFTINDEX = "leftIndex";
    private static final String TAG_TOPINDEX = "topIndex";
    private static final String TAG_WIDTH = "width";
    private static final String TAG_HEIGHT = "height";
    private static final String TAG_TEXT = "text";
    JSONObject json;
    JSONArray tables = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... args) {


        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        try {
            json = jsonParser.makeHttpRequest(url_all_tables, "GET", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String file_url) {
        try {
            tables = json.getJSONArray(TAG_TABLES);
            for (int i = 0; i < tables.length(); i++) {
                JSONObject c = tables.getJSONObject(i);
                int id = c.getInt(TAG_TID);
                String tableType = c.getString(TAG_TABLETYPE);
                int int_leftIndex = c.getInt(TAG_LEFTINDEX);
                int int_topIndex = c.getInt(TAG_TOPINDEX);
                int int_width = c.getInt(TAG_WIDTH);
                int int_height = c.getInt(TAG_HEIGHT);
                String String_text = c.getString(TAG_TEXT);

                if (tableType.equals("R"))
                    allTables.add(new RectangleTable(id, tableType, int_leftIndex, int_topIndex, int_width, int_height, String_text));
                else if (tableType.equals("O"))
                    allTables.add(new OvalTable(id, tableType, int_leftIndex, int_topIndex, int_width, int_height, String_text));
            }
        } catch (Exception e) {
        }

    }
}
