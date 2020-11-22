package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.apimodels.ModelSlots;
import com.clinicapp.drravibhaskar.fragments.EveningFragment;
import com.clinicapp.drravibhaskar.fragments.MorningFragment;
import com.clinicapp.drravibhaskar.managers.VolleySingleton;
import com.clinicapp.drravibhaskar.managers.WebURLS;
import com.google.gson.Gson;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentSlotActivtiy extends AppCompatActivity {

    GridView gridView,gridTimeSlot1;
    GridAdaptor gridAdaptor;
    GridAdaptorEvening gridAdaptorEvening;
    ArrayList<ModelSlots> items;
    CardView date,card_grid2,card_grid1;
    TextView dateset;
    String currentDate = "";
    String morDate="";
    String eveDate="";
    LinearLayout cover;
    LinearLayout lin_AppointmentList,line1_eveningSlot,coverEvening;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_slot_activtiy);

        progressDialog=new ProgressDialog(this);

        findViewById(R.id.back_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        dateset = findViewById(R.id.dateset);
        date = findViewById(R.id.date);
        coverEvening=findViewById(R.id.coverEvening);
        card_grid1=findViewById(R.id.card_grid1);
        card_grid2=findViewById(R.id.card_grid2);
        line1_eveningSlot=findViewById(R.id.line1_eveningSlot);
        coverEvening.setVisibility(View.GONE);
        lin_AppointmentList= findViewById(R.id.lin_AppointmentList);
        cover = findViewById(R.id.cover);
        cover.setVisibility(View.GONE);
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        currentDate = dateFormat.format(calendar1.getTime());
        items = new ArrayList<>();
        dateset.setText(currentDate);


        gridView = findViewById(R.id.gridTimeSlot);
        gridTimeSlot1=findViewById(R.id.gridTimeSlot1);
//        gridAdaptor = new GridAdaptor(getApplicationContext(), items);
//        gridView.setAdapter(gridAdaptor);
//        gridAdaptorEvening=new GridAdaptorEvening(getApplicationContext(),items);
//        gridTimeSlot1.setAdapter(gridAdaptorEvening);

        Intent intentDate=getIntent();
        currentDate=intentDate.getStringExtra("currentDate");
        dateset.setText(currentDate);
        //Toast.makeText(this, ""+currentDate, Toast.LENGTH_SHORT).show();


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AppointmentSlotActivtiy.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        CharSequence strDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, month, year);
                        long dtDob = chosenDate.toMillis(true);
                        strDate = DateFormat.format("EEEE, dd MMMM yyyy", dtDob);
                        dateset.setText(String.valueOf(strDate));
                        setData();
                        setEveningData();
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        setData();
        setEveningData();
    }

    private void setEveningData() {
        eveDate=dateset.getText().toString().trim();
        final String evening="Evening";
        if (eveDate.isEmpty()){
            Toast.makeText(this, "Date not selected", Toast.LENGTH_SHORT).show();
        }else {
            items.clear();
            progressDialog.show();
            progressDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebURLS.TIME_SLOT_EVENING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                ModelSlots.Example example=gson.fromJson(response, ModelSlots.Example.class);
                if (example.Error.equalsIgnoreCase("Success")){
                    progressDialog.dismiss();
                    card_grid1.setVisibility(View.VISIBLE);
                    coverEvening.setVisibility(View.GONE);
                    List<ModelSlots.ResultRow> resultRows=example.ResultRows;
                    gridAdaptorEvening=new GridAdaptorEvening(getApplicationContext(),resultRows);
                    gridTimeSlot1.setAdapter(gridAdaptorEvening);
                }else {

                    progressDialog.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentSlotActivtiy.this);
                    builder.setTitle("Alert !");
                    builder.setMessage("Today is Sunday");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            dialogInterface.dismiss();
                            coverEvening.setVisibility(View.VISIBLE);
                            card_grid1.setVisibility(View.GONE);
                            //ntd
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
//                gridAdaptorEvening = new GridAdaptorEvening(getApplicationContext(), items);
//                gridTimeSlot1.setAdapter(gridAdaptorEvening);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                coverEvening.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
                card_grid1.setVisibility(View.GONE);
                if (error instanceof NoConnectionError) {
                    ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = null;
                    if (cm != null) {
                        activeNetwork = cm.getActiveNetworkInfo();
                    }
                    if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                        Toast.makeText(getApplicationContext(), "Server is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Your device is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                        && error.getCause().getMessage().contains("connection")) {
                    Toast.makeText(getApplicationContext(), "Your device is not connected to internet.",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof MalformedURLException) {
                    Toast.makeText(getApplicationContext(), "Bad Request.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                        || error.getCause() instanceof JSONException
                        || error.getCause() instanceof XmlPullParserException) {
                    Toast.makeText(getApplicationContext(), "Parse Error (because of invalid json or xml).",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof OutOfMemoryError) {
                    Toast.makeText(getApplicationContext(), "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "server couldn't find the authenticated request.",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "Server is not responding.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                        || error.getCause() instanceof ConnectTimeoutException
                        || error.getCause() instanceof SocketException
                        || (error.getCause().getMessage() != null
                        && error.getCause().getMessage().contains("Connection timed out"))) {
                    Toast.makeText(getApplicationContext(), "Connection timeout error",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "An unknown error occurred.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Date", eveDate);
                map.put("Timing",evening);
                return map;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }
    }

    private void setData() {

        morDate=dateset.getText().toString().trim();
        final String morning="Morning";
        if (morDate.isEmpty()){
            Toast.makeText(this, "Date not selected", Toast.LENGTH_SHORT).show();
        }
        else {
            items.clear();
            progressDialog.show();;
            progressDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebURLS.TIME_SLOT_MORNING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("status", "onResponse: " + response);

                Gson gson=new Gson();
                ModelSlots.Example example=gson.fromJson(response, ModelSlots.Example.class);
                if (example.Error.equalsIgnoreCase("Success")){
                    progressDialog.dismiss();
                    card_grid2.setVisibility(View.VISIBLE);
                    cover.setVisibility(View.GONE);
                    List<ModelSlots.ResultRow> resultRows=example.ResultRows;
                    gridAdaptor=new GridAdaptor(getApplicationContext(),resultRows);
                    gridView.setAdapter(gridAdaptor);
                }else {

                    progressDialog.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentSlotActivtiy.this);
                    builder.setTitle("Alert !");
                    builder.setMessage("Today is Sunday");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            dialogInterface.dismiss();
                            cover.setVisibility(View.VISIBLE);
                            card_grid2.setVisibility(View.GONE);
                            //ntd
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

//                gridAdaptor = new GridAdaptor(getApplicationContext(), items);
//                gridView.setAdapter(gridAdaptor);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                cover.setVisibility(View.VISIBLE);
                card_grid2.setVisibility(View.GONE);
                if (error instanceof NoConnectionError) {
                    ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = null;
                    if (cm != null) {
                        activeNetwork = cm.getActiveNetworkInfo();
                    }
                    if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                        Toast.makeText(getApplicationContext(), "Server is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Your device is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                        && error.getCause().getMessage().contains("connection")) {
                    Toast.makeText(getApplicationContext(), "Your device is not connected to internet.",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof MalformedURLException) {
                    Toast.makeText(getApplicationContext(), "Bad Request.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                        || error.getCause() instanceof JSONException
                        || error.getCause() instanceof XmlPullParserException) {
                    Toast.makeText(getApplicationContext(), "Parse Error (because of invalid json or xml).",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof OutOfMemoryError) {
                    Toast.makeText(getApplicationContext(), "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "server couldn't find the authenticated request.",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "Server is not responding.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                        || error.getCause() instanceof ConnectTimeoutException
                        || error.getCause() instanceof SocketException
                        || (error.getCause().getMessage() != null
                        && error.getCause().getMessage().contains("Connection timed out"))) {
                    Toast.makeText(getApplicationContext(), "Connection timeout error",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "An unknown error occurred.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>  map=new HashMap<>();
                map.put("Date", morDate);
                map.put("Timing",morning);
                return map;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }
    }


    private class GridAdaptor extends BaseAdapter {

        Context context;
        List<ModelSlots.ResultRow> gridItems;

        public GridAdaptor(Context context, List<ModelSlots.ResultRow> gridItems) {
            this.context = context;
            this.gridItems = gridItems;
        }

        @Override
        public int getCount() {
            return gridItems.size();
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

            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_for_time_slot, viewGroup, false);

            final TextView txt_timeSlot = view.findViewById(R.id.txt_timeSlot);
            LinearLayout lin_container = view.findViewById(R.id.linear_container);
            String status = gridItems.get(i).getStatus();
            Log.d("status", "Status is: " + status);
            if (status.equalsIgnoreCase("Time Out")) {
                txt_timeSlot.setText(gridItems.get(i).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.pink));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Sorry Slot Time is Out", Toast.LENGTH_SHORT).show();
                    }
                });

            } else if (status.equalsIgnoreCase("Available")) {
                txt_timeSlot.setText(gridItems.get(i).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.green));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(), BookingFormActivity.class);
                        intent.putExtra("SlotId",String.valueOf(gridItems.get(i).getSlotId()));
                        intent.putExtra("date", dateset.getText().toString());
                        intent.putExtra("time", gridItems.get(i).getTiming());
                        startActivity(intent);

                    }
                });

            } else if (status.equalsIgnoreCase("Block")) {
                txt_timeSlot.setText(gridItems.get(i).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.red));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Slot is Blocked", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                txt_timeSlot.setText(gridItems.get(i).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Already Booked", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            return view;
        }
    }


    private class GridAdaptorEvening extends BaseAdapter{

        Context context;
        List<ModelSlots.ResultRow> gridItems;


        public GridAdaptorEvening(Context context, List<ModelSlots.ResultRow> gridItems) {
            this.context = context;
            this.gridItems = gridItems;
        }

        @Override
        public int getCount() {
            return gridItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            convertView = LayoutInflater.from(context).inflate(R.layout.custom_layout_for_time_slot, parent, false);

            final TextView txt_timeSlot = convertView.findViewById(R.id.txt_timeSlot);
            LinearLayout lin_container = convertView.findViewById(R.id.linear_container);
            String status = gridItems.get(position).getStatus();
            Log.d("status", "Status is: " + status);
            if (status.equalsIgnoreCase("Time Out")) {
                txt_timeSlot.setText(gridItems.get(position).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.pink));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Sorry Slot Time is Out", Toast.LENGTH_SHORT).show();
                    }
                });

            } else if (status.equalsIgnoreCase("Available")) {
                txt_timeSlot.setText(gridItems.get(position).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.green));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int slotId=gridItems.get(position).getSlotId();
                        Intent intent = new Intent(getApplicationContext(), BookingFormActivity.class);
                        intent.putExtra("SlotId",String.valueOf(slotId));
                        intent.putExtra("date", dateset.getText().toString());
                        intent.putExtra("time", gridItems.get(position).getTiming());
                        startActivity(intent);

                    }
                });

            } else if (status.equalsIgnoreCase("Block")) {
                txt_timeSlot.setText(gridItems.get(position).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.red));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Slot is Blocked", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                txt_timeSlot.setText(gridItems.get(position).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Already Booked", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            return convertView;
        }
    }




}