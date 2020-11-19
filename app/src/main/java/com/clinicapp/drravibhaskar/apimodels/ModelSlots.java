package com.clinicapp.drravibhaskar.apimodels;

import java.util.ArrayList;
import java.util.List;

public class ModelSlots {
    public class ResultRow
    {
        //@JsonProperty("SlotId")
        public int SlotId;
        //@JsonProperty("Date")
        public String Date;
        //@JsonProperty("Slot")
        public String Slot;
        //@JsonProperty("Timing")
        public String Timing;
        public String status;
        //@JsonProperty("IsActive")
        public int IsActive;

        public ResultRow(int slotId, String date, String slot, String timing, String status, int isActive) {
            SlotId = slotId;
            Date = date;
            Slot = slot;
            Timing = timing;
            this.status = status;
            IsActive = isActive;
        }

        public int getSlotId() {
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

        public int getIsActive() {
            return IsActive;
        }
    }

    public class Example
    {
        //@JsonProperty("Status")
        public boolean Status;
        //@JsonProperty("Error")
        public String Error;
        //@JsonProperty("ResultRows")
        public List<ResultRow> ResultRows;
    }
}
