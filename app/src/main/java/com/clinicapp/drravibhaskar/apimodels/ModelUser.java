package com.clinicapp.drravibhaskar.apimodels;

public class ModelUser {

    public static class User
    {
        public String user_id;
        public String ashram_id;
        public String user_name;
        public String user_email;
        public String user_mobile;
        public String user_type;
        public String status;
        public String user_photo;
        public String token;

        public User() {
        }

        public User(String user_id, String ashram_id, String user_name, String user_email, String user_mobile, String user_type, String status, String user_photo, String token) {
            this.user_id = user_id;
            this.ashram_id = ashram_id;
            this.user_name = user_name;
            this.user_email = user_email;
            this.user_mobile = user_mobile;
            this.user_type = user_type;
            this.status = status;
            this.user_photo = user_photo;
            this.token = token;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAshram_id() {
            return ashram_id;
        }

        public void setAshram_id(String ashram_id) {
            this.ashram_id = ashram_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


    public class Example
    {
        public boolean error;
        public String message;
        public User user;
    }

}
