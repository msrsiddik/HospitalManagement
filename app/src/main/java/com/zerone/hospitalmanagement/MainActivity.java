package com.zerone.hospitalmanagement;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.zerone.hospitalmanagement.Database.DoctorDataSource;
import com.zerone.hospitalmanagement.Model.DoctorModel;

public class MainActivity extends AppCompatActivity implements FragmentController{

    FragmentManager manager;
    private UserPreference userPreference;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreference = new UserPreference(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragmentContainer,new HomeFragment()).commit();

        autoAddDoctor();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.logout);
        if (userPreference.getLoginStatus()){
            item.setVisible(true);
        }
        else {
            item.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout){
            userPreference.setLoginStatus(false);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void gotoHomeFragment(){
        manager.beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
    }

    @Override
    public void gotoLoginPage() {
        manager.beginTransaction().addToBackStack("login").replace(R.id.fragmentContainer,new LoginFragment()).commit();
    }

    @Override
    public void gotoAdminPanel() {
        manager.beginTransaction().remove(new HomeFragment());
        manager.popBackStack("login",FragmentManager.POP_BACK_STACK_INCLUSIVE);
        manager.beginTransaction().replace(R.id.fragmentContainer,new AdminPanelFragment()).commit();
    }

    @Override
    public void gotoAddDoctorForm() {
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,new AddDoctorFormFragment()).commit();
    }

    @Override
    public void gotoDoctorList() {
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,new DoctorListFragment()).commit();
    }

    @Override
    public void gotoAppointment() {
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,new AppoinmentFragment()).commit();
    }

    @Override
    public void gotoPatientList(String name) {
        PatientListFragment patientListFragment = new PatientListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("loginDoctor",name);
        patientListFragment.setArguments(bundle);
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,patientListFragment).commit();
    }

    @Override
    public void gotoDoctorEditForm(int id) {
        AddDoctorFormFragment doctorFormFragment = new AddDoctorFormFragment();
        Bundle bundle = new Bundle();
        bundle.putString("doctorId", String.valueOf(id));
        doctorFormFragment.setArguments(bundle);
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, doctorFormFragment).commit();
    }

    private void autoAddDoctor() {
        if (userPreference.getDataFirstTimeEntry()){
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
            userPreference.setDataFirstTimeEntry(false);
        }
    }


}
