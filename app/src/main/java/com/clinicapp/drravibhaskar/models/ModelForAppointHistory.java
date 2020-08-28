package com.clinicapp.drravibhaskar.models;

public class ModelForAppointHistory {
    String date;
    String slot;
    String appoint_id;

    public ModelForAppointHistory(String date, String slot, String appoint_id) {
        this.date = date;
        this.slot = slot;
        this.appoint_id = appoint_id;
    }

    public String getDate() {
        return date;
    }

    public String getSlot() {
        return slot;
    }

    public String getAppoint_id() {
        return appoint_id;
    }
}
