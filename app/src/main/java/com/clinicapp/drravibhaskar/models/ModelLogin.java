package com.clinicapp.drravibhaskar.models;

import java.util.List;

public class ModelLogin {

    public static class ResultRow {

        public String Id;

        public String PatientId;

        public String Name;

        public String Gender;
        public String dob;
        public String mobileno;
        public String email;

        public String BloodGroup;

        public String StreetAddress;

        public String Locatity;

        public String City;

        public String PinCode;

        public String Images;

        public String CreateDate;

        public String IsActive;
        public String IsDeleted;

        public String Password;

        public ResultRow(String id, String patientId, String name, String gender, String dob, String mobileno, String email, String bloodGroup, String streetAddress, String locatity, String city, String pinCode, String images, String createDate, String isActive, String isDeleted, String password) {
            Id = id;
            PatientId = patientId;
            Name = name;
            Gender = gender;
            this.dob = dob;
            this.mobileno = mobileno;
            this.email = email;
            BloodGroup = bloodGroup;
            StreetAddress = streetAddress;
            Locatity = locatity;
            City = city;
            PinCode = pinCode;
            Images = images;
            CreateDate = createDate;
            IsActive = isActive;
            IsDeleted = isDeleted;
            Password = password;
        }

        public String getId() {
            return Id;
        }

        public String getPatientId() {
            return PatientId;
        }

        public String getName() {
            return Name;
        }

        public String getGender() {
            return Gender;
        }

        public String getDob() {
            return dob;
        }

        public String getMobileno() {
            return mobileno;
        }

        public String getEmail() {
            return email;
        }

        public String getBloodGroup() {
            return BloodGroup;
        }

        public String getStreetAddress() {
            return StreetAddress;
        }

        public String getLocatity() {
            return Locatity;
        }

        public String getCity() {
            return City;
        }

        public String getPinCode() {
            return PinCode;
        }

        public String getImages() {
            return Images;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public String getIsActive() {
            return IsActive;
        }

        public String getIsDeleted() {
            return IsDeleted;
        }

        public String getPassword() {
            return Password;
        }
    }

    public class Example {

        public boolean Status;

        public String Error;
        public List<ResultRow> ResultRows;
    }

}
