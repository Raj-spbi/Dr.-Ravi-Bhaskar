package com.clinicapp.drravibhaskar.models;

import java.util.ArrayList;
import java.util.List;

public class ModelForAppointHistory {

    public class ResultRow
    {

        //@JsonProperty("BookingID")
        public String BookingID;
        //@JsonProperty("TimeSlot")
        public String TimeSlot;
        //@JsonProperty("BookingDate")
        public String BookingDate;
//        @JsonProperty("PatientName")
//        public String patientName;
//        @JsonProperty("Email")
//        public String email;
//        @JsonProperty("MobileNumber")
//        public String mobileNumber;
//        @JsonProperty("Gender")
//        public String gender;
//        @JsonProperty("Age")
//        public String age;
//        @JsonProperty("City")
//        public String city;
//        @JsonProperty("Occupation")
//        public String occupation;
//        @JsonProperty("Uploadfile")
//        public object uploadfile;
//        @JsonProperty("Discription")
//        public String discription;
//        @JsonProperty("TransactionId")
//        public object transactionId;
//        @JsonProperty("Address")
//        public String address;
//        @JsonProperty("Payment_Date")
//        public object payment_Date;
//        @JsonProperty("PayAmount")
//        public object payAmount;
//        @JsonProperty("RefId")
//        public object refId;
//        @JsonProperty("SlotId")
//        public int slotId;
//        @JsonProperty("Status")
//        public String status;
//        @JsonProperty("IsActive")
//        public int isActive;
//        @JsonProperty("PatientID")
//        public String patientID;


        public ResultRow(String bookingID, String timeSlot, String bookingDate) {
            BookingID = bookingID;
            TimeSlot = timeSlot;
            BookingDate = bookingDate;
        }

        public String getBookingID() {
            return BookingID;
        }

        public String getTimeSlot() {
            return TimeSlot;
        }

        public String getBookingDate() {
            return BookingDate;
        }
    }

    public class Example
    {
        //@JsonProperty("Status")
        public int Status;
        //@JsonProperty("Error")
        public String Error;
        //@JsonProperty("ResultRows")
        public ArrayList<ResultRow> ResultRows;
    }
}
