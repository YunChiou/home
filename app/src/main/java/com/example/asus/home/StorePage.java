package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
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

public class StorePage extends ToolbarActivity {

    private ImageView p1;
    private ImageView p2;

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
        setContentView(R.layout.activity_store_page);

        storenameText = (TextView) findViewById(R.id.storename);
        phoneText = (TextView) findViewById(R.id.phone);
        addressText = (TextView) findViewById(R.id. address);
        p1 = (ImageView)findViewById(R.id.imageView);
        p1.setOnTouchListener(new TouchListener());
        p2 = (ImageView)findViewById(R.id.imageView3);
        p2.setOnTouchListener(new TouchListener());

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
    //產生back arrow
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
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

    private final class TouchListener implements View.OnTouchListener {

        /** 記錄是拖拉照片模式還是放大縮小照片模式 */
        private int mode = 0;// 初始狀態
        /** 拖拉照片模式 */
        private static final int MODE_DRAG = 1;
        /** 放大縮小照片模式 */
        private static final int MODE_ZOOM = 2;

        /** 用於記錄開始時候的坐標位置 */
        private PointF startPoint = new PointF();
        /** 用於記錄拖拉圖片移動的坐標位置 */
        private Matrix matrix = new Matrix();
        /** 用於記錄圖片要進行拖拉時候的坐標位置 */
        private Matrix currentMatrix = new Matrix();

        /** 兩個手指的開始距離 */
        private float startDis;
        /** 兩個手指的中間點 */
        private PointF midPoint;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            /** 通過與運算保留最後八位 MotionEvent.ACTION_MASK = 255 */
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                // 手指壓下屏幕
                case MotionEvent.ACTION_DOWN:
                    mode = MODE_DRAG;
                    // 記錄ImageView當前的移動位置
                    currentMatrix.set(p1.getImageMatrix());
                    currentMatrix.set(p2.getImageMatrix());
                    startPoint.set(event.getX(), event.getY());
                    break;
                // 手指在屏幕上移動，改事件會被不斷觸發
                case MotionEvent.ACTION_MOVE:
                    // 拖拉圖片
                    if (mode == MODE_DRAG) {
                        float dx = event.getX() - startPoint.x; // 得到x軸的移動距離
                        float dy = event.getY() - startPoint.y; // 得到x軸的移動距離
                        // 在沒有移動之前的位置上進行移動
                        matrix.set(currentMatrix);
                        matrix.postTranslate(dx, dy);
                    }
                    // 放大縮小圖片
                    else if (mode == MODE_ZOOM) {
                        float endDis = distance(event);// 結束距離
                        if (endDis > 10f) { // 兩個手指並攏在一起的時候像素大於10
                            float scale = endDis / startDis;// 得到縮放倍數
                            matrix.set(currentMatrix);
                            matrix.postScale(scale, scale,midPoint.x,midPoint.y);
                        }
                    }
                    break;
                // 手指離開屏幕
                case MotionEvent.ACTION_UP:
                    // 當觸點離開屏幕，但是屏幕上還有觸點(手指)
                case MotionEvent.ACTION_POINTER_UP:
                    mode = 0;
                    break;
                // 當屏幕上已經有觸點(手指)，再有一個觸點壓下屏幕
                case MotionEvent.ACTION_POINTER_DOWN:
                    mode = MODE_ZOOM;
                    /** 計算兩個手指間的距離 */
                    startDis = distance(event);
                    /** 計算兩個手指間的中間點 */
                    if (startDis > 10f) { // 兩個手指並攏在一起的時候像素大於10
                        midPoint = mid(event);
                        //記錄當前ImageView的縮放倍數
                        currentMatrix.set(p1.getImageMatrix());
                        currentMatrix.set(p2.getImageMatrix());
                    }
                    break;
            }
            p1.setImageMatrix(matrix);
            p2.setImageMatrix(matrix);
            return true;
        }

        /** 計算兩個手指間的距離 */
        private float distance(MotionEvent event) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            /** 使用勾股定理返回兩點之間的距離 */
            return (float) Math.sqrt(dx * dx + dy * dy);
        }

        /** 計算兩個手指間的中間點 */
        private PointF mid(MotionEvent event) {
            float midX = (event.getX(1) + event.getX(0)) / 2;
            float midY = (event.getY(1) + event.getY(0)) / 2;
            return new PointF(midX, midY);
        }

    }

}
