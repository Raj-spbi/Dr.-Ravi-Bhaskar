package com.clinicapp.drravibhaskar.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.models.ModelForTimeSlots;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class EveningFragment extends Fragment {

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

    public EveningFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evening, container, false);
        gridView = view.findViewById(R.id.gridTimeSlot);
        setData();
        gridAdaptor = new GridAdaptor(getContext(), items);
        gridView.setAdapter(gridAdaptor);
        dateset = view.findViewById(R.id.dateset);
        date = view.findViewById(R.id.date);

        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String currentDate = dateFormat.format(calendar1.getTime());
        dateset.setText(currentDate);

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

        return view;
    }
    private void setData() {
        items=new ArrayList<>();
        items.add(new ModelForTimeSlots("9:30 AM"));
        items.add(new ModelForTimeSlots("9:45 AM"));
        items.add(new ModelForTimeSlots("10:00 AM"));
        items.add(new ModelForTimeSlots("10:15 AM"));
        items.add(new ModelForTimeSlots("10:30 AM"));
        items.add(new ModelForTimeSlots("10:45 AM"));
        items.add(new ModelForTimeSlots("11:00 AM"));
        items.add(new ModelForTimeSlots("11:15 AM"));
        items.add(new ModelForTimeSlots("11:30 AM"));
        items.add(new ModelForTimeSlots("11:45 AM"));
        items.add(new ModelForTimeSlots("12:00 PM"));
        items.add(new ModelForTimeSlots("12:15 PM"));
        items.add(new ModelForTimeSlots("12:30 PM"));
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
        public View getView(int i, View view, ViewGroup viewGroup) {

            view= LayoutInflater.from(context).inflate(R.layout.custom_layout_for_time_slot,viewGroup,false);

            TextView txt_timeSlot=view.findViewById(R.id.txt_timeSlot);
            txt_timeSlot.setText(gridItems.get(i).getTxt_timeName());
            return view;
        }
    }
}