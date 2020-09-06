package com.clinicapp.drravibhaskar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.activities.LoginActivity;
import com.clinicapp.drravibhaskar.adapters.AdapterCustomList1;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;
import com.clinicapp.drravibhaskar.models.ModelCustomList1;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {

    ListView listView;
    List<ModelCustomList1> modelCustomList1s;
    AdapterCustomList1 adapterCustomList1;

    ViewPager viewPager;
    TabLayout tabLayout;
    TextView username;
    //    MyCustomPagerAdaptor pagerAdaptor;
//    ArrayList<ModelImageBannerItem> list;
    SliderLayout mDemoSlider;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.list1);

        mDemoSlider = view.findViewById(R.id.slider);
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Chat with Doctor", R.drawable.banner5);
        file_maps.put("Big Bang Theory", R.drawable.banner3);
        file_maps.put("House of Cards", R.drawable.banner1);
        file_maps.put("Test Booking", R.drawable.banner2);
        file_maps.put("Talk With Your Doctor", R.drawable.banner4);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(com.daimajia.slider.library.SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(com.daimajia.slider.library.SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);
        Random rand = new Random();

// Obtain a number between [0 - 49].
        int n = rand.nextInt(5);
        {
            switch (n) {
                case 0:
                    mDemoSlider.setPresetTransformer("FlipHorizontal");
                    break;
                case 1:
                    mDemoSlider.setPresetTransformer("CubeIn");
                    break;
                case 2:
                    mDemoSlider.setPresetTransformer("DepthPage");
                    break;
                case 3:
                    mDemoSlider.setPresetTransformer("Tablet");
                    break;
                case 4:
                    mDemoSlider.setPresetTransformer("Stack");
                    break;
                case 5:
                    mDemoSlider.setPresetTransformer("Foreground2Background");
                    break;
                case 6:
                    mDemoSlider.setPresetTransformer("Background2Foreground");
                    break;
                case 7:
                    mDemoSlider.setPresetTransformer("Accordian");
                    break;
                default:
                    mDemoSlider.setPresetTransformer("Default");
                    break;


            }
        }
//        mDemoSlider.setPresetTransformer("FlipHorizontal");
        mDemoSlider.addOnPageChangeListener(this);


        if (!SharedPrefManagerAdmin.getInstance(getContext()).isLoggedIn()) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        ModelUser user = SharedPrefManagerAdmin.getInstance(getContext()).getUser();

        username = view.findViewById(R.id.username);
        String Gender = user.getGender();
        if (Gender.equals("Male")){
            username.setText("Mr. "+user.getName())  ;
        }else if (Gender.equals("Female")){
            username.setText("Miss. "+user.getName())  ;
        }else {
            username.setText(user.getName())  ;
        }

//        addImages();



        modelCustomList1s=new ArrayList<>();
        modelCustomList1s.add(new ModelCustomList1("Book Appointment", R.drawable.ic_baseline_date_range_24));
        modelCustomList1s.add(new ModelCustomList1("Avail Home Service", R.drawable.stethoscope_1));
//        modelCustomList1s.add(new ModelCustomList1("Buy / Rent Equipments",R.drawable.ic_outline_accessible_24));
//        modelCustomList1s.add(new ModelCustomList1("Shop Medicine",R.drawable.ic_outline_add_box_24));
        adapterCustomList1 = new AdapterCustomList1(getContext(), modelCustomList1s);
        listView.setAdapter(adapterCustomList1);
        return view;
    }


//    private void addImages() {
//
//        list=new ArrayList<>();
//        list.add(new ModelImageBannerItem(R.drawable.banner1));
//        list.add(new ModelImageBannerItem(R.drawable.banner2));
//        list.add(new ModelImageBannerItem(R.drawable.banner3));
//        list.add(new ModelImageBannerItem(R.drawable.banner4));
//        list.add(new ModelImageBannerItem(R.drawable.banner5));
//
//    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Toast.makeText(this, "Scrolled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
//        Toast.makeText(this, "Page Changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        Toast.makeText(this, "Page Changed "+state, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getContext(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

//    private  class MyCustomPagerAdaptor extends PagerAdapter {
//
//        Context context;
//        ArrayList<ModelImageBannerItem> bannerItems;
//        LayoutInflater layoutInflater;
//
//        public MyCustomPagerAdaptor(Context context, ArrayList<ModelImageBannerItem> bannerItems) {
//            this.context = context;
//            this.bannerItems = bannerItems;
//            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public int getCount() {
//            return bannerItems.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//            return view==((LinearLayout) object);
//        }
//
//        @NonNull
//        @Override
//        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//
//            View itemView=layoutInflater.inflate(R.layout.imagebannerlayout,container,false);
//
//            ImageView imageView=(ImageView)itemView.findViewById(R.id.imageView);
//            imageView.setImageResource(bannerItems.get(position).getImages());
//            container.addView(itemView);
//            return itemView;
//        }
//
//        @Override
//        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            container.removeView((LinearLayout)object);
//        }
//    }
}