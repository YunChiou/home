package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Context;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Registration_customer extends ToolbarActivity {

    private ProgressDialog cDialog;
    JSONParser jsonParser = new JSONParser();
    EditText inputaccount;
    EditText inputpassword;
    EditText inputname;
    EditText inputdes;
    EditText inputsale;
    Button confirm;
    // url to create new product
    private static String url_create_customer = "http://163.14.68.37/android_connect/create_customer.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_customer);
        // Edit Text
        inputaccount = (EditText) findViewById(R.id.cus_act);
        inputpassword = (EditText) findViewById(R.id.cus_pwd);
        inputname = (EditText) findViewById(R.id.cus_name);
        inputdes = (EditText) findViewById(R.id.cus_des);
        inputsale = (EditText) findViewById(R.id.cus_sale);
        // Create button
        confirm=(Button)findViewById(R.id.cus_confirm_button);
        // button click event
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                User user = new User();
                user.setAccount(inputaccount.getText().toString());
                user.setName(inputname.getText().toString());
                user.setPassword(inputpassword.getText().toString());
                new Registration_customer.CreateNewCustomer(user).execute();
=======

                Customer customer = new Customer();
                customer.setAccount(inputaccount.getText().toString());
                customer.setName(inputname.getText().toString());
                customer.setPassword(inputpassword.getText().toString());
                customer.setdes(inputdes.getText().toString());
                customer.setsale(inputsale.getText().toString());

                new Registration_customer.CreateNewCustomer(customer).execute();
>>>>>>> 6e30231a1ab95f4563e1bb6fd28f7e3dc4541fb1

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

        int id = -1;
        User user;

        CreateNewCustomer (User user) {
            this.user = user;
        }

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

<<<<<<< HEAD
            String account = user.getAccount();
            String password = user.getPassword();
            String name = user.getName();
=======
            String account = customer.getAccount();
            String password = customer.getPassword();
            String name = customer.getName();
            String description = customer.getdes();
            String sale = customer.getsale();

>>>>>>> 6e30231a1ab95f4563e1bb6fd28f7e3dc4541fb1
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", account));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("name", name));
<<<<<<< HEAD
=======
            params.add(new BasicNameValuePair("description", description));
            params.add(new BasicNameValuePair("sale", sale));
            // getting JSON Object
            // Note that create product url accepts POST method
>>>>>>> 6e30231a1ab95f4563e1bb6fd28f7e3dc4541fb1

            JSONObject json = jsonParser.makeHttpRequest(url_create_customer,
                    "POST", params);

            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    id = json.getInt(TAG_ID);
                    user.setID(id);
                    Model.getInstance().setUser(user);
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
            if (id > 0) {
                user.setID(id);
                cDialog.setMessage("註冊成功！");
                cDialog.setIndeterminate(false);
                cDialog.setCancelable(true);
                cDialog.show();
            }
            else {
                cDialog.setMessage("註冊失敗！");
                cDialog.setIndeterminate(false);
                cDialog.setCancelable(true);
                cDialog.show();
            }
        }
    }
}