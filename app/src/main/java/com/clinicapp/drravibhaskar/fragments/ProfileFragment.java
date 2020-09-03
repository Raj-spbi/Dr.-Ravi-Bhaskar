package com.clinicapp.drravibhaskar.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clinicapp.drravibhaskar.BottomSheetClass;
import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.activities.LoginActivity;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;
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
        if (!SharedPrefManagerAdmin.getInstance(getContext()).isLoggedIn()) {
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        ModelUser user = SharedPrefManagerAdmin.getInstance(getContext()).getUser();

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