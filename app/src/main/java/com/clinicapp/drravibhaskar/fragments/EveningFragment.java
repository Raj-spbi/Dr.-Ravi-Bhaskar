package com.clinicapp.drravibhaskar.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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
import com.clinicapp.drravibhaskar.activities.BookingFormActivity;
import com.clinicapp.drravibhaskar.apimodels.ModelSlots;
import com.clinicapp.drravibhaskar.managers.VolleySingleton;
import com.clinicapp.drravibhaskar.managers.WebURLS;

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
import java.util.Map;


public class EveningFragment extends Fragment {

    GridView gridView;
    GridAdaptor gridAdaptor;
    ArrayList<ModelSlots> items;
    final DatePickerDialog[] datePickerDialog = new DatePickerDialog[1];
    final int[] year = new int[1];
    final int[] month = new int[1];
    final int[] dayOfMonth = new int[1];
    final Calendar[] calendar = new Calendar[1];
    CardView date;
    TextView dateset;
    String currentDate = "";

    LinearLayout cover;

    public EveningFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_evening, container, false);
//        dateset = view.findViewById(R.id.dateset);
//        date = view.findViewById(R.id.date);
//        cover = view.findViewById(R.id.cover);
//        cover.setVisibility(View.GONE);
//
//        Calendar calendar1 = Calendar.getInstance();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
//        currentDate = dateFormat.format(calendar1.getTime());
//        items = new ArrayList<>();
//        dateset.setText(currentDate);
//
//        gridView = view.findViewById(R.id.gridTimeSlot);
//        setData(currentDate);
//        gridAdaptor = new GridAdaptor(getContext(), items);
//        gridView.setAdapter(gridAdaptor);
//
//        date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                calendar[0] = Calendar.getInstance();
//                year[0] = calendar[0].get(Calendar.YEAR);
//                month[0] = calendar[0].get(Calendar.MONTH);
//                dayOfMonth[0] = calendar[0].get(Calendar.DAY_OF_MONTH);
//                datePickerDialog[0] = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        CharSequence strDate = null;
//                        Time chosenDate = new Time();
//                        chosenDate.set(dayOfMonth, month, year);
//                        long dtDob = chosenDate.toMillis(true);
//                        strDate = DateFormat.format("EEEE, dd MMMM yyyy", dtDob);
//                        dateset.setText(strDate);
//                        currentDate = String.valueOf(strDate);
//
//                        setData(currentDate);
////                                dateset.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                    }
//                }, year[0], month[0], dayOfMonth[0]);
//                datePickerDialog[0].getDatePicker().setMinDate(System.currentTimeMillis());
//                datePickerDialog[0].show();
//            }
//        });

        return view;
    }

//    private void setData(final String currentDate) {
//
//        items.clear();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebURLS.AllDate, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("status", "onResponse: " + response);
////                    Toast.makeText(getContext(), ""+currentDate+" "+response, Toast.LENGTH_SHORT).show();
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String message = jsonObject.getString("Error");
//                    Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
//                    if (message.equalsIgnoreCase("success")) {
//                        JSONArray jsonArray = jsonObject.getJSONArray("ResultRows");
//                        for (int j = 0; j < jsonArray.length(); j++) {
//                            JSONObject jsonObject1 = jsonArray.getJSONObject(j);
//
//                            items.add(new ModelSlots(jsonObject1.getString("SlotId"), jsonObject1.getString("Date"),
//                                    jsonObject1.getString("Slot"), jsonObject1.getString("Timing"), jsonObject1.getString("status"),
//                                    jsonObject1.getString("IsActive")));
//                            cover.setVisibility(View.GONE);
//                        }
//                        gridAdaptor = new GridAdaptor(getContext(), items);
//                        gridView.setAdapter(gridAdaptor);
//                    } else {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                        builder.setTitle("Alert !");
//                        builder.setMessage(message);
//                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//
//                                dialogInterface.dismiss();
//                                cover.setVisibility(View.VISIBLE);
//                                //ntd
//                            }
//                        });
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                gridAdaptor = new GridAdaptor(getActivity(), items);
//                gridView.setAdapter(gridAdaptor);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                cover.setVisibility(View.VISIBLE);
//                if (error instanceof NoConnectionError) {
//                    ConnectivityManager cm = (ConnectivityManager) getActivity()
//                            .getSystemService(Context.CONNECTIVITY_SERVICE);
//                    NetworkInfo activeNetwork = null;
//                    if (cm != null) {
//                        activeNetwork = cm.getActiveNetworkInfo();
//                    }
//                    if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
//                        Toast.makeText(getActivity(), "Server is not connected to internet.",
//                                Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getActivity(), "Your device is not connected to internet.",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
//                        || (error.getCause().getMessage() != null
//                        && error.getCause().getMessage().contains("connection"))) {
//                    Toast.makeText(getActivity(), "Your device is not connected to internet.",
//                            Toast.LENGTH_SHORT).show();
//                } else if (error.getCause() instanceof MalformedURLException) {
//                    Toast.makeText(getActivity(), "Bad Request.", Toast.LENGTH_SHORT).show();
//                } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
//                        || error.getCause() instanceof JSONException
//                        || error.getCause() instanceof XmlPullParserException) {
//                    Toast.makeText(getActivity(), "Parse Error (because of invalid json or xml).",
//                            Toast.LENGTH_SHORT).show();
//                } else if (error.getCause() instanceof OutOfMemoryError) {
//                    Toast.makeText(getActivity(), "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
//                } else if (error instanceof AuthFailureError) {
//                    Toast.makeText(getActivity(), "server couldn't find the authenticated request.",
//                            Toast.LENGTH_SHORT).show();
//                } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
//                    Toast.makeText(getActivity(), "Server is not responding.", Toast.LENGTH_SHORT).show();
//                } else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
//                        || error.getCause() instanceof ConnectTimeoutException
//                        || error.getCause() instanceof SocketException
//                        || (error.getCause().getMessage() != null
//                        && error.getCause().getMessage().contains("Connection timed out"))) {
//                    Toast.makeText(getActivity(), "Connection timeout error",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "An unknown error occurred.",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("Date", currentDate);
//                map.put("Timing", "Evening");
//                return map;
//            }
//        };
//
//        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
//    }

    private class GridAdaptor extends BaseAdapter {

        Context context;
        ArrayList<ModelSlots.ResultRow> gridItems;

        public GridAdaptor(Context context, ArrayList<ModelSlots.ResultRow> gridItems) {
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
                        Toast.makeText(getContext(), "Sorry Slot Time is Out", Toast.LENGTH_SHORT).show();
                    }
                });

            } else if (status.equalsIgnoreCase("Available")) {
                txt_timeSlot.setText(gridItems.get(i).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.green));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String slotId= String.valueOf(gridItems.get(i).getSlotId());
                        Intent intent = new Intent(getContext(), BookingFormActivity.class);
                        intent.putExtra("SlotId",slotId);
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
                        Toast.makeText(getContext(), "Slot is Blocked", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                txt_timeSlot.setText(gridItems.get(i).getTiming());
                txt_timeSlot.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                lin_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "Already Booked", Toast.LENGTH_SHORT).show();

                    }
                });
            }


            return view;
        }
    }
}