package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.clinicapp.drravibhaskar.R;
import com.skydoves.elasticviews.ElasticButton;

public class RegistrationActivity extends AppCompatActivity {

    Button btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        FindViewById();
        MyClickListener();
    }

    private void FindViewById() {
        btn_reg=findViewById(R.id.btn_reg);
    }

    private void MyClickListener() {
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}