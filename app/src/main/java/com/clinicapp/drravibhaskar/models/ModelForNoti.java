package com.clinicapp.drravibhaskar.models;

import java.util.List;

public class ModelForNoti {

    public class ResultRow
    {
        //@JsonProperty("Fdid")
        public int Fdid;
        //@JsonProperty("Name")
        public String Name;
        //@JsonProperty("Email")
        public String Email;
        //@JsonProperty("Phone")
        public String Phone;
        //@JsonProperty("Msg")
        public String Msg;
        //@JsonProperty("MsgStatus")
        public boolean MsgStatus;
        //@JsonProperty("UploadDt")
        public String UploadDt;


        public ResultRow(int fdid, String name, String email, String phone, String msg, boolean msgStatus, String uploadDt) {
            Fdid = fdid;
            Name = name;
            Email = email;
            Phone = phone;
            Msg = msg;
            MsgStatus = msgStatus;
            UploadDt = uploadDt;
        }

        public int getFdid() {
            return Fdid;
        }

        public String getName() {
            return Name;
        }

        public String getEmail() {
            return Email;
        }

        public String getPhone() {
            return Phone;
        }

        public String getMsg() {
            return Msg;
        }

        public boolean isMsgStatus() {
            return MsgStatus;
        }

        public String getUploadDt() {
            return UploadDt;
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
