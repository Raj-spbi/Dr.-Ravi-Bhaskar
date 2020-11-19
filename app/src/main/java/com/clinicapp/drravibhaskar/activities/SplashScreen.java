package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.clinicapp.drravibhaskar.R;
import com.clinicapp.drravibhaskar.managers.SharedPrefManagerAdmin;

public class SplashScreen extends AppCompatActivity {

    ImageView image;
    Animation shrink_and_rotate;
    LinearLayout lin_internet;
    Button btn_load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image=findViewById(R.id.image);
        lin_internet=findViewById(R.id.lin_internet);
        btn_load=findViewById(R.id.btn_load);
        CheckInternetPermission();

    }

    private void CheckInternetPermission() {

        if(isOnline()){
            //Toast.makeText(this, "You Are Connected", Toast.LENGTH_SHORT).show();
            image.setVisibility(View.VISIBLE);
            lin_internet.setVisibility(View.GONE);
            shrink_and_rotate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shrik_and_rotate);
            image.startAnimation(shrink_and_rotate);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (SharedPrefManagerAdmin.getInstance(SplashScreen.this).isLoggedIn()) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }  else {
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        finish();
                    }

                }
            },2000);

        }
        else{
            //Toast.makeText(this, "You Are not connected", Toast.LENGTH_SHORT).show();
            lin_internet.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
            btn_load.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckInternetPermission();
                }
            });
        }

    }

    private boolean isOnline() {

        ConnectivityManager cm= (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo !=null && netInfo.isConnectedOrConnecting()){
            return true;
        }
        else {
            return false;
        }

    }
}