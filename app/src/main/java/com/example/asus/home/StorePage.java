package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StorePage extends NavigationbarActivity {

    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jsonParser = new JSONParser();
    JSONObject json;
    ArrayList<HashMap<String, String>> customersList;

    // url to get all products list
    private static String url_all_restaurant = "http://163.14.68.37/android_connect/get_restaurant_details.php";
    TextView storenameText;
    TextView phoneText;
    TextView addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_store_page);
        //產生sliding menu
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_store_page, null, false);
        drawer.addView(contentView, 0);

        storenameText = (TextView) findViewById(R.id.storename);
        phoneText = (TextView) findViewById(R.id.phone);
        addressText = (TextView) findViewById(R.id. address);

        ImageButton fb = (ImageButton)findViewById(R.id.fb);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri fb = Uri.parse("https://www.facebook.com/newseoul/?rf=271866176233316");
                Intent intent = new Intent(Intent.ACTION_VIEW,fb);
                startActivity(intent);
            }
        });

        new StorePage.LoadAllProducts().execute();
    }

    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(StorePage.this);
            pDialog.setMessage("Loading user data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            int currentID = Model.getInstance().getRestaurant().getID();
            params.add(new BasicNameValuePair("rid", currentID + ""));

            try {
                json = jsonParser.makeHttpRequest(url_all_restaurant, "GET", params);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/


        @Override
        protected void onPostExecute(String s)
        {
            pDialog.dismiss();
            //textViewJSON.setText(json.toString());
            try {
                //JSONArray value = json.getJSONArray("product");
                //JSONObject userObject = value.getJSONObject(0);
                JSONObject value = json.getJSONArray("product").getJSONObject(0);
                storenameText.setText("店家名稱 : "+value.getString("name"));
                phoneText.setText("電話  : "+value.getString("phone"));
                addressText.setText("地址 : "+value.getString("address"));
            }catch(Exception e) {

            }
        }
    }

}
