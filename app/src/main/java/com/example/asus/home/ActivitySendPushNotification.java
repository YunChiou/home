package com.example.asus.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitySendPushNotification extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSendPush;
    private ProgressDialog progressDialog;
    private EditText editTextTitle, editTextMessage;
    private List<String> devices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_push_notification);

        buttonSendPush = (Button) findViewById(R.id.buttonSendPush);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        devices = new ArrayList<>();
        buttonSendPush.setOnClickListener(this);
    }

    private void sendSinglePush(){
        final String title = editTextTitle.getText().toString();
        final String message = editTextMessage.getText().toString();
        //final String id = "86";
        Intent intent = getIntent();
        final String id = intent.getStringExtra("tableId");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://163.14.68.37/android_connect/sendSinglePush.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ActivitySendPushNotification.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("message", message);
                params.put("id", id);
                return params;
            }
        };
        MyVolley.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        sendSinglePush();
    }
}

