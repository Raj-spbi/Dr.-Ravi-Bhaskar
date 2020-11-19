package com.clinicapp.drravibhaskar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.clinicapp.drravibhaskar.activities.LoginActivity;
import com.clinicapp.drravibhaskar.activities.MainActivity;
import com.clinicapp.drravibhaskar.activities.RegistrationActivity;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;
import com.clinicapp.drravibhaskar.managers.VolleySingleton;
import com.clinicapp.drravibhaskar.managers.WebURLS;
import com.clinicapp.drravibhaskar.models.ModelLogin;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.skydoves.elasticviews.ElasticButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BottomSheetClass extends BottomSheetDialogFragment {
    ProgressDialog progressDialog;
    String name, mobile, address, email,patientId;
    EditText d_name, d_phone, d_address, txt_emailinfo;

    private static final String TAG = "BottomSheetClass";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layouteditprofile, container, false);

        d_name = view.findViewById(R.id.d_name);
        d_phone = view.findViewById(R.id.d_phone);
        d_address = view.findViewById(R.id.d_address);
        txt_emailinfo = view.findViewById(R.id.txt_emailinfo);
        ElasticButton save = view.findViewById(R.id.save);
        ModelLogin.ResultRow data=SharedPrefManagerAdmin.getInstance(getContext()).getUser();
        d_name.setText(data.getName());
        d_phone.setText(data.getMobileno());
        d_address.setText(data.getStreetAddress());
        txt_emailinfo.setText(data.getEmail());
        patientId=data.getPatientId();
        progressDialog = new ProgressDialog(getContext());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateProfile();
            }
        });


        return view;


    }

    private void updateProfile() {
        progressDialog.setTitle("Please wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        name = d_name.getText().toString().trim();
        mobile = d_phone.getText().toString().trim();
        email = txt_emailinfo.getText().toString().trim();
        address = d_address.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebURLS.UPDATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
//                    Log.d("tests", "onResponse: "+response);
//                    if (jsonObject.getString("Error").equals("Success")){
//                        progressDialog.dismiss();
//                        Log.d("reso", "updateprofile: " + response);
//                        Toast.makeText(getContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(getContext(), MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//
//                        SharedPreferences sharedPreferences=getContext().getSharedPreferences("ashrmadminlogindata", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor=sharedPreferences.edit();
//                        editor.putString("name",name);
//                        editor.putString("contactNo",mobile);
//                        editor.putString("Email",email);
//                        editor.putString("Address1",address);
//                        editor.apply();
//                        editor.commit();
//                    }else {
//                        progressDialog.dismiss();
//                        Toast.makeText(getContext(), "Patient profile not updated", Toast.LENGTH_SHORT).show();
//                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
//                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onErrorResponse: "+error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("contactNo", mobile);
                map.put("Email", email);
                map.put("Address1", address);
                map.put("PatientID",patientId);
                return map;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}


