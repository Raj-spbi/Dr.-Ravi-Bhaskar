package com.clinicapp.drravibhaskar.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clinicapp.drravibhaskar.R;
import com.skydoves.elasticviews.ElasticButton;


public class PrivacyAndPolicy extends Fragment {

    ElasticButton btn_readMore;

    public PrivacyAndPolicy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_privacy_and_policy, container, false);

        btn_readMore=view.findViewById(R.id.btn_readMore);

        btn_readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readMore();
            }
        });


        return view;
    }

    private void readMore() {

        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.drravibhaskar.com/privacy-policy.aspx"));
        startActivity(intent);

    }
}