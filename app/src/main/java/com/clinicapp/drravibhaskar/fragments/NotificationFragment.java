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
import android.widget.ImageView;
import android.widget.TextView;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.models.ModelForAppointHistory;
import com.clinicapp.drravibhaskar.models.ModelForNoti;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    List<ModelForNoti> forNotis;
    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        forNotis=new ArrayList<>();
        forNotis.add(new ModelForNoti(R.drawable.banner1,"Hello Parvej ! Dr. Ravi Bhaskar has posted a notification May be it is For you... "));
        forNotis.add(new ModelForNoti(R.drawable.banner2,"Hello Rajat ! Dr. Ravi Bhaskar has posted a notification May be it is For you... "));
        forNotis.add(new ModelForNoti(R.drawable.banner3,"Hello Rajesh ! Dr. Ravi Bhaskar has posted a notification May be it is For you... "));
        forNotis.add(new ModelForNoti(R.drawable.banner4,"Hello Parvez ! Dr. Ravi Bhaskar has posted a notification May be it is For you... "));
        forNotis.add(new ModelForNoti(R.drawable.banner5,"Hello Ajay ! Dr. Ravi Bhaskar has posted a notification May be it is For you... "));
        AdapterNoti adapterAppointHistory=new AdapterNoti(getContext(),forNotis);
        recyclerView.setAdapter(adapterAppointHistory);


        return view;
    }
    private class AdapterNoti extends RecyclerView.Adapter<AdapterNoti.ExViewHolder>{

        Context context;
        List<ModelForNoti> modelForNotis;

        public AdapterNoti(Context context, List<ModelForNoti> modelForNotis) {
            this.context = context;
            this.modelForNotis = modelForNotis;
        }

        @NonNull
        @Override
        public ExViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_noti,parent,false);
            return new ExViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ExViewHolder holder, int position) {
            holder.notil.setText(modelForNotis.get(position).getNoti());
            holder.img.setImageResource(modelForNotis.get(position).getImageRes());
        }

        @Override
        public int getItemCount() {
            return modelForNotis.size();
        }

        public class ExViewHolder extends RecyclerView.ViewHolder{

            ImageView img;
            TextView notil;
            public ExViewHolder(@NonNull View itemView) {
                super(itemView);
                img=itemView.findViewById(R.id.image);
                notil=itemView.findViewById(R.id.notification);
            }
        }
    }
}