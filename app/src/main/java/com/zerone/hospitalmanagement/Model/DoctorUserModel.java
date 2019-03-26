package com.zerone.hospitalmanagement.Model;

public class DoctorUserModel {
    private int id;
    private String email, password;

    public DoctorUserModel(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
