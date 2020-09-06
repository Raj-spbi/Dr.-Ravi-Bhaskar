package com.clinicapp.drravibhaskar.activities;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

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

        modelGrids = new ArrayList<>();
        modelGrids.add(new ModelGrid(R.drawable.cart_ic, "Medicine\nDelivery"));
        modelGrids.add(new ModelGrid(R.drawable.nursing_care_ic, "Nursing Care"));
        modelGrids.add(new ModelGrid(R.drawable.equipmment_rental_ic, "Equipment Rental"));
        modelGrids.add(new ModelGrid(R.drawable.consultation_ic, "Investigations"));

        adapterAvailService = new AdapterAvailService(getApplicationContext(), modelGrids);
        gridView.setAdapter(adapterAvailService);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
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
//                    Toast.makeText(context, "" + modelGrids.get(i).getName(), Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder=new AlertDialog.Builder(AvailServiceActivity.this);
                    View view1=getLayoutInflater().inflate(R.layout.custom_alert_home_avail,null);

                    ImageView cross=view1.findViewById(R.id.cross);
                    TextView save=view1.findViewById(R.id.save);

                    builder.setView(view1);
                    final AlertDialog alertDialog=builder.create();
                    cross.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(AvailServiceActivity.this, "We'll Contact You As Soon As Possible", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();

                }
            });
            return view;
        }

    }
}