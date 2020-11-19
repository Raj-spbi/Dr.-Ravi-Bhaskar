package com.clinicapp.drravibhaskar.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.activities.LoginActivity;
import com.clinicapp.drravibhaskar.adapters.AdapterCustomList1;
import com.clinicapp.drravibhaskar.adapters.SliderAdapter;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;
import com.clinicapp.drravibhaskar.models.ModelCustomList1;
import com.clinicapp.drravibhaskar.models.ModelLogin;
import com.clinicapp.drravibhaskar.models.SliderModel;
import com.clinicapp.drravibhaskar.transformation.DepthPageTransformer;
import com.clinicapp.drravibhaskar.transformation.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment  {

    ListView listView;
    List<ModelCustomList1> modelCustomList1s;
    AdapterCustomList1 adapterCustomList1;


    TextView username,txt_visitWebsite;


    ViewPager viewPager;
    List<SliderModel> sliderModels;
    private int currentPage = 2;
    private Timer timer;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.list1);
        txt_visitWebsite=view.findViewById(R.id.txt_visitWebsite);


        if (!SharedPrefManagerAdmin.getInstance(getContext()).isLoggedIn()) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        ModelLogin.ResultRow user=SharedPrefManagerAdmin.getInstance(getContext()).getUser();

        username = view.findViewById(R.id.username);
        String Gender = user.getGender();
        if (Gender.equals("Male")){
            username.setText("Mr. "+user.getName())  ;
        }else if (Gender.equals("Female")){
            username.setText("Miss. "+user.getName())  ;
        }else {
            username.setText(user.getName())  ;
        }

        txt_visitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.drravibhaskar.com/"));
                startActivity(intent);
            }
        });
//        addImages();



//        All About Slider .... Starts Here
        viewPager = view.findViewById(R.id.viewPager);
        sliderModels = new ArrayList<>();
        sliderModels.add(new SliderModel(R.drawable.banner1));
        sliderModels.add(new SliderModel(R.drawable.banner2));

        sliderModels.add(new SliderModel(R.drawable.banner3));
        sliderModels.add(new SliderModel(R.drawable.banner4));
        sliderModels.add(new SliderModel(R.drawable.banner5));
        sliderModels.add(new SliderModel(R.drawable.banner2));
        sliderModels.add(new SliderModel(R.drawable.banner1));
        sliderModels.add(new SliderModel(R.drawable.banner2));

        sliderModels.add(new SliderModel(R.drawable.banner3));
        sliderModels.add(new SliderModel(R.drawable.banner4));
        SliderAdapter sliderAdapter = new SliderAdapter(sliderModels);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(20);
        viewPager.setCurrentItem(currentPage);
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    pageLooper();
                }
            }
        };
        viewPager.addOnPageChangeListener(onPageChangeListener);
        startbannerSlideShow();
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pageLooper();
                stopbannerSlideShow();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    startbannerSlideShow();
                }
                return false;
            }
        });

        Random random = new Random();
        int n = random.nextInt(5);
        if (n == 0) {
            viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    viewPager.setPageTransformer(true, new DepthPageTransformer());

                }
            });

        } else {

            viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
                }
            });

        }
//         Slider Ends Here..................



        modelCustomList1s=new ArrayList<>();
        modelCustomList1s.add(new ModelCustomList1("Book Appointment", R.drawable.ic_baseline_date_range_24));
        modelCustomList1s.add(new ModelCustomList1("Avail Home Service", R.drawable.stethoscope_1));
        modelCustomList1s.add(new ModelCustomList1("Gallery", R.drawable.ic_gallery));
        modelCustomList1s.add(new ModelCustomList1("Awareness Videos", R.drawable.ic_ribbon));
//        modelCustomList1s.add(new ModelCustomList1("Buy / Rent Equipments",R.drawable.ic_outline_accessible_24));
//        modelCustomList1s.add(new ModelCustomList1("Shop Medicine",R.drawable.ic_outline_add_box_24));
        adapterCustomList1 = new AdapterCustomList1(getContext(), modelCustomList1s);
        listView.setAdapter(adapterCustomList1);
        return view;
    }
    private void pageLooper() {
        if (currentPage == sliderModels.size() - 2) {
            currentPage = 2;
            viewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1) {
            currentPage = sliderModels.size() - 3;
            viewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startbannerSlideShow() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModels.size()) {
                    currentPage = 1;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 3000);
    }

    private void stopbannerSlideShow() {
        timer.cancel();
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