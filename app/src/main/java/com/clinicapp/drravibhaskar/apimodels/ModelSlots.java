package com.clinicapp.drravibhaskar.apimodels;

public class ModelSlots {
    String SlotId;
    String Date;
    String Slot;
    String Timing;
    String status;
    String IsActive;

    public ModelSlots(String slotId, String date, String slot, String timing, String status, String isActive) {
        SlotId = slotId;
        Date = date;
        Slot = slot;
        Timing = timing;
        this.status = status;
        IsActive = isActive;
    }

    public String getSlotId() {
        return SlotId;
    }

    public String getDate() {
        return Date;
    }

    public String getSlot() {
        return Slot;
    }

    public String getTiming() {
        return Timing;
    }

    public String getStatus() {
        return status;
    }

    public String getIsActive() {
        return IsActive;
    }
}
