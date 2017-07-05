package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.AsyncTask;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerData extends NavigationbarActivity  {

    // Progress Dialog
    private ProgressDialog pDialog;
    private TextView textViewJSON;
    // Creating JSON Parser object
    JSONParser jsonParser = new JSONParser();
    JSONObject json;
    ArrayList<HashMap<String, String>> customersList;

    // url to get all products list
    private static String url_all_users = "http://163.14.68.37/android_connect/get_user_details.php";
     TextView nameText;
     TextView accountText;
     TextView passwordText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //產生sliding menu
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_customerdata, null, false);
        drawer.addView(contentView, 0);

        // Hashmap for ListView
        customersList = new ArrayList<HashMap<String, String>>();
        textViewJSON = (TextView) findViewById(R.id.textViewJSON);

        nameText = (TextView) findViewById(R.id.name);
        accountText = (TextView) findViewById(R.id.account);
        passwordText = (TextView) findViewById(R.id. password);

        // Loading products in Background Thread
        new LoadAllProducts().execute();
        ItemsToShow(Model.getInstance().getUser().getType());
    }

    String id ;
    String name ;
    String account ;

    class LoadAllProducts extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CustomerData.this);
            pDialog.setMessage("Loading user data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            int currentID = Model.getInstance().getUser().getID();
            params.add(new BasicNameValuePair("id", currentID + ""));

            try {
                json = jsonParser.makeHttpRequest(url_all_users, "GET", params);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {
            pDialog.dismiss();
            textViewJSON.setText(json.toString());
            try {
                //JSONArray value = json.getJSONArray("product");
                //JSONObject userObject = value.getJSONObject(0);
                JSONObject value = json.getJSONArray("product").getJSONObject(0);
                nameText.setText("姓名"+value.getString("name"));
                accountText.setText("帳號"+value.getString("account"));
                passwordText.setText("密碼"+value.getString("password"));
                qrcode(getQRcodeValue(value.getString("id")));
            }catch(Exception e) {

            }
        }
    }

    //創造QRCode
    protected String getQRcodeValue(String id) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(id);
        //buffer.append( name +"，");
        //buffer.append(account);
        return buffer.toString();
    }
    public static Bitmap mergeBitmaps(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(),
             bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }

   // public Bitmap mergeBitmaps(Bitmap bmp1, Bitmap bmp2) {
    //    Bitmap combined = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
     //   Canvas canvas = new Canvas(combined);
      //  canvas.drawBitmap(bmp1, new Matrix(), null);
       // int centreX = bmp2.getWidth() - bmp2.getWidth() /2;
        //int centreY = (bmp2.getHeight() - bmp2.getHeight()) /2 ;
        //canvas.drawBitmap(bmp2,centreX,centreY, null);
        //return combined;
    //}

    void qrcode(String value) {
        QRCodeWriter writer = new QRCodeWriter();
        //從drawable抓圖片
        Bitmap mylogo = BitmapFactory.decodeResource(getResources(), R.drawable.badmintain);
        try {
            BitMatrix bitMatrix = writer.encode(value , BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) findViewById(R.id.image)).setImageBitmap(mergeBitmaps(bmp,mylogo));

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
