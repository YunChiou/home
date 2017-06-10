package com.example.asus.home;

import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateDeviceToken extends AsyncTask<String, String, String> {

    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static final String URL_REGISTER_DEVICE = "http://163.14.68.37/android_connect/UpdateDeviceToken.php";

    String token;

    UpdateDeviceToken(String token) {
        this.token = token;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... args) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", Integer.toString(Model.getInstance().getUser().getID())));
        params.add(new BasicNameValuePair("token", token));

        try {
            JSONObject json = jsonParser.makeHttpRequest(URL_REGISTER_DEVICE, "POST", params);
            int success = json.getInt(TAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String file_url) {

    }
}
