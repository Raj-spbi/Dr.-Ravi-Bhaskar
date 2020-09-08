package com.clinicapp.drravibhaskar.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
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
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.activities.BookingFormActivity;
import com.clinicapp.drravibhaskar.managers.VolleySingleton;
import com.clinicapp.drravibhaskar.managers.WebURLS;
import com.clinicapp.drravibhaskar.models.ModelForTimeSlots;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class MorningFragment extends Fragment {

    GridView gridView;
    GridAdaptor gridAdaptor;
    ArrayList<ModelForTimeSlots> items;
    final DatePickerDialog[] datePickerDialog = new DatePickerDialog[1];
    final int[] year = new int[1];
    final int[] month = new int[1];
    final int[] dayOfMonth = new int[1];
    final Calendar[] calendar = new Calendar[1];
    CardView date;
    TextView dateset;
    String currentDate="";
//    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_morning, container, false);
        dateset = view.findViewById(R.id.dateset);
        date = view.findViewById(R.id.date);

        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        currentDate = dateFormat.format(calendar1.getTime());
        dateset.setText(currentDate);
        Toast.makeText(getContext(), ""+currentDate, Toast.LENGTH_SHORT).show();


        gridView = view.findViewById(R.id.gridTimeSlot);
        setData();
        gridAdaptor = new GridAdaptor(getContext(), items);
        gridView.setAdapter(gridAdaptor);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar[0] = Calendar.getInstance();
                year[0] = calendar[0].get(Calendar.YEAR);
                month[0] = calendar[0].get(Calendar.MONTH);
                dayOfMonth[0] = calendar[0].get(Calendar.DAY_OF_MONTH);
                datePickerDialog[0] = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        CharSequence strDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, month, year);
                        long dtDob = chosenDate.toMillis(true);
                        strDate = DateFormat.format("EEEE, dd MMMM yyyy", dtDob);
                        dateset.setText(strDate);
//                                dateset.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year[0], month[0], dayOfMonth[0]);
                datePickerDialog[0].getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog[0].show();
            }
        });

//        date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(BookAppointmentActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//
//                // Get Current Date
//                final Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//                                CharSequence strDate = null;
//                                Time chosenDate=new Time();
//                                chosenDate.set(dayOfMonth, monthOfYear, year);
//                                long dtDob = chosenDate.toMillis(true);
//                                strDate = DateFormat.format("EEEE, dd MMMM yyyy", dtDob);
//                                dateset.setText(strDate);
////                                dateset.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//
//                            }
//                        }, mYear, mMonth, mDay);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
//                datePickerDialog.show();
//            }
//        });

        return view;
    }

    private void setData() {
        items=new ArrayList<>();
//        items.add(new ModelForTimeSlots("9:30 AM"));

        StringRequest stringRequest=new StringRequest(Request.Method.POST, WebURLS.AllDate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>  map=new HashMap<>();
                map.put("Date",currentDate);
                map.put("Timing","Evening");
                return map;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private class GridAdaptor extends BaseAdapter {

        Context context;
        ArrayList<ModelForTimeSlots> gridItems;

        public GridAdaptor(Context context, ArrayList<ModelForTimeSlots> gridItems) {
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

            view= LayoutInflater.from(context).inflate(R.layout.custom_layout_for_time_slot,viewGroup,false);

            final TextView txt_timeSlot=view.findViewById(R.id.txt_timeSlot);
            txt_timeSlot.setText(gridItems.get(i).getTxt_timeName());

            LinearLayout lin_container=view.findViewById(R.id.linear_container);
            lin_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent=new Intent(getContext(), BookingFormActivity.class);
                    intent.putExtra("date",dateset.getText().toString());
                    intent.putExtra("time",gridItems.get(i).getTxt_timeName());
                    startActivity(intent);

                }
            });
            return view;
        }
    }
}