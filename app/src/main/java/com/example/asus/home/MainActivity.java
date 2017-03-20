package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edittext_account;
    EditText edittext_password;
    Button register;
    Button login;
    TextView login_hint;
    private ProgressDialog pDialog;
    JSONParser jParser_get = new JSONParser();
    JSONParser jParser_post = new JSONParser();
    private static String url_all = "http://163.14.68.37/android_connect/get_all.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_BOSS = "boss";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_ACCOUNT = "account";
    JSONArray boss_array = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext_account = (EditText)findViewById(R.id.edittext_account);
        edittext_password = (EditText)findViewById(R.id.edittext_password);
        login_hint = (TextView)findViewById(R.id.login_hint);
        register = (Button)findViewById(R.id.button_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
        login = (Button)findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boss boss = new Boss();
                boss.setAccount(edittext_account.getText().toString().trim());
                boss.setPassword(edittext_password.getText().toString().trim());
                new Login(boss).execute();
            }
        });

    }
    class Login extends AsyncTask<String, String, String> {
        int id = -1;
        String check = "false";
        Boss boss;
        public Login(Boss boss) {
            this.boss = boss;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */


        protected String doInBackground(String... args) {
            String account = boss.getAccount();
            String password = boss.getPassword();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", account));
            params.add(new BasicNameValuePair("password", password));
            // getting JSON string from URL
            JSONObject json = jParser_post.makeHttpRequest(url_all,
                    "POST", params);
            // Check your log cat for JSON reponse

            Log.d("Create Response", json.toString());
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    boss_array = json.getJSONArray("boss_array");
                    JSONObject c = boss_array.getJSONObject(0);
                    id = c.getInt("bid");
                    check = "true";
                   // Boss boss = new Boss(id, c.getString("account"), c.getString("password"), "", "", "", "");
                    //Model.getInstance().setBoss(boss);
                }
                return check;
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
            if(s.equals("false"))
                login_hint.setText("帳號或密碼錯誤");
            else{
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HomePage.class);
                startActivity(intent);
            }



        }
    }



}