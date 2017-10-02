package com.example.asus.home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    Button login;
    TextView register;
    private ProgressDialog pDialog;
    JSONParser jParser_get = new JSONParser();
    JSONParser jParser_post = new JSONParser();
    private static String url_all = "http://163.14.68.37/android_connect/get_all.php";
    private static final String TAG_SUCCESS = "success";
    JSONArray boss_array = null;
    CheckBox checkBox;
    static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = (CheckBox) findViewById(R.id.auto);
        edittext_account = (EditText)findViewById(R.id.edittext_account);
        edittext_password = (EditText)findViewById(R.id.edittext_password);
        register = (TextView)findViewById(R.id.textView_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
        //自動填入帳密
        auto_fill();
        //帳密皆非空則自動登入
        if(validation_check()){
            User user = new User();
            user.setAccount(edittext_account.getText().toString().trim());
            user.setPassword(edittext_password.getText().toString().trim());
            new Login(user).execute();
            sendTokenToServer();
        }
        login = (Button)findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation_check()){
                    //存入checkbox status
                    prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    prefs.edit().putBoolean("check", checkBox.isChecked()).commit();
                    User user = new User();
                    user.setAccount(edittext_account.getText().toString().trim());
                    user.setPassword(edittext_password.getText().toString().trim());
                    new Login(user).execute();
                    sendTokenToServer();
                }
            }
        });

    }

    //檢查有沒有輸入帳號密碼
    private boolean validation_check(){
        if(edittext_account.getText().toString().trim().equals("") ||
                edittext_password.getText().toString().equals("")) return false;
        else return true;
    }
    //自動填入帳密
    private void auto_fill(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs.getBoolean("check", false)){
            checkBox.setChecked(true);
            edittext_account.setText(prefs.getString("account", null));
            edittext_password.setText(prefs.getString("password", null));
        }
    }

    //storing token to mysql server
    private void sendTokenToServer() {
        final String token = SharedPrefManager.getInstance(this).getDeviceToken();
        if (token == null) {
            //Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }
        new UpdateDeviceToken(token).execute();
    }

    class Login extends AsyncTask<String, String, String> {
        int id = -1;
        String type;
        String check = "false";
        User user;
        public Login(User user) {
            this.user = user;
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

        protected String doInBackground(String... args) {
            String account = user.getAccount();
            String password = user.getPassword();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", account));
            params.add(new BasicNameValuePair("password", password));
            JSONObject json = jParser_post.makeHttpRequest(url_all, "POST", params);

            Log.d("Create Response", json.toString());
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    prefs.edit().putString("account", account).commit();
                    prefs.edit().putString("password", password).commit();
                    boss_array = json.getJSONArray("boss_array");
                    JSONObject c = boss_array.getJSONObject(0);
                    id = c.getInt("id");
                    user.setID(id);
                    type = c.getString("userType");
                    user.setType(type);
                    check = "true";
                    Model.getInstance().setUser(user);

                }
                else{

                }
                return check;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            if(s.equals("false")){

            }
            else{
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HomePage.class);
                startActivity(intent);
            }
        }

    }
}