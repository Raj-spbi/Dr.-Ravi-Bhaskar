package com.clinicapp.drravibhaskar.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import com.clinicapp.drravibhaskar.BookAppointmentActivity;
import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.models.ModelCustomList1;
import java.util.List;

public class AdapterCustomList1 extends BaseAdapter {
    Context context;
    List<ModelCustomList1> modelCustomList1s;


    public AdapterCustomList1(Context context, List<ModelCustomList1> modelCustomList1s) {
        this.context = context;
        this.modelCustomList1s = modelCustomList1s;
    }

    @Override
    public int getCount() {
        return modelCustomList1s.size();
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
        view= LayoutInflater.from(context).inflate(R.layout.custom_layout1,viewGroup,false);
        TextView textView=view.findViewById(R.id.title);
        ImageView imageView=view.findViewById(R.id.image);
        textView.setText(modelCustomList1s.get(i).getTitle());
        imageView.setImageResource(modelCustomList1s.get(i).getImage());
        CardView container=view.findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    Intent intent = new Intent(context, BookAppointmentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Available Soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
