package com.clinicapp.drravibhaskar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clinicapp.drravibhaskar.BottomSheetClass;
import com.clinicapp.drravibhaskar.R;
import com.skydoves.elasticviews.ElasticButton;


public class ProfileFragment extends Fragment {


    ElasticButton editProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        editProfile=view.findViewById(R.id.editProfile);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetClass bottomSheetClass=new BottomSheetClass();
                bottomSheetClass.show(getFragmentManager(),bottomSheetClass.getTag());
            }
        });

        return view;
    }
}