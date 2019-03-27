package com.zerone.hospitalmanagement;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminPanelFragment extends Fragment {
    private CardView addDoctorBtn, modifyDoctorBtn, patientListBtn;
    private Toolbar toolbar;
    private UserPreference userPreference;
    private Context context;

    private FragmentController controller;

    public AdminPanelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_panel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addDoctorBtn = view.findViewById(R.id.addDoctorBtn);
        modifyDoctorBtn = view.findViewById(R.id.modifyDoctorBtn);
        patientListBtn = view.findViewById(R.id.patientListBtn);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        userPreference = new UserPreference(context);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.logout){
                    userPreference.setLoginStatus(false);
                    controller.gotoHomeFragment();
                }
                return true;
            }
        });

        controller = (FragmentController) getActivity();

        addDoctorBtn.setOnClickListener(addDoctorListener);
        modifyDoctorBtn.setOnClickListener(modifyDoctorListener);
        patientListBtn.setOnClickListener(patientListListener);
    }

    View.OnClickListener addDoctorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller.gotoAddDoctorForm();
        }
    };

    View.OnClickListener modifyDoctorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller.gotoDoctorList();
        }
    };

    View.OnClickListener patientListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller.gotoPatientList("admin");
        }
    };
}
