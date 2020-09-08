package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;
import com.clinicapp.drravibhaskar.managers.VolleySingleton;
import com.clinicapp.drravibhaskar.managers.WebURLS;
import com.skydoves.elasticviews.ElasticButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class LoginActivity extends AppCompatActivity {

    ElasticButton btn_login,btn_registration;
    TextView txt_forgotPassword;
    EditText et_email,et_password;
    String email="";
    String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FindViewById();
        MyClickListner();
    }

    private void FindViewById() {
        et_email=(EditText)findViewById(R.id.et_email);
        et_password=(EditText)findViewById(R.id.et_password);
        btn_login=findViewById(R.id.btn_login);
        btn_registration=findViewById(R.id.btn_register);
        txt_forgotPassword=(TextView)findViewById(R.id.txt_forgotPassword);
    }

    private void MyClickListner() {
        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegistrationActivity.class);
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

        if (et_email.getText().toString().trim().isEmpty()){
            et_email.setError("Enter Email");
            et_email.requestFocus();
            return;
        }
        else if (et_password.getText().toString().isEmpty()){
            et_password.setError("Enter Password");
            et_password.requestFocus();
            return;
        }
        else {
            email=et_email.getText().toString().trim();
            password=et_password.getText().toString().trim();
            StringRequest stringRequest=new StringRequest(Request.Method.POST, WebURLS.LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("null")) {
                        Toast.makeText(LoginActivity.this, "Record Not Found", Toast.LENGTH_SHORT).show();
                        et_email.setText("");
                        et_password.setText("");
                    } else {
//                        Toast.makeText(LoginActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                        //Log.d("reso", "onResponse: "+response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String id = jsonObject.getString("id");
                            if (id.equals("null")) {
                                id = id.replace(id, "");
                            }
                            String patientId = jsonObject.getString("PatientID");
                            if (patientId.equals("null")) {
                                patientId = patientId.replace(patientId, "");
                            }
                            String name = jsonObject.getString("name");
                            if (name.equals("null")) {
                                name = name.replace(name, "");
                            }
                            String gender = jsonObject.getString("gender");
                            if (gender.equals("null")) {
                                gender = gender.replace(gender, "");
                            }
                            String DOB = jsonObject.getString("DOB");
                            if (DOB.equals("null")) {
                                DOB = DOB.replace(DOB, "");
                            }
                            String age = jsonObject.getString("age");
                            if (age.equals("null")) {
                                age = age.replace(age, "");
                            }
                            String Address = jsonObject.getString("Address1");
                            if (Address.equals("null")) {
                                Address = Address.replace(Address, "");
                            }
                            String ContactNo = jsonObject.getString("contactNo");
                            if (ContactNo.equals("null")) {
                                ContactNo = ContactNo.replace(ContactNo, "");
                            }
                            String email = jsonObject.getString("Email");
                            if (email.equals("null")) {
                                email = email.replace(email, "");
                            }
                            String image = jsonObject.getString("Images");
                            if (image.equals("null")) {
                                image = image.replace(image, "");
                            }
                            String password = jsonObject.getString("Password");
                            if (password.equals("null")) {
                                password = password.replace(password, "");
                            }
                            ModelUser modelUser = new ModelUser(id, patientId, name, gender, DOB, age, Address, ContactNo, image, email, password);
                            SharedPrefManagerAdmin.getInstance(getApplicationContext()).userLogin(modelUser);
                            et_password.setText("");
                            et_email.setText("");
                            //                        SharedPreferences sharedPreferences=getSharedPreferences("patient",MODE_PRIVATE);
//                        SharedPreferences.Editor editor=sharedPreferences.edit();
//                        editor.putString("name",name);
//                        editor.putString("email",email);
//                        editor.putString("mobile",mobile);
//                        editor.putString("image",image);
//                        editor.commit();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();


                            //Toast.makeText(LoginActivity.this, "Login Success"+name+","+mobile+","+image, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map=new HashMap<>();
                    map.put("PatientID",email);
                    map.put("Password",password);
                    return map;
                }
            };
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }

    }

    private void openDialogForgotPassword() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.forgot_passworddialog,null);
        ElasticButton btn_save=(ElasticButton)view.findViewById(R.id.btn_forgetSubmit);

        builder.setView(view);
        final AlertDialog alertDialog=builder.create();

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