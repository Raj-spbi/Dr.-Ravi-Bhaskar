package com.clinicapp.drravibhaskar.models;

public class ModelForNoti {
    int ImageRes;
    String noti;

    public ModelForNoti(int imageRes, String noti) {
        ImageRes = imageRes;
        this.noti = noti;
    }

    public int getImageRes() {
        return ImageRes;
    }

    public String getNoti() {
        return noti;
    }
}
