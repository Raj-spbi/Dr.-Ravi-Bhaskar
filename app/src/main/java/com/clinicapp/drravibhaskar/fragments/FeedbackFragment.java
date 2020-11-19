package com.clinicapp.drravibhaskar.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;
import com.clinicapp.drravibhaskar.managers.VolleySingleton;
import com.clinicapp.drravibhaskar.managers.WebURLS;
import com.clinicapp.drravibhaskar.models.ModelLogin;

import java.util.HashMap;
import java.util.Map;

public class FeedbackFragment extends Fragment {

    String name="";
    String mobile="";
    String email="";
    String message="";
    Button senNow;

    ProgressDialog progressDialog;
    EditText et_message;


    public FeedbackFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_feedback, container, false);
        // Inflate the layout for this fragment

        progressDialog=new ProgressDialog(getContext());

        senNow=view.findViewById(R.id.senNow);
        et_message=view.findViewById(R.id.et_message);

        ModelLogin.ResultRow data= SharedPrefManagerAdmin.getInstance(getContext()).getUser();
        name=data.getName();
        mobile=data.getMobileno();
        email=data.getEmail();

        senNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedBack();
            }
        });

        return view;
    }

    private void sendFeedBack() {
        message=et_message.getText().toString().trim();
        if (message.isEmpty()){
            et_message.setError("Please enter feedback");
            et_message.requestFocus();
            return;
        }
        else {
            mobile="7890878789";
            progressDialog.show();
            progressDialog.setCancelable(false);
            StringRequest stringRequest=new StringRequest(Request.Method.POST, WebURLS.FEED_BACK, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Feedback Submitted Successfully", Toast.LENGTH_SHORT).show();
                    et_message.setText("");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map=new HashMap<>();
                    map.put("Name",name);
                    map.put("Email",email);
                    map.put("Phone",mobile);
                    map.put("Msg",message);
                    return map;
                }
            };
            VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        }


    }
}