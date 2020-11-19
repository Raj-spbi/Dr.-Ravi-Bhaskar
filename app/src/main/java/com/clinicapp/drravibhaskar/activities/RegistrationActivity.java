package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;
import com.clinicapp.drravibhaskar.managers.VolleySingleton;
import com.clinicapp.drravibhaskar.managers.WebURLS;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    EditText et_name, et_phone, et_email, et_password;
    Spinner sp_gender;
    Button btn_reg;
    private static final String TAG = "RegistrationActivity";
    String name, mobile, email, password, gender;
    TextView txt_alreadyReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        progressDialog = new ProgressDialog(this);
        sp_gender = findViewById(R.id.sp_gender);

        FindViewById();
        MyClickListener();


        gender = sp_gender.getSelectedItem().toString();

//        userRegister();
    }

    private void userRegister() {
        if (et_name.getText().toString().trim().isEmpty()) {
            et_name.setError("Enter Name");
            et_name.requestFocus();
            return;
        }
        if (et_phone.getText().toString().trim().isEmpty()) {
            et_phone.setError("Enter Mobile");
            et_phone.requestFocus();
            return;
        }
        if (et_email.getText().toString().trim().isEmpty()) {
            et_email.setError("Enter Email");
            et_email.requestFocus();
            return;
        }
        if (gender.equalsIgnoreCase("")) {
            sp_gender.requestFocus();
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return;
        } else if (et_password.getText().toString().isEmpty()) {
            et_password.setError("Enter Password");
            et_password.requestFocus();
            return;
        } else {

            progressDialog.setTitle("Please wait...");
            progressDialog.show();
            name = et_name.getText().toString().trim();
            mobile = et_phone.getText().toString().trim();
            email = et_email.getText().toString().trim();
            password = et_password.getText().toString().trim();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, WebURLS.REGISTER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();
                    Log.d("reso", "onResponseregister: " + response);

                    if (response.equals("null")) {
                        Toast.makeText(RegistrationActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(RegistrationActivity.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                       // Log.d("reso", "onResponseregister: " + response);
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    //Toast.makeText(RegistrationActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                    if(error instanceof NoConnectionError){
                        ConnectivityManager cm = (ConnectivityManager)getApplicationContext()
                                .getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetwork = null;
                        if (cm != null) {
                            activeNetwork = cm.getActiveNetworkInfo();
                        }
                        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
                            Toast.makeText(getApplicationContext(), "Server is not connected to internet.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Your device is not connected to internet.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                            && error.getCause().getMessage().contains("connection")){
                        Toast.makeText(getApplicationContext(), "Your device is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    } else if (error.getCause() instanceof MalformedURLException){
                        Toast.makeText(getApplicationContext(), "Bad Request.", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                            || error.getCause() instanceof JSONException
                            || error.getCause() instanceof XmlPullParserException){
                        Toast.makeText(getApplicationContext(), "Parse Error (because of invalid json or xml).",
                                Toast.LENGTH_SHORT).show();
                    } else if (error.getCause() instanceof OutOfMemoryError){
                        Toast.makeText(getApplicationContext(), "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
                    }else if (error instanceof AuthFailureError){
                        Toast.makeText(getApplicationContext(), "server couldn't find the authenticated request.",
                                Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                        Toast.makeText(getApplicationContext(), "Server is not responding.", Toast.LENGTH_SHORT).show();
                    }else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                            || error.getCause() instanceof ConnectTimeoutException
                            || error.getCause() instanceof SocketException
                            || (error.getCause().getMessage() != null
                            && error.getCause().getMessage().contains("Connection timed out"))) {
                        Toast.makeText(getApplicationContext(), "Connection timeout error",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "An unknown error occurred.",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("Name", name);
                    map.put("MobileNo", mobile);
                    map.put("Email", email);
                    map.put("Gender", gender);
                    map.put("Password", password);
                    return map;
                }
            };
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }

    }


    private void FindViewById() {
        btn_reg = findViewById(R.id.btn_reg);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        txt_alreadyReg=findViewById(R.id.txt_alreadyReg);

    }


    private void MyClickListener() {
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegister();

            }
        });

        txt_alreadyReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}