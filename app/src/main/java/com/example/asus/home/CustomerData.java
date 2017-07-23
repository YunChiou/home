package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

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

import static android.R.attr.bitmap;

public class CustomerData extends ToolbarActivity {

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
     Button button_next;
     Button button_pre;

    private ImageSwitcher sw;//圖片轉換

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdata);


        // Hashmap for ListView
        customersList = new ArrayList<HashMap<String, String>>();
        textViewJSON = (TextView) findViewById(R.id.textViewJSON);

        nameText = (TextView) findViewById(R.id.name);
        accountText = (TextView) findViewById(R.id.account);
        passwordText = (TextView) findViewById(R.id. password);
        button_next = (Button)findViewById(R.id.button_next);
        button_pre = (Button)findViewById(R.id.button_pre);
        sw = (ImageSwitcher) findViewById(R.id.image);

        // Loading products in Background Thread
        new LoadAllProducts().execute();
        //ItemsToShow(Model.getInstance().getUser().getType());
        //圖片轉換
        sw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView(){
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });
        button_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sw.setImageResource(R.drawable.badmintain);
            }
        });
        button_pre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sw.setImageResource(R.drawable.baseball1);
            }
        });
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
            //textViewJSON.setText(json.toString());
            try {
                //JSONArray value = json.getJSONArray("product");
                //JSONObject userObject = value.getJSONObject(0);
                JSONObject value = json.getJSONArray("product").getJSONObject(0);
                nameText.setText("姓名　"+value.getString("name"));
                accountText.setText("帳號　"+value.getString("account"));
                passwordText.setText("密碼　"+value.getString("password"));
                qrcode(getQRcodeValue(value.getString("id")));
            }catch(Exception e) {
            }
        }
    }

    //創造QRCode
    protected String getQRcodeValue(String id) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(id);
        buffer.append(name +"，");
        buffer.append(account);
        return buffer.toString();
    }

    /*public static Bitmap mergeBitmaps(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(),
        bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
   }*/

   public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == android.R.id.home) {
           finish(); // close this activity and return to preview activity (if there is any)
       }
       return super.onOptionsItemSelected(item);
   }
    void qrcode(String value) {
        QRCodeWriter writer = new QRCodeWriter();
        //從drawable抓圖片
        Bitmap mylogo1 = BitmapFactory.decodeResource(getResources(), R.drawable.badmintain);
        Bitmap mylogo2 = BitmapFactory.decodeResource(getResources(), R.drawable.baseball1);
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
            //((ImageView) findViewById(R.id.image)).setImageBitmap(mergeBitmaps(bmp,mylogo));
            // Drawable d = new BitmapDrawable(bmp);
            //sw.setImageDrawable(d);
            //sw.setImageDrawable(new BitmapDrawable(getResources(),bmp));
            //((ImageView) findViewById(R.id.image)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
