package com.clinicapp.drravibhaskar.managers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.clinicapp.drravibhaskar.activities.LoginActivity;
import com.clinicapp.drravibhaskar.apimodels.ModelUser;
import com.clinicapp.drravibhaskar.models.ModelLogin;

import java.util.List;


public class SharedPrefManagerAdmin {
    //the constants

    private static final String SHARED_PREF_NAME = "PatientLoginDetails";
    private static String Id="Id";
    private static final String PatientId = "PatientId";
    private static final String Name = "Name";
    private static final String Gender = "Gender";
    private static final String dob = "dob";
    private static final String mobileno = "mobileno";
    private static final String email = "email";
    private static final String BloodGroup = "BloodGroup";
    private static final String StreetAddress = "StreetAddress";
    private static final String Locatity = "Locatity";
    private static final String City="City";
    private static final String PinCode="PinCode";
    private static final String Images = "Images";
    private static final String CreateDate = "CreateDate";
    private static final String IsActive = "IsActive";
    private static final String IsDeleted = "IsDeleted";
    private static final String Password = "Password";

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

    public void userLogin(ModelLogin.ResultRow user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Id, user.getId());
        editor.putString(PatientId,user.getPatientId());
        editor.putString(Name,user.getName());
        editor.putString(Gender,user.getGender());
        editor.putString(dob,user.getDob());
        editor.putString(mobileno,user.getMobileno());
        editor.putString(email,user.getEmail());
        editor.putString(BloodGroup,user.getBloodGroup());
        editor.putString(StreetAddress,user.getStreetAddress());
        editor.putString(Locatity,user.getLocatity());
        editor.putString(City,user.getCity());
        editor.putString(PinCode,user.getPinCode());
        editor.putString(Images,user.getImages());
        editor.putString(CreateDate,user.getCreateDate());
        editor.putString(IsActive,user.getIsActive());;
        editor.putString(IsDeleted,user.getIsDeleted());;
        editor.putString(Password,user.getPassword());;
        editor.apply();
    }
//
    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Name, null) != null;
    }
//
    //this method will give the logged in user
    public ModelLogin.ResultRow getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new ModelLogin.ResultRow(
                sharedPreferences.getString(Id,null),
                sharedPreferences.getString(PatientId, null),
                sharedPreferences.getString(Name, null),
                sharedPreferences.getString(Gender, null),
                sharedPreferences.getString(dob, null),
                sharedPreferences.getString(mobileno, null),
                sharedPreferences.getString(email, null),
                sharedPreferences.getString(BloodGroup, null),
                sharedPreferences.getString(StreetAddress, null),
                sharedPreferences.getString(Locatity, null),
                sharedPreferences.getString(City, null),
                sharedPreferences.getString(PinCode, null),
                sharedPreferences.getString(Images, null),
                sharedPreferences.getString(CreateDate, null),
                sharedPreferences.getString(IsActive, null),
                sharedPreferences.getString(IsDeleted, null),
                sharedPreferences.getString(Password, null)
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
