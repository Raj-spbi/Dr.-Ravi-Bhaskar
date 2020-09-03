package com.clinicapp.drravibhaskar.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.activities.LoginActivity;
import com.clinicapp.drravibhaskar.adapters.AdapterCustomList1;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;
import com.clinicapp.drravibhaskar.models.ModelCustomList1;
import com.clinicapp.drravibhaskar.models.ModelImageBannerItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ListView listView;
    List<ModelCustomList1> modelCustomList1s;
    AdapterCustomList1 adapterCustomList1;

    ViewPager viewPager;
    TabLayout tabLayout;
    TextView username;
    MyCustomPagerAdaptor pagerAdaptor;
    ArrayList<ModelImageBannerItem> list;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        listView=view.findViewById(R.id.list1);
        if (!SharedPrefManagerAdmin.getInstance(getContext()).isLoggedIn()) {
            Intent intent=new Intent(getActivity(),LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        ModelUser user = SharedPrefManagerAdmin.getInstance(getContext()).getUser();

        viewPager=view.findViewById(R.id.viewPager);
        tabLayout=view.findViewById(R.id.tabview);
        username=view.findViewById(R.id.username);
        String Gender=user.getGender();
        if (Gender.equals("Male")){
            username.setText("Mr. "+user.getName())  ;
        }else if (Gender.equals("Female")){
            username.setText("Miss. "+user.getName())  ;
        }else {
            username.setText(user.getName())  ;
        }

        addImages();

        pagerAdaptor=new MyCustomPagerAdaptor(getContext(),list);
        viewPager.setAdapter(pagerAdaptor);
        tabLayout.setupWithViewPager(viewPager,true);

        modelCustomList1s=new ArrayList<>();
        modelCustomList1s.add(new ModelCustomList1("Book Appointment",R.drawable.ic_baseline_date_range_24));
        modelCustomList1s.add(new ModelCustomList1("Avail Home Service",R.drawable.stethoscope_1));
//        modelCustomList1s.add(new ModelCustomList1("Buy / Rent Equipments",R.drawable.ic_outline_accessible_24));
//        modelCustomList1s.add(new ModelCustomList1("Shop Medicine",R.drawable.ic_outline_add_box_24));
        adapterCustomList1=new AdapterCustomList1(getContext(),modelCustomList1s);
        listView.setAdapter(adapterCustomList1);
        return view;
    }


    private void addImages() {

        list=new ArrayList<>();
        list.add(new ModelImageBannerItem(R.drawable.banner1));
        list.add(new ModelImageBannerItem(R.drawable.banner2));
        list.add(new ModelImageBannerItem(R.drawable.banner3));
        list.add(new ModelImageBannerItem(R.drawable.banner4));
        list.add(new ModelImageBannerItem(R.drawable.banner5));

    }

    private  class MyCustomPagerAdaptor extends PagerAdapter {

        Context context;
        ArrayList<ModelImageBannerItem> bannerItems;
        LayoutInflater layoutInflater;

        public MyCustomPagerAdaptor(Context context, ArrayList<ModelImageBannerItem> bannerItems) {
            this.context = context;
            this.bannerItems = bannerItems;
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return bannerItems.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==((LinearLayout) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View itemView=layoutInflater.inflate(R.layout.imagebannerlayout,container,false);

            ImageView imageView=(ImageView)itemView.findViewById(R.id.imageView);
            imageView.setImageResource(bannerItems.get(position).getImages());
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((LinearLayout)object);
        }
    }
}