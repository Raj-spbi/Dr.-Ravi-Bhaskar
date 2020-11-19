package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.clinicapp.drravibhaskar.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    String currentDate = "";
    DatePicker simpleDatePicker;
    Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        findViewById(R.id.back_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        simpleDatePicker= findViewById(R.id.simpleDatePicker);
        btn_click=findViewById(R.id.btn_click);

        Calendar cal=Calendar.getInstance();
        simpleDatePicker.setMinDate(System.currentTimeMillis());
        simpleDatePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                CharSequence strDate = null;
                Time chosenDate = new Time();
                chosenDate.set(dayOfMonth, monthOfYear, year);
                long dtDob = chosenDate.toMillis(true);
                strDate = DateFormat.format("EEEE, dd MMMM yyyy", dtDob);
                currentDate = String.valueOf(strDate);
                //Toast.makeText(getContext(), ""+currentDate, Toast.LENGTH_SHORT).show();

            }
        });

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentDate.isEmpty()){
                    Date d = new Date();
                    CharSequence s  = DateFormat.format("EEEE, dd MMMM yyyy", d.getTime());
                    //Toast.makeText(CalendarActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),AppointmentSlotActivtiy.class);
                    intent.putExtra("currentDate",s);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(getApplicationContext(),AppointmentSlotActivtiy.class);
                    intent.putExtra("currentDate",currentDate);
                    startActivity(intent);
                }
            }
        });
    }
}