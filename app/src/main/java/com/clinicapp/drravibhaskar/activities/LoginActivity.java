package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.clinicapp.drravibhaskar.MainActivity;
import com.clinicapp.drravibhaskar.R;
import com.skydoves.elasticviews.ElasticButton;

public class LoginActivity extends AppCompatActivity {

    ElasticButton btn_login,btn_registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FindViewById();
        MyClickListner();
    }

    private void FindViewById() {
        btn_login=findViewById(R.id.btn_login);
        btn_registration=findViewById(R.id.btn_register);
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
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}