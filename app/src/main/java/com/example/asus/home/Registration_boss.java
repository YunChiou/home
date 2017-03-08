package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Registration_boss extends ToolbarActivity {

    // Progress Dialog
    private ProgressDialog cDialog;
    JSONParser jsonParser = new JSONParser();
    EditText inputaccount;
    EditText inputphone;
    EditText inputaddress;
    EditText inputstorename;
    EditText inputpassword;
    EditText inputname;
    Button confirm;
    private static String url_create_boss = "http://163.14.68.37/android_connect/create_boss.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_boss);
        inputaccount = (EditText) findViewById(R.id.boss_act);
        inputpassword = (EditText) findViewById(R.id.boss_pwd);
        inputname = (EditText) findViewById(R.id.boss_name);
        inputaddress = (EditText) findViewById(R.id.boss_storeadd);
        inputphone = (EditText) findViewById(R.id.boss_storephone);
        inputstorename = (EditText) findViewById(R.id.boss_storename);
        confirm=(Button)findViewById(R.id.boss_confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = inputaccount.getText().toString();
                String password = inputpassword.getText().toString();
                String name = inputname.getText().toString();
                String phone = inputphone.getText().toString();
                String address = inputaddress.getText().toString();
                String storename = inputstorename.getText().toString();
                new Registration_boss.CreateNewBoss().execute(account,password,name,phone,address,storename);

                Intent intent = new Intent();
                intent.setClass(Registration_boss.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
    class CreateNewBoss extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cDialog = new ProgressDialog(Registration_boss.this);
            cDialog.setMessage("Creating Account..");
            cDialog.setIndeterminate(false);
            cDialog.setCancelable(true);
            cDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            String name=args[0];
            String account=args[1];
            String password=args[2];
            String phone=args[3];
            String address=args[4];
            String storename=args[5];

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", account));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("phone", phone));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("storename", storename));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_boss,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    // successfully created product

                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            //cDialog.dismiss();
            cDialog.setMessage("註冊成功！");
            cDialog.setIndeterminate(false);
            cDialog.setCancelable(true);
            cDialog.show();
        }

    }
}
