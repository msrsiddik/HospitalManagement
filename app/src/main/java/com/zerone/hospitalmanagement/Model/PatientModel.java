package com.zerone.hospitalmanagement.Model;

public class PatientModel {
    private int id;
    private String name, gender, age, address, doctor;

    public PatientModel(int id, String name, String gender, String age, String address, String doctor) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.doctor = doctor;
    }

    public PatientModel(String name, String gender, String age, String address, String doctor) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getDoctor() {
        return doctor;
    }
}
