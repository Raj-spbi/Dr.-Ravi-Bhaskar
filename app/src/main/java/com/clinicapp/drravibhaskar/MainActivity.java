package com.clinicapp.drravibhaskar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.clinicapp.drravibhaskar.activities.LoginActivity;
import com.clinicapp.drravibhaskar.activities.MessageActivity;
import com.clinicapp.drravibhaskar.fragments.AboutUsFragment;
import com.clinicapp.drravibhaskar.fragments.ChangePasswordFragment;
import com.clinicapp.drravibhaskar.fragments.ContactUsFragment;
import com.clinicapp.drravibhaskar.fragments.EveningFragment;
import com.clinicapp.drravibhaskar.fragments.HomeFragment;
import com.clinicapp.drravibhaskar.fragments.MorningFragment;
import com.clinicapp.drravibhaskar.fragments.PrivacyAndPolicy;
import com.clinicapp.drravibhaskar.fragments.ProfileFragment;
import com.clinicapp.drravibhaskar.fragments.TermsAndCondition;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.skydoves.elasticviews.ElasticCardView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;

    public static FragmentManager FRAGMENT_MANAGER = null;
    NavigationView navigationView;
    RelativeLayout container;
    ElasticCardView home, profile,
            changepassword,aboutus, contactus, share, settings, privacypolicy, termsandcondition,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (RelativeLayout) findViewById(R.id.container);

        FRAGMENT_MANAGER=getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction=FRAGMENT_MANAGER.beginTransaction();
        fragmentTransaction.replace(R.id.container,new HomeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        //Nav Menus
        home = findViewById(R.id.home);
        profile = findViewById(R.id.profile);
        changepassword = findViewById(R.id.changepassword);
        aboutus = findViewById(R.id.aboutus);
        contactus = findViewById(R.id.contactus);
        share = findViewById(R.id.share);
        settings = findViewById(R.id.settings);
        privacypolicy=findViewById(R.id.privacypolicy);
        logout=findViewById(R.id.logout);
        termsandcondition=findViewById(R.id.termsandconditions);

        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar);
        if (myToolBar != null) {
            setSupportActionBar(myToolBar);
             myToolBar.hideOverflowMenu();
        }

        //For drawer
        navigationView = findViewById(R.id.navigation_view);
        drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolBar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        myToolBar.setNavigationIcon(R.drawable.menuravi);



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nav Menus Click Handlings.....
                drawer.closeDrawer(GravityCompat.START);
                 FragmentManager fragmentManagerz=getSupportFragmentManager();
                 FragmentTransaction fragmentTransactionz=fragmentManagerz.beginTransaction();
                fragmentTransactionz.replace(R.id.container,new HomeFragment());
                fragmentTransactionz.addToBackStack(null);
                fragmentTransactionz.commit();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nav Menus Click Handlings.....
                drawer.closeDrawer(GravityCompat.START);
                 FragmentManager fragmentManagerz=getSupportFragmentManager();
                 FragmentTransaction fragmentTransactionz=fragmentManagerz.beginTransaction();
                fragmentTransactionz.replace(R.id.container,new ProfileFragment());
                fragmentTransactionz.addToBackStack(null);
                fragmentTransactionz.commit();
            }
        });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nav Menus Click Handlings.....
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager fragmentManagerz=getSupportFragmentManager();
                FragmentTransaction fragmentTransactionz=fragmentManagerz.beginTransaction();
                fragmentTransactionz.replace(R.id.container,new ChangePasswordFragment());
                fragmentTransactionz.addToBackStack(null);
                fragmentTransactionz.commit();
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nav Menus Click Handlings.....
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager fragmentManagerz=getSupportFragmentManager();
                FragmentTransaction fragmentTransactionz=fragmentManagerz.beginTransaction();
                fragmentTransactionz.replace(R.id.container,new AboutUsFragment());
                fragmentTransactionz.addToBackStack(null);
                fragmentTransactionz.commit();
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nav Menus Click Handlings.....
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager fragmentManagerz=getSupportFragmentManager();
                FragmentTransaction fragmentTransactionz=fragmentManagerz.beginTransaction();
                fragmentTransactionz.replace(R.id.container,new ContactUsFragment());
                fragmentTransactionz.addToBackStack(null);
                fragmentTransactionz.commit();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nav Menus Click Handlings.....
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager fragmentManagerz=getSupportFragmentManager();
                FragmentTransaction fragmentTransactionz=fragmentManagerz.beginTransaction();
                fragmentTransactionz.replace(R.id.container,new EveningFragment());
                fragmentTransactionz.addToBackStack(null);
                fragmentTransactionz.commit();
            }
        });
        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nav Menus Click Handlings.....
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager fragmentManagerz=getSupportFragmentManager();
                FragmentTransaction fragmentTransactionz=fragmentManagerz.beginTransaction();
                fragmentTransactionz.replace(R.id.container,new PrivacyAndPolicy());
                fragmentTransactionz.addToBackStack(null);
                fragmentTransactionz.commit();
            }
        });
        termsandcondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nav Menus Click Handlings.....
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager fragmentManagerz=getSupportFragmentManager();
                FragmentTransaction fragmentTransactionz=fragmentManagerz.beginTransaction();
                fragmentTransactionz.replace(R.id.container,new TermsAndCondition());
                fragmentTransactionz.addToBackStack(null);
                fragmentTransactionz.commit();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent.putExtra(Intent.EXTRA_TEXT, "Download the App For Booking your Appointment with Dr. Ravi Bhaskar.");
                startActivity(Intent.createChooser(intent, getString(R.string.share)));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });




        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        FragmentManager fragmentManager1=getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
                        switch (item.getItemId()) {
                            case R.id.appointments:
                                fragmentTransaction1.replace(R.id.container,new EveningFragment());
//                                Toast.makeText(MainActivity.this, "Feature", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.myhome:
                                fragmentTransaction1.replace(R.id.container,new HomeFragment());
//                                Toast.makeText(MainActivity.this, "Shedules", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.notification:
                                fragmentTransaction1.replace(R.id.container,new MorningFragment());
//                                Toast.makeText(MainActivity.this, "Music", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        fragmentTransaction1.addToBackStack(null);
                        fragmentTransaction1.commit();
                        return false;
                    }
                });

    }



    public boolean isInternetConnected() {
        // At activity startup we manually check the internet status and change
        // the text status
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.message:
//                Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), MessageActivity.class);
                startActivity(intent);
                finish();
                break;
//            case R.id.donations:
//                Toast.makeText(this, "Bookmarked", Toast.LENGTH_SHORT).show();
////                intent = new Intent(getApplicationContext(), AdminShowDonationActivity.class);
////                startActivity(intent);
////                finish();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);

            builder.setIcon(R.drawable.ic_baseline_exit_to_app_24);
            builder.setMessage("Do you want to Exit");
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }


}