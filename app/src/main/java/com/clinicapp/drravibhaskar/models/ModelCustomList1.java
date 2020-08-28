package com.clinicapp.drravibhaskar.models;

public class ModelCustomList1 {
    private String title;
    private  int image;

    public ModelCustomList1() {
    }

    public ModelCustomList1(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
