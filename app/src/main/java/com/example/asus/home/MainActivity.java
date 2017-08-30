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
    TextView login_hint;
    TextView register;
    private ProgressDialog pDialog;
    JSONParser jParser_get = new JSONParser();
    JSONParser jParser_post = new JSONParser();
    private static String url_all = "http://163.14.68.37/android_connect/get_all.php";
    private static final String TAG_SUCCESS = "success";
    JSONArray boss_array = null;
    static SharedPreferences prefs;
    CheckBox check;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext_account = (EditText)findViewById(R.id.edittext_account);
        edittext_password = (EditText)findViewById(R.id.edittext_password);
        login_hint = (TextView)findViewById(R.id.login_hint);
        check = (CheckBox) findViewById(R.id.auto);
        register = (TextView)findViewById(R.id.textView_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
        //自動填入帳號密碼
        auto_login();
        //如帳密皆非空就自動登入
        if(validation_check()){
            user = new User();
            user.setAccount(edittext_account.getText().toString().trim());
            user.setPassword(edittext_password.getText().toString().trim());
            prefs.edit().putBoolean("check",check.isChecked()).commit();
            new Login(user).execute();
            sendTokenToServer();
        }
        //手動的登入
        login = (Button)findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation_check()){
                    user = new User();
                    user.setAccount(edittext_account.getText().toString().trim());
                    user.setPassword(edittext_password.getText().toString().trim());
                    prefs.edit().putBoolean("check",check.isChecked()).commit();
                    new Login(user).execute();
                    sendTokenToServer();
                }
                else{//帳密提示字
                    if(edittext_account.getText().toString().trim().equals("")){
                        if(edittext_password.getText().toString().equals(""))
                            login_hint.setText("請輸入帳號和密碼");
                        else
                            login_hint.setText("請輸入帳號");
                    }
                    else if(edittext_password.getText().toString().trim().equals("")){
                        if(edittext_account.getText().toString().equals(""))
                            login_hint.setText("請輸入帳號和密碼");
                        else
                            login_hint.setText("請輸入密碼");
                    }

                }
            }
        });

    }
    //自動填入帳號密碼
    private void auto_login(){
        prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        check.setChecked(prefs.getBoolean("check", false));
        //如果上次有勾選"自動登入"
        if(prefs.getBoolean("check", false)){
            //自動填入帳號與密碼
            edittext_account.setText(prefs.getString("account", null));
            edittext_password.setText(prefs.getString("password", null));
        }
    }
    //檢查有沒有輸入帳號密碼
    private boolean validation_check(){
        if(edittext_account.getText().toString().trim().equals("") ||
                edittext_password.getText().toString().equals("")) return false;
        else return true;

    }
    //storing token to mysql server
    private void sendTokenToServer() {
        final String token = SharedPrefManager.getInstance(this).getDeviceToken();
        if (token == null) {
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
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

        /**
         * getting All products from url
         * */



        protected String doInBackground(String... args) {
            String account = user.getAccount();
            String password = user.getPassword();

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
                    id = c.getInt("id");
                    user.setID(id);
                    type = c.getString("userType");
                    user.setType(type);
                    check = "true";
                    Model.getInstance().setUser(user);
                    //自動登入功能
                    prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    prefs.edit().putString("account",account).commit();
                    prefs.edit().putString("password",password).commit();



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
            if(s.equals("false"))
                login_hint.setText("帳號或密碼錯誤");
            else{
                login_hint.setText("");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HomePage.class);
                startActivity(intent);
            }
        }

    }

}