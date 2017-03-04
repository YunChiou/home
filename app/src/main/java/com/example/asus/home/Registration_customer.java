package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
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

public class Registration_customer extends ToolbarActivity {

    // Progress Dialog
    private ProgressDialog cDialog;
    JSONParser jsonParser = new JSONParser();
    EditText account;
    EditText password;
    EditText name;
    Button confirm;
    // url to create new product
    private static String url_create_customer = "http://163.14.68.37/android_connect/create_customer.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_customer);
        // Edit Text
        account = (EditText) findViewById(R.id.cus_act);
        password = (EditText) findViewById(R.id.cus_pwd);
        name = (EditText) findViewById(R.id.cus_name);
        // Create button
        confirm=(Button)findViewById(R.id.cus_confirm_button);
        // button click event
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Registration_customer.CreateNewCustomer().execute();

                Intent intent = new Intent();
                intent.setClass(Registration_customer.this, HomePage.class);
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

    class CreateNewCustomer extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cDialog = new ProgressDialog(Registration_customer.this);
            cDialog.setMessage("Creating Account..");
            cDialog.setIndeterminate(false);
            cDialog.setCancelable(true);
            cDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String string_account = account.getText().toString();
            String string_password = password.getText().toString();
            String string_name = name.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", string_account));
            params.add(new BasicNameValuePair("password", string_password));
            params.add(new BasicNameValuePair("name", string_name));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_customer,
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