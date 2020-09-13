package com.clinicapp.drravibhaskar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;

public class BookingFormActivity extends AppCompatActivity {

    TextView txt_date, txt_time, txt_fileName;
    Button openGallery, submit;

    EditText patientName, patientContact, patientEmailId, patientAge, patientOccupation, patienCity, patientDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);
        txt_fileName = findViewById(R.id.txt_fileName);
        openGallery = findViewById(R.id.openGallery);
        submit = findViewById(R.id.submit);
        patientName = findViewById(R.id.patientName);
        patientContact = findViewById(R.id.patientContact);
        patientEmailId = findViewById(R.id.patientEmailId);
        patientAge = findViewById(R.id.patientAge);
        patientOccupation = findViewById(R.id.patientOccupation);
        patienCity = findViewById(R.id.patienCity);
        patientAge = findViewById(R.id.patientAge);
        patientDesc = findViewById(R.id.patientDesc);
        if (!SharedPrefManagerAdmin.getInstance(getApplicationContext()).isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        ModelUser user = SharedPrefManagerAdmin.getInstance(getApplicationContext()).getUser();

        patientName.setText(user.getName());
        patientContact.setText(user.getContactNo());
        patientEmailId.setText(user.getEmail());
        patientAge.setText(user.getAge());


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookingFormActivity.this, "Apply Tomorrow", Toast.LENGTH_SHORT).show();
            }
        });

        ToolBar();
        FindViewByID();
        myClickListener();

    }

    private void ToolBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Book Appointment");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void FindViewByID() {

        txt_date=(TextView)findViewById(R.id.txt_date);
        txt_time=(TextView)findViewById(R.id.txt_time);

    }

    private void myClickListener() {

        Intent intent=getIntent();
        txt_time.setText(intent.getStringExtra("time"));
        txt_date.setText(intent.getStringExtra("date"));

    }

}