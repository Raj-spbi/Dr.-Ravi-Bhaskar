package com.clinicapp.drravibhaskar.apimodels;

public class ModelUser {

    public String id;

    public String PatientID;
    public String name;
    public String gender;
    public String DOB;
    public String age;
    public String Address1;
    public String contactNo;
    public String Images;
    public String Email;
    public String Password;

    public ModelUser(String id, String patientID, String name, String gender, String DOB, String age, String address1, String contactNo, String images, String email, String password) {
        this.id = id;
        PatientID = patientID;
        this.name = name;
        this.gender = gender;
        this.DOB = DOB;
        this.age = age;
        Address1 = address1;
        this.contactNo = contactNo;
        Images = images;
        Email = email;
        Password = password;
    }

    public String getId() {
        return id;
    }

    public String getPatientID() {
        return PatientID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDOB() {
        return DOB;
    }

    public String getAge() {
        return age;
    }

    public String getAddress1() {
        return Address1;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getImages() {
        return Images;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
}
