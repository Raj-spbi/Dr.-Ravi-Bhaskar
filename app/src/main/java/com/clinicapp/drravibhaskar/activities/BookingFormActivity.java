package com.clinicapp.drravibhaskar.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BookingFormActivity extends AppCompatActivity {

    TextView txt_date, txt_time, txt_fileName;
    Spinner gender;
    Button openGallery,submit;
    Bitmap bitmap;
    String imgRes="";
    ProgressDialog progressDialog;

    EditText patientName, patientContact, patientEmailId, patientAge, patientOccupation, patienCity, patientDesc;

    String BookingDate="";
    String TimeSlot="";
    String PatientName="";
    String Email="";
    String MobileNumber="";
    String Gender="";
    String Age="";
    String City="";
    String Occupation="";
    String Uploadfile="";
    String Discription="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        ToolBar();
        FindViewByID();
        myClickListener();

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
        //txt_fileName = findViewById(R.id.txt_fileName);
        //openGallery = findViewById(R.id.openGallery);
        submit = findViewById(R.id.submit);
        patientName = findViewById(R.id.patientName);
        gender=findViewById(R.id.gender);
        patientContact = findViewById(R.id.patientContact);
        patientEmailId = findViewById(R.id.patientEmailId);
        patientAge = findViewById(R.id.patientAge);
        patientOccupation = findViewById(R.id.patientOccupation);
        patienCity = findViewById(R.id.patienCity);
        patientAge = findViewById(R.id.patientAge);
        patientDesc = findViewById(R.id.patientDesc);

    }

    private void myClickListener() {

        Intent intent=getIntent();
        txt_time.setText(intent.getStringExtra("time"));
        txt_date.setText(intent.getStringExtra("date"));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(BookingFormActivity.this, "Apply Tomorrow", Toast.LENGTH_SHORT).show();
                uploadFormData();
            }
        });

//        openGallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent.setType("image/*"); //
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(intent,10);
//            }
//        });

    }

    private void uploadFormData() {

        BookingDate=txt_date.getText().toString().trim();
        TimeSlot=txt_time.getText().toString().trim();
        PatientName=patientName.getText().toString().trim();
        Email=patientEmailId.getText().toString().trim();
        MobileNumber=patientContact.getText().toString().trim();
        Gender=gender.getSelectedItem().toString().trim();
        Age=patientAge.getText().toString().trim();
        City=patienCity.getText().toString().trim();
        Occupation=patientOccupation.getText().toString().trim();
        Discription=patientDesc.getText().toString().trim();

        if (BookingDate.isEmpty()){
            Toast.makeText(this, "Booking Time Not Found ", Toast.LENGTH_SHORT).show();
        }
        else if (TimeSlot.isEmpty()){
            Toast.makeText(this, "time Slot Not Found", Toast.LENGTH_SHORT).show();
        }
        else if (PatientName.isEmpty()) {
            patientName.setError("Enter Name");
            patientName.requestFocus();
            return;
        }
        else if (Email.isEmpty()){
            patientEmailId.setError("Enter Email");
            patientEmailId.requestFocus();
            return;
        }
        else if (MobileNumber.isEmpty()){
            patientContact.setError("Enter Mobile Number");
            patientContact.requestFocus();
            return;
        }
        else if (Gender.equals("Choose Gender")){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
        }
        else if (Age.isEmpty()){
            patientAge.setError("Enter Age");
            patientAge.requestFocus();
            return;
        }
        else if (City.isEmpty()){
            patienCity.setError("Enter City");
            patienCity.requestFocus();
            return;
        }
        else if (Occupation.isEmpty()){
            patientOccupation.setError("Enter Occupation");
            patientOccupation.requestFocus();
            return;
        }
        else if (Discription.isEmpty()){
            patientDesc.setError("Enter Description");
            patientDesc.requestFocus();
            return;
        }
        else {
            progressDialog.show();
            StringRequest stringRequest=new StringRequest(Request.Method.POST, WebURLS.BOOKING_APPOINTMENT, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Toast.makeText(BookingFormActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(BookingFormActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map=new HashMap<>();
                    map.put("BookingDate",BookingDate);
                    map.put("TimeSlot",TimeSlot);
                    map.put("PatientName",PatientName);
                    map.put("Email",Email);
                    map.put("MobileNumber",MobileNumber);
                    map.put("Gender",Gender);
                    map.put("Age",Age);
                    map.put("City",City);
                    map.put("Occupation",Occupation);
                    map.put("Discription",Discription);
                    map.put("Uploadfile",imgRes);
                    return map;
                }
            };
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }



    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 10 && resultCode == Activity.RESULT_OK && data != null) {
//            Uri imgUri = data.getData();
//            txt_fileName.setText("image.png");
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
//                imgRes = convertBitmapToBase64(bitmap);
//                //Toast.makeText(this, imgRes + "  converter mehtod", Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                Toast.makeText(this, e.getMessage() + "  error message", Toast.LENGTH_SHORT).show();
//            }
//
//        } else {
//            Toast.makeText(this, "error in on activityresult", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    private String convertBitmapToBase64(Bitmap bitmap) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
//        byte[] byteArray = byteArrayOutputStream.toByteArray();
//
//        //Toast.makeText(this, Base64.encodeToString(byteArray, Base64.DEFAULT).toString() + "", Toast.LENGTH_SHORT).show();
//        return Base64.encodeToString(byteArray, Base64.DEFAULT);
//    }

}