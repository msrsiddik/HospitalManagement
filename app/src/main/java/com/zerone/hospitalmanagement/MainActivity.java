package com.zerone.hospitalmanagement;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentController{

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragmentContainer,new HomeFragment()).commit();
    }

    @Override
    public void gotoLoginPage() {
        manager = getSupportFragmentManager();
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,new LoginFragment()).commit();
    }

    @Override
    public void gotoAdminPanel() {
        manager = getSupportFragmentManager();
        manager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,new AdminPanelFragment()).commit();
    }
}
