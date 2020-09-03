package com.clinicapp.drravibhaskar.models;

public class ModelGrid {
    int ImageRes;
    String name;

    public ModelGrid(int imageRes, String name) {
        ImageRes = imageRes;
        this.name = name;
    }

    public int getImageRes() {
        return ImageRes;
    }

    public String getName() {
        return name;
    }
}
