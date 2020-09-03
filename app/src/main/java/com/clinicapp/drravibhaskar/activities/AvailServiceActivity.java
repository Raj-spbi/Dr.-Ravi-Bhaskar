package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.models.ModelGrid;

import java.util.ArrayList;
import java.util.List;

public class AvailServiceActivity extends AppCompatActivity {

    GridView gridView;
    List<ModelGrid> modelGrids;
    AdapterAvailService adapterAvailService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avail_service);
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

        modelGrids=new ArrayList<>();
        modelGrids.add(new ModelGrid(R.drawable.ic_baseline_exit_to_app_24,"Medicine\nDelivery"));
        modelGrids.add(new ModelGrid(R.drawable.ic_baseline_date_range_24,"Nursing Care"));
        modelGrids.add(new ModelGrid(R.drawable.ic_baseline_favorite_24,"Equipment Rental"));
        modelGrids.add(new ModelGrid(R.drawable.ic_baseline_notifications_none_24,"Investigations"));

        adapterAvailService=new AdapterAvailService(getApplicationContext(),modelGrids);
        gridView.setAdapter(adapterAvailService);
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
            TextView textView = view.findViewById(R.id.title);
            ImageView image = view.findViewById(R.id.image);
            textView.setText(modelGrids.get(i).getName());
            image.setImageResource(modelGrids.get(i).getImageRes());
            CardView cardView = view.findViewById(R.id.cover);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "" + modelGrids.get(i).getName(), Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder=new AlertDialog.Builder(AvailServiceActivity.this);
                    View view1=getLayoutInflater().inflate(R.layout.custom_alert_home_avail,null);

                    builder.setView(view1);
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

                }
            });
            return view;
        }

    }
}