package com.zerone.hospitalmanagement.Model;

public class DoctorModel {
    private int id;
    private String doctorName, doctorEducation, doctorProfession, doctorCategory,
            doctorAddress, doctorMobile, doctorEmail, doctorPassword;

    public DoctorModel(int id, String doctorName, String doctorEducation, String doctorProfession, String doctorCategory, String doctorAddress, String doctorMobile, String doctorEmail, String doctorPassword) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorEducation = doctorEducation;
        this.doctorProfession = doctorProfession;
        this.doctorCategory = doctorCategory;
        this.doctorAddress = doctorAddress;
        this.doctorMobile = doctorMobile;
        this.doctorEmail = doctorEmail;
        this.doctorPassword = doctorPassword;
    }

    public DoctorModel(String doctorName, String doctorEducation, String doctorProfession, String doctorCategory, String doctorAddress, String doctorMobile, String doctorEmail, String doctorPassword) {
        this.doctorName = doctorName;
        this.doctorEducation = doctorEducation;
        this.doctorProfession = doctorProfession;
        this.doctorCategory = doctorCategory;
        this.doctorAddress = doctorAddress;
        this.doctorMobile = doctorMobile;
        this.doctorEmail = doctorEmail;
        this.doctorPassword = doctorPassword;
    }

    public int getId() {
        return id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorEducation() {
        return doctorEducation;
    }

    public String getDoctorProfession() {
        return doctorProfession;
    }

    public String getDoctorCategory() {
        return doctorCategory;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public String getDoctorMobile() {
        return doctorMobile;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }
}
