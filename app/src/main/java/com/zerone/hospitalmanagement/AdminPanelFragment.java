package com.zerone.hospitalmanagement;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminPanelFragment extends Fragment {
    private CardView addDoctorBtn, modifyDoctorBtn, patientListBtn;
    private FragmentController controller;

    public AdminPanelFragment() {
        // Required empty public constructor
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

        addDoctorBtn.setOnClickListener(addDoctorListener);
        modifyDoctorBtn.setOnClickListener(modifyDoctorListener);

    }

    View.OnClickListener addDoctorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller = (FragmentController) getActivity();
            controller.gotoAddDoctorForm();
        }
    };

    View.OnClickListener modifyDoctorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller = (FragmentController) getActivity();
            controller.gotoDoctorList();
        }
    };
}
