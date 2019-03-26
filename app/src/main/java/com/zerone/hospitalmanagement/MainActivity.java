package com.zerone.hospitalmanagement;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zerone.hospitalmanagement.Database.DoctorDataSource;
import com.zerone.hospitalmanagement.Model.DoctorModel;

public class MainActivity extends AppCompatActivity implements FragmentController{

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragmentContainer,new HomeFragment()).commit();

        DoctorDataSource doctorDataSource = new DoctorDataSource(this);
        String[] name = getResources().getStringArray(R.array.doctor_name);
        String[] edu = getResources().getStringArray(R.array.doctor_edu);
        String[] prof = getResources().getStringArray(R.array.doctor_prof);
        String[] cate = getResources().getStringArray(R.array.doctor_category);
        String[] addr = getResources().getStringArray(R.array.doctor_address);
        String[] email = getResources().getStringArray(R.array.doctor_email);
        String[] pass = getResources().getStringArray(R.array.doctor_password);
        for (int i = 0; i < name.length ; i++) {
            doctorDataSource.insertNewDoctor(new DoctorModel(name[i],edu[i],prof[i],cate[i],addr[i],null,email[i],pass[i]));
        }

    }

    @Override
    public void gotoLoginPage() {
        manager = getSupportFragmentManager();
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,new LoginFragment()).commit();
    }

    @Override
    public void gotoAdminPanel() {
        manager = getSupportFragmentManager();
        manager.popBackStack();
        manager.beginTransaction().replace(R.id.fragmentContainer,new AdminPanelFragment()).commit();

    }

    @Override
    public void gotoAddDoctorForm() {
        manager = getSupportFragmentManager();
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,new AddDoctorFormFragment()).commit();
    }

    @Override
    public void gotoDoctorList() {
        manager = getSupportFragmentManager();
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,new DoctorListFragment()).commit();
    }

    @Override
    public void gotoAppointment() {
        manager = getSupportFragmentManager();
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,new AppoinmentFragment()).commit();
    }

    @Override
    public void gotoPatientList(String name) {
        manager = getSupportFragmentManager();
        PatientListFragment patientListFragment = new PatientListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("loginDoctor",name);
        patientListFragment.setArguments(bundle);
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,patientListFragment).commit();
    }

}
