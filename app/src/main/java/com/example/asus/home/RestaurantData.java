package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

public class RestaurantData extends NavigationbarActivity {

    private ProgressDialog pDialog;
    private TextView textViewJSON;
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
        setContentView(R.layout.activity_restaurant_data);

        //產生sliding menu
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_restaurant_data, null, false);
        drawer.addView(contentView, 0);

        // Hashmap for ListView
        customersList = new ArrayList<HashMap<String, String>>();
        textViewJSON = (TextView) findViewById(R.id.textViewJSON);

        storenameText = (TextView) findViewById(R.id.storename);
       phoneText = (TextView) findViewById(R.id.phone);
       addressText = (TextView) findViewById(R.id. address);

        // Loading products in Background Thread
        new RestaurantData.LoadAllProducts().execute();
        //QRCode
        qrcode(getQRcodeValue());
    }
    //宣告
    String storename ;
    String phone ;
    String address ;

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
            pDialog = new ProgressDialog(RestaurantData.this);
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
            textViewJSON.setText(json.toString());
            try {
                //JSONArray value = json.getJSONArray("product");
                //JSONObject userObject = value.getJSONObject(0);
                JSONObject value = json.getJSONArray("product").getJSONObject(0);
                storenameText.setText("店家名稱"+value.getString("name"));
                phoneText.setText("電話"+value.getString("phone"));
                addressText.setText("地址"+value.getString("address"));
            }catch(Exception e) {

            }
        }
    }

    //創造QRCode創造QRCode
    protected String getQRcodeValue() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(storename + "，");
        buffer.append( phone +"，");
        buffer.append(address);
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
}
