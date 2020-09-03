package com.clinicapp.drravibhaskar.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.clinicapp.drravibhaskar.activities.LoginActivity;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;


public class SharedPrefManagerAdmin {
    //the constants

    private static final String SHARED_PREF_NAME = "ashrmadminlogindata";
    private static final String USER_ID = "user_id";
    private static final String PATIENT_ID = "patientId";
    private static final String USER_NAME = "user_name";
    private static final String GENDER = "gender";
    private static final String DOB = "dob";
    private static final String AGE = "age";
    private static final String ADDRESS1 = "address";
    private static final String CONTACT = "contact";


    private static final String IMAGE = "image";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static SharedPrefManagerAdmin mInstance;
    private static Context mCtx;
    private SharedPrefManagerAdmin(Context context){
        mCtx=context;
    }
    public static synchronized SharedPrefManagerAdmin getInstance(Context context){
        if (mInstance==null){
            mInstance=new SharedPrefManagerAdmin(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences

    public void userLogin(ModelUser user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, user.getId());
        editor.putString(PATIENT_ID,user.getPatientID());
        editor.putString(USER_NAME,user.getName());
        editor.putString(GENDER,user.getGender());
        editor.putString(DOB,user.getDOB());
        editor.putString(AGE,user.getAge());
        editor.putString(ADDRESS1,user.getAddress1());
        editor.putString(CONTACT,user.getContactNo());
        editor.putString(IMAGE,user.getImages());
        editor.putString(EMAIL,user.getEmail());
        editor.putString(PASSWORD,user.getPassword());;
        editor.apply();
    }
//
    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, null) != null;
    }
//
    //this method will give the logged in user
    public ModelUser getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new ModelUser(
                sharedPreferences.getString(USER_ID, null),
                sharedPreferences.getString(PATIENT_ID, null),
                sharedPreferences.getString(USER_NAME, null),
                sharedPreferences.getString(GENDER, null),
                sharedPreferences.getString(DOB, null),
                sharedPreferences.getString(AGE, null),
                sharedPreferences.getString(ADDRESS1, null),
                sharedPreferences.getString(CONTACT, null),
                sharedPreferences.getString(IMAGE, null),
                sharedPreferences.getString(EMAIL, null),
                sharedPreferences.getString(PASSWORD, null)

        );
    }
//
//    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }

}
