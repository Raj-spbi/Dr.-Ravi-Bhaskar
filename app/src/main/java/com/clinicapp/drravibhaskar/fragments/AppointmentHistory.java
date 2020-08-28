package com.clinicapp.drravibhaskar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.models.ModelForAppointHistory;

import java.util.ArrayList;
import java.util.List;

public class AppointmentHistory extends Fragment {


    RecyclerView recyclerView;
    List<ModelForAppointHistory> modelForAppointHistories;

    public AppointmentHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_appointment_history, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        modelForAppointHistories=new ArrayList<>();
        modelForAppointHistories.add(new ModelForAppointHistory("20 April 2020","12:20 PM","DRRAVI12345678"));
        modelForAppointHistories.add(new ModelForAppointHistory("20 April 2020","12:20 PM","DRRAVI12345678"));
        modelForAppointHistories.add(new ModelForAppointHistory("20 April 2020","12:20 PM","DRRAVI12345678"));
        modelForAppointHistories.add(new ModelForAppointHistory("20 April 2020","12:20 PM","DRRAVI12345678"));
        modelForAppointHistories.add(new ModelForAppointHistory("20 April 2020","12:20 PM","DRRAVI12345678"));
        modelForAppointHistories.add(new ModelForAppointHistory("20 April 2020","12:20 PM","DRRAVI12345678"));
        modelForAppointHistories.add(new ModelForAppointHistory("20 April 2020","12:20 PM","DRRAVI12345678"));
        modelForAppointHistories.add(new ModelForAppointHistory("20 April 2020","12:20 PM","DRRAVI12345678"));
        modelForAppointHistories.add(new ModelForAppointHistory("20 April 2020","12:20 PM","DRRAVI12345678"));
        AdapterAppointHistory adapterAppointHistory=new AdapterAppointHistory(getContext(),modelForAppointHistories);
        recyclerView.setAdapter(adapterAppointHistory);


        return view;
    }
    private class AdapterAppointHistory extends RecyclerView.Adapter<AdapterAppointHistory.ExViewHolder>{
        Context context;
        List<ModelForAppointHistory> models;

        public AdapterAppointHistory(Context context, List<ModelForAppointHistory> models) {
            this.context = context;
            this.models = models;
        }

        @NonNull
        @Override
        public ExViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view=LayoutInflater.from(getContext()).inflate(R.layout.custom_appoint,parent,false);
         return new ExViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ExViewHolder holder, int position) {
            holder.date.setText(models.get(position).getDate());
            holder.slot.setText(models.get(position).getSlot());
            holder.app_id.setText(models.get(position).getAppoint_id());
        }

        @Override
        public int getItemCount() {
            return models.size();
        }

        public class ExViewHolder extends RecyclerView.ViewHolder{

            TextView date,slot,app_id;
            public ExViewHolder(@NonNull View itemView) {
                super(itemView);
                date=itemView.findViewById(R.id.date);
                slot=itemView.findViewById(R.id.slot);
                app_id=itemView.findViewById(R.id.app_id);
            }
        }
    }
}