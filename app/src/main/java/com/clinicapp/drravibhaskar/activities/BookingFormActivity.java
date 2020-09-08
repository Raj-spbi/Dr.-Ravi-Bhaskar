package com.clinicapp.drravibhaskar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.clinicapp.drravibhaskar.R;

public class BookingFormActivity extends AppCompatActivity {

    TextView txt_date,txt_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

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