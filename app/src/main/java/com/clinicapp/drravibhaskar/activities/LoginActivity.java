package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.clinicapp.drravibhaskar.R;
import com.skydoves.elasticviews.ElasticButton;

public class LoginActivity extends AppCompatActivity {

    ElasticButton btn_login,btn_registration;
    TextView txt_forgotPassword;

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
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        txt_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogForgotPassword();
            }
        });
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