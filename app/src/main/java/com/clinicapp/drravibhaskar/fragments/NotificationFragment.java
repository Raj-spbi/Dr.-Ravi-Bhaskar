package com.clinicapp.drravibhaskar.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.clinicapp.drravibhaskar.managers.VolleySingleton;
import com.clinicapp.drravibhaskar.managers.WebURLS;
import com.clinicapp.drravibhaskar.models.ModelForAppointHistory;
import com.clinicapp.drravibhaskar.models.ModelForNoti;
import com.google.gson.Gson;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterNoti adapterNoti;

    public NotificationFragment() {
        // Required empty public constructor
    }
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        progressDialog=new ProgressDialog(getContext());
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setNotificationData();

        return view;
    }

    private void setNotificationData() {
        progressDialog.show();
        progressDialog.setCancelable(false);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, WebURLS.PAIENT_STORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("datata", "onResponse: "+response);
                Gson gson=new Gson();
                ModelForNoti.Example example=gson.fromJson(response, ModelForNoti.Example.class);
                if (example.Error.equalsIgnoreCase("Success")){
                    progressDialog.dismiss();
                    List<ModelForNoti.ResultRow> resultRows=example.ResultRows;
                    adapterNoti=new AdapterNoti(getContext(),resultRows);
                    recyclerView.setAdapter(adapterNoti);
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Record not found", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                if (error instanceof NoConnectionError) {
                    ConnectivityManager cm = (ConnectivityManager) getActivity()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = null;
                    if (cm != null) {
                        activeNetwork = cm.getActiveNetworkInfo();
                    }
                    if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                        Toast.makeText(getActivity(), "Server is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Your device is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                        && error.getCause().getMessage().contains("connection")) {
                    Toast.makeText(getActivity(), "Your device is not connected to internet.",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof MalformedURLException) {
                    Toast.makeText(getActivity(), "Bad Request.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                        || error.getCause() instanceof JSONException
                        || error.getCause() instanceof XmlPullParserException) {
                    Toast.makeText(getActivity(), "Parse Error (because of invalid json or xml).",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof OutOfMemoryError) {
                    Toast.makeText(getActivity(), "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getActivity(), "server couldn't find the authenticated request.",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                    Toast.makeText(getActivity(), "Server is not responding.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                        || error.getCause() instanceof ConnectTimeoutException
                        || error.getCause() instanceof SocketException
                        || (error.getCause().getMessage() != null
                        && error.getCause().getMessage().contains("Connection timed out"))) {
                    Toast.makeText(getActivity(), "Connection timeout error",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "An unknown error occurred.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    private class AdapterNoti extends RecyclerView.Adapter<AdapterNoti.ExViewHolder>{

        String convTime=null;
        Context context;
        List<ModelForNoti.ResultRow> modelForNotis;

        public AdapterNoti(Context context, List<ModelForNoti.ResultRow> modelForNotis) {
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
            holder.notification.setText(modelForNotis.get(position).getName());

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
                Date past = format.parse(modelForNotis.get(position).getUploadDt());
                Date now = new Date();
                long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
                long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
                long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
                long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());

                if (seconds == 0) {
                    holder.txt_date.setText("just now");
                } else if (seconds < 60 && seconds > 0) {
                    holder.txt_date.setText(seconds + " seconds ago");
                } else if (minutes < 60) {
                    holder.txt_date.setText(minutes + " minutes ago");
                } else if (hours < 24) {
                    holder.txt_date.setText(hours + " hours ago");
                } else if (days >= 7) {
                    if (days > 360) {
                        convTime = (days / 360) + " years ago ";
                        holder.txt_date.setText(convTime);
                    } else if (days > 30) {
                        convTime = (days / 30) + " months ago ";
                        holder.txt_date.setText(convTime);
                    } else {
                        convTime = (days / 7) + " week ago";
                        holder.txt_date.setText(convTime);
                    }
                } else if (days < 7) {
                    convTime = days + " days ago";
                    holder.txt_date.setText(convTime);
                }
            }
            catch (Exception j){
                j.printStackTrace();
            }
//            holder.txt_date.setText(modelForNotis.get(position).getUploadDt());
            holder.txt_message.setText(modelForNotis.get(position).getMsg());
        }

        @Override
        public int getItemCount() {
            return modelForNotis.size();
        }

        public class ExViewHolder extends RecyclerView.ViewHolder{

            TextView notification,txt_date,txt_message;
            public ExViewHolder(@NonNull View itemView) {
                super(itemView);
                notification=itemView.findViewById(R.id.notification);
                txt_date=itemView.findViewById(R.id.txt_date);
                txt_message=itemView.findViewById(R.id.txt_message);
            }
        }
    }
}