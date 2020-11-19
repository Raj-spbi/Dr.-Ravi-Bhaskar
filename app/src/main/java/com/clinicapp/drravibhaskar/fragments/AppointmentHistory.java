package com.clinicapp.drravibhaskar.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.activities.LoginActivity;
import com.clinicapp.drravibhaskar.activities.MainActivity;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;
import com.clinicapp.drravibhaskar.managers.VolleySingleton;
import com.clinicapp.drravibhaskar.managers.WebURLS;
import com.clinicapp.drravibhaskar.models.ModelForAppointHistory;
import com.clinicapp.drravibhaskar.models.ModelLogin;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentHistory extends Fragment {

    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    String patientId="";

    AdapterAppointHistory adapterAppointHistory;
    ImageView img_dataNotFound;



    public AppointmentHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment_history, container, false);
        progressDialog = new ProgressDialog(getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        img_dataNotFound=view.findViewById(R.id.img_dataNotFound);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        getHistory();

        return view;
    }

    private void getHistory() {
        ModelLogin.ResultRow data=SharedPrefManagerAdmin.getInstance(getContext()).getUser();
        patientId=data.getPatientId();
        progressDialog.show();
        progressDialog.setTitle("Please wait....");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebURLS.BOOKING_HISTORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("resuktres", "onResponse: "+response);
                Gson gson=new Gson();
                try {
                    ModelForAppointHistory.Example example=gson.fromJson(response, ModelForAppointHistory.Example.class);
                    if (example.Error.equals("Success")){
                        progressDialog.dismiss();
                        ArrayList<ModelForAppointHistory.ResultRow> data=example.ResultRows;
                        adapterAppointHistory=new AdapterAppointHistory(getContext(),data);
                        recyclerView.setAdapter(adapterAppointHistory);
                        img_dataNotFound.setVisibility(View.GONE);
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "List Not Found", Toast.LENGTH_SHORT).show();
                        img_dataNotFound.setVisibility(View.VISIBLE);
                    }
                } catch (JsonSyntaxException e) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "No Record Found", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                img_dataNotFound.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("PatientID",patientId);
                return map;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


    private class AdapterAppointHistory extends RecyclerView.Adapter<AdapterAppointHistory.ExViewHolder> {
        Context context;
        ArrayList<ModelForAppointHistory.ResultRow> models;

        public AdapterAppointHistory(Context context, ArrayList<ModelForAppointHistory.ResultRow> models) {
            this.context = context;
            this.models = models;
        }

        @NonNull
        @Override
        public ExViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_appoint, parent, false);
            return new ExViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ExViewHolder holder, int position) {
            holder.date.setText(models.get(position).getBookingDate());
            holder.slot.setText(models.get(position).getTimeSlot());
            holder.bookingId.setText(models.get(position).getBookingID());
        }

        @Override
        public int getItemCount() {
            return models.size();
        }

        public class ExViewHolder extends RecyclerView.ViewHolder {

            TextView date, slot, bookingId;

            public ExViewHolder(@NonNull View itemView) {
                super(itemView);
                date = itemView.findViewById(R.id.BookingDate);
                slot = itemView.findViewById(R.id.TimeSlot);
                bookingId = itemView.findViewById(R.id.BookingID);
            }
        }
    }


}