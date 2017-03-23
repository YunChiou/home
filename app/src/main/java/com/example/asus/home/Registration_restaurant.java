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

public class Registration_restaurant extends ToolbarActivity {

    private ProgressDialog cDialog;
    JSONParser jsonParser = new JSONParser();
    EditText inputname;
    EditText inputadd;
    EditText inputphone;
    Button confirm;
    private static String url_create_restaurant = "http://163.14.68.37/android_connect/create_restaurant.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_restaurant);

        inputname = (EditText) findViewById(R.id.shop_name);
        inputadd = (EditText) findViewById(R.id.shop_add);
        inputphone = (EditText) findViewById(R.id.shop_phone);
        confirm=(Button)findViewById(R.id.restaurant_confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Restaurant restaurant = new Restaurant();
                restaurant.setAddress(inputadd.getText().toString());
                restaurant.setStorename(inputname.getText().toString());
                restaurant.setPhone(inputphone.getText().toString());
                new Registration_restaurant.CreateNewRestaurant(restaurant).execute();

                Intent intent = new Intent();
                intent.setClass(Registration_restaurant.this, HomePage.class);
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
    class CreateNewRestaurant extends AsyncTask<String, String, String> {

        CreateNewRestaurant (Restaurant restaurant) {
            this.restaurant = restaurant;
        }
        int id = -1;
        Restaurant restaurant;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cDialog = new ProgressDialog(Registration_restaurant.this);
            cDialog.setMessage("Creating Account..");
            cDialog.setIndeterminate(false);
            cDialog.setCancelable(true);
            cDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            String storename = restaurant.getStorename();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String address = restaurant.getAddress();
            String phone = restaurant.getPhone();
            // Building Boss Parameters
            params.add(new BasicNameValuePair("name", storename));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("phone", phone));
            // getting JSON Object
            // Note that create product url accepts POST method

            JSONObject json = jsonParser.makeHttpRequest(url_create_restaurant,
                    "POST", params);
            // check log cat fro response
            Log.d("Create Response", json.toString());
            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    id = json.getInt(TAG_ID);
                    restaurant.setID(id);
                    Model.getInstance().setRestaurant(restaurant);
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

            if (id > 0) {
                restaurant.setID(id);

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
