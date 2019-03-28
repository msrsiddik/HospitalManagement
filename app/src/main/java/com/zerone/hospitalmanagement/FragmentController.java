package com.zerone.hospitalmanagement;

public interface FragmentController {
    void gotoHomeFragment();
    void gotoLoginPage();
    void gotoAdminPanel();
    void gotoAddDoctorForm();
    void gotoDoctorList();
    void gotoAppointment();
    void gotoPatientList(String name);
    void gotoDoctorEditForm(int id);
    void dialPhoneNumber(String mobileNumber);
}
