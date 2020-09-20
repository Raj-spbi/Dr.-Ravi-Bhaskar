package com.clinicapp.drravibhaskar.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

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
import com.clinicapp.drravibhaskar.models.ModelGrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvailServiceActivity extends AppCompatActivity {

    GridView gridView;
    List<ModelGrid> modelGrids;
    AdapterAvailService adapterAvailService;
    ProgressDialog progressDialog;

    String pId="";
    String emailId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avail_service);

        progressDialog=new ProgressDialog(this);
        gridView=findViewById(R.id.gridView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        modelGrids = new ArrayList<>();
        modelGrids.add(new ModelGrid(R.drawable.cart_ic, "Medicine\nDelivery"));
        modelGrids.add(new ModelGrid(R.drawable.nursing_care_ic, "Nursing Care"));
        modelGrids.add(new ModelGrid(R.drawable.equipmment_rental_ic, "Equipment Rental"));
        modelGrids.add(new ModelGrid(R.drawable.consultation_ic, "Investigations"));

        adapterAvailService = new AdapterAvailService(getApplicationContext(), modelGrids);
        gridView.setAdapter(adapterAvailService);

        ModelUser user = SharedPrefManagerAdmin.getInstance(AvailServiceActivity.this).getUser();
        pId=user.getPatientID();
        //Toast.makeText(this, ""+pId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public class AdapterAvailService extends BaseAdapter {
        Context context;
        List<ModelGrid> modelGrids;

        public AdapterAvailService(Context context, List<ModelGrid> modelGrids) {
            this.context = context;
            this.modelGrids = modelGrids;
        }

        @Override
        public int getCount() {
            return modelGrids.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {


            view = LayoutInflater.from(context).inflate(R.layout.custom_home_avail_layout, viewGroup, false);
            final TextView textView = view.findViewById(R.id.title);
            ImageView image = view.findViewById(R.id.image);
            textView.setText(modelGrids.get(i).getName());
            image.setImageResource(modelGrids.get(i).getImageRes());
            CardView cardView = view.findViewById(R.id.cover);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "" + modelGrids.get(i).getName(), Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder=new AlertDialog.Builder(AvailServiceActivity.this);
                    View view1=getLayoutInflater().inflate(R.layout.custom_alert_home_avail,null);

                    ImageView cross=view1.findViewById(R.id.cross);
                    final EditText et_name=view1.findViewById(R.id.et_name);
                    final EditText et_address=view1.findViewById(R.id.et_address);
                    final EditText et_mobile=view1.findViewById(R.id.et_mobile);
                    final EditText et_email=view1.findViewById(R.id.et_email);
                    Button submit=view1.findViewById(R.id.submit);
                    TextView save=view1.findViewById(R.id.save);
                    TextView event=view1.findViewById(R.id.txt_event);
                    final String gridName=modelGrids.get(i).getName();
                    event.setText(gridName);

                    builder.setView(view1);
                    final AlertDialog alertDialog=builder.create();
                    alertDialog.setCancelable(false);
                    cross.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (et_name.getText().toString().trim().isEmpty()){
                                et_name.setError("Enter Your Name");
                                et_name.requestFocus();
                                return;
                            }
                            else if (et_address.getText().toString().trim().isEmpty()){
                                et_address.setError("Enter Your Address");
                                et_address.requestFocus();
                                return;
                            }
                            else if (et_mobile.getText().toString().trim().isEmpty()){
                                et_mobile.setError("Enter Your Mobile Number");
                                et_mobile.requestFocus();
                                return;
                            }
                            else if (et_email.getText().toString().trim().isEmpty()){
                                et_email.setError("Enter Your Mobile Number");
                                et_email.requestFocus();
                                return;
                            }
                            else {
                                progressDialog.show();
                                progressDialog.setMessage("We will contact you soon");
                                final String name=et_name.getText().toString().trim();
                                final String address=et_address.getText().toString().trim();
                                final String mobile=et_mobile.getText().toString().trim();
                                emailId=et_email.getText().toString().trim();
                                StringRequest stringRequest=new StringRequest(Request.Method.POST, WebURLS.HOME_SERVICES, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        progressDialog.dismiss();
                                        Toast.makeText(AvailServiceActivity.this, "Completed Successfully", Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progressDialog.dismiss();
                                        Toast.makeText(AvailServiceActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                                    }
                                }){

                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String,String> map=new HashMap<>();
                                        map.put("PatientID",pId);
                                        map.put("Name",name);
                                        map.put("Email",emailId);
                                        map.put("ContactNo",mobile);
                                        map.put("Address",address);
                                        map.put("HomeServiceType",gridName);
                                        return map;
                                    }
                                };
                                VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

                            }

                        }
                    });
                    alertDialog.show();

                }
            });
            return view;
        }

    }
}