package com.zerone.hospitalmanagement;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private CardView doctorListBtn, appointmentBtn, adminBtn, doctorBtn;
    private FragmentController controller;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doctorListBtn = view.findViewById(R.id.doctorListBtn);
        appointmentBtn = view.findViewById(R.id.appointmentBtn);
        adminBtn = view.findViewById(R.id.adminBtn);
        doctorBtn = view.findViewById(R.id.doctorBtn);

        doctorListBtn.setOnClickListener(doctorListListener);
        appointmentBtn.setOnClickListener(appointmentListener);
        adminBtn.setOnClickListener(adminListener);
        doctorBtn.setOnClickListener(doctorListener);
    }

    View.OnClickListener doctorListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller = (FragmentController) getActivity();
            controller.gotoDoctorList();
        }
    };

    View.OnClickListener appointmentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Appointment", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener adminListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller = (FragmentController) getActivity();
            controller.gotoLoginPage();
        }
    };

    View.OnClickListener doctorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller = (FragmentController) getActivity();
            controller.gotoLoginPage();
        }
    };
}
