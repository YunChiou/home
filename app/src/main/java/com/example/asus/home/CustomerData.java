package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerData extends AppCompatActivity  {

    // Progress Dialog
    private ProgressDialog pDialog;
    private TextView textViewJSON;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> customersList;

    // url to get all products list
    private static String url_all_customers = "http://163.14.68.37/android_connect/get_all_customers.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CUSTOMERS = "customers";
    private static final String TAG_CID = "cid";
    private static final String TAG_NAME = "name";
    private static final String TAG_ACCOUNT = "account";

    // products JSONArray
    JSONArray customers = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdata);

        // Hashmap for ListView
        customersList = new ArrayList<HashMap<String, String>>();
        textViewJSON = (TextView) findViewById(R.id.textViewJSON);
        // Loading products in Background Thread
        new LoadAllProducts().execute();
        //QRCode
        inputValue();

    }
    //宣告
    JSONObject c;
    String id ;
    String name ;
    String account ;

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CustomerData.this);
            pDialog.setMessage("Loading customer data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */


        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_customers, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    customers = json.getJSONArray(TAG_CUSTOMERS);

                    JSONObject c = customers.getJSONObject(3);

                    // Storing each json item in variable
                    String id = c.getString(TAG_CID);
                    String name = c.getString(TAG_NAME);
                    String account = c.getString(TAG_ACCOUNT);
                    return id + "\n姓名" + name + "\n帳號" + account;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            textViewJSON.setText(s);
        }
    }
    //創造QRCode創造QRCode
    protected String getQRcodeValue() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(id + "，");
        buffer.append( name +"，");
        buffer.append(account);
        return buffer.toString();
    }
    void qrcode(String value) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode( value , BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) findViewById(R.id.image)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    private void inputValue() {
        Button button = (Button) findViewById(R.id.gen_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrcode(getQRcodeValue());
            }
        });

    }
}
