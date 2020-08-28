package com.clinicapp.drravibhaskar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.clinicapp.drravibhaskar.MainActivity;
import com.clinicapp.drravibhaskar.R;

public class SplashScreen extends AppCompatActivity {

    ImageView image;
    Animation shrink_and_rotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image=findViewById(R.id.image);
        shrink_and_rotate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shrik_and_rotate);
        image.startAnimation(shrink_and_rotate);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        },2000);
    }
}