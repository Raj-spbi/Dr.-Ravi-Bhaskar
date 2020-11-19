package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.clinicapp.drravibhaskar.models.ModelLogin;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.skydoves.elasticviews.ElasticButton;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ElasticButton btn_registration;
    Button btn_login;
    TextView txt_forgotPassword;
    EditText et_email, et_password;
    String email = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);


        FindViewById();
        MyClickListner();
    }

    private void FindViewById() {
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_registration = findViewById(R.id.btn_register);
        txt_forgotPassword = (TextView) findViewById(R.id.txt_forgotPassword);
    }

    private void MyClickListner() {
        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                UserLogin();
            }
        });
        txt_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogForgotPassword();
            }
        });
    }

    private void UserLogin() {

        if (et_email.getText().toString().trim().isEmpty()) {
            et_email.setError("Enter Email");
            et_email.requestFocus();
            return;
        } else if (et_password.getText().toString().isEmpty()) {
            et_password.setError("Enter Password");
            et_password.requestFocus();
            return;
        } else {

            progressDialog.setTitle("Please wait....");
            progressDialog.show();
            progressDialog.setCancelable(false);
            email = et_email.getText().toString().trim();
            password = et_password.getText().toString().trim();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, WebURLS.LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson=new Gson();
                    try {
                        JSONObject jsonObject1=new JSONObject(response);
//                        Log.d("resps", "onResponse: "+response);
                        if (jsonObject1.getString("Error").equalsIgnoreCase("Success")){
                            progressDialog.dismiss();
                            JSONArray  jsonArray=jsonObject1.getJSONArray("ResultRows");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(0);
                                String Id= String.valueOf(jsonObject.getInt("Id"));
                                String PatientId=jsonObject.getString("PatientId");
                                String Name=jsonObject.getString("Name");
                                String Gender=jsonObject.getString("Gender");
                                String dob=jsonObject.getString("Dob");
                                String mobileno=jsonObject.getString("MobileNo");
                                String email=jsonObject.getString("Email");
                                String BloodGroup=jsonObject.getString("BloodGroup");
                                String StreetAddress=jsonObject.getString("StreetAddress");
                                String Locatity=jsonObject.getString("Locatity");
                                String City=jsonObject.getString("City");
                                String PinCode=jsonObject.getString("PinCode");
                                String Images=jsonObject.getString("Images");
                                String CreateDate=jsonObject.getString("CreateDate");
                                String IsActive=jsonObject.getString("IsActive");
                                String IsDeleted=jsonObject.getString("IsDeleted");
                                String Password=jsonObject.getString("Password");
                                ModelLogin.ResultRow alldata=new ModelLogin.ResultRow(Id,PatientId,Name,Gender,dob,mobileno,email,BloodGroup,StreetAddress,Locatity,City,PinCode,Images,CreateDate,IsActive,IsDeleted,Password);
                                SharedPrefManagerAdmin.getInstance(getApplicationContext()).userLogin(alldata);
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, ""+jsonObject1.getString("Error"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    //Toast.makeText(LoginActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

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
                    map.put("UserId",email);
                    map.put("Password", password);
                    return map;
                }
            };
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }

    }

    private void openDialogForgotPassword() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.forgot_passworddialog, null);
        ElasticButton btn_save = (ElasticButton) view.findViewById(R.id.btn_forgetSubmit);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Amazing!!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}