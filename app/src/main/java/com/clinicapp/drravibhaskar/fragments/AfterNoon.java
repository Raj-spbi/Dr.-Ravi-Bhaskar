package com.clinicapp.drravibhaskar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.models.ModelForTimeSlots;

import java.util.ArrayList;


public class AfterNoon extends Fragment {
    GridView gridView;
    GridAdaptor gridAdaptor;
    ArrayList<ModelForTimeSlots> items;

    public AfterNoon() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_after_noon, container, false);
        gridView=view.findViewById(R.id.gridTimeSlot);
        setData();
        gridAdaptor=new GridAdaptor(getContext(),items);
        gridView.setAdapter(gridAdaptor);

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