package com.clinicapp.drravibhaskar.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.models.SliderModel;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    List<SliderModel> sliderModels;

    public SliderAdapter(List<SliderModel> sliderModels) {
        this.sliderModels = sliderModels;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slide_layout, container, false);

        LinearLayout action_container = view.findViewById(R.id.action_container);
//        action_container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor()));
        ImageView banner_slider = view.findViewById(R.id.banner_slider);
        banner_slider.setImageResource(sliderModels.get(position).getBanner());
        container.addView(view, 0);
        return view;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }


    @Override
    public int getCount() {
        return sliderModels.size();
    }


}