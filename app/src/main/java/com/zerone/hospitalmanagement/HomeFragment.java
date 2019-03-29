package com.zerone.hospitalmanagement;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zerone.hospitalmanagement.Database.DoctorDataSource;
import com.zerone.hospitalmanagement.Model.DoctorModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private AutoCompleteTextView searchDoctor;
    private ImageButton searchTextClearBtn;
    private Dialog dialog;
    private CardView doctorListBtn, appointmentBtn, adminBtn, doctorBtn;
    private Context context;
    private FragmentController controller;
    private UserPreference userPreference;
    private String[] doctorName;
    private DoctorDataSource doctorDataSource;

    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchDoctor = view.findViewById(R.id.searchDoctor);
        searchTextClearBtn = view.findViewById(R.id.searchTextClearBtn);
        doctorListBtn = view.findViewById(R.id.doctorListBtn);
        appointmentBtn = view.findViewById(R.id.appointmentBtn);
        adminBtn = view.findViewById(R.id.adminBtn);
        doctorBtn = view.findViewById(R.id.doctorBtn);

        dialog = new Dialog(context);
        doctorSearch();

        controller = (FragmentController) getActivity();

        doctorListBtn.setOnClickListener(doctorListListener);
        appointmentBtn.setOnClickListener(appointmentListener);
        adminBtn.setOnClickListener(adminListener);
        doctorBtn.setOnClickListener(doctorListener);

        userPreference = new UserPreference(getContext());
    }

    private void doctorSearch() {
        doctorDataSource = new DoctorDataSource(context);
        doctorName = new String[doctorDataSource.getAlldoctorInfoCollectList().size()];
        for (int i = 0; i < doctorDataSource.getAlldoctorInfoCollectList().size(); i++) {
            doctorName[i] = doctorDataSource.getAlldoctorInfoCollectList().get(i).getDoctorName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, doctorName);
        searchDoctor.setAdapter(adapter);
        searchTextClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDoctor.getText().clear();
            }
        });
        dialog.setContentView(R.layout.doctor_view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView doctorNameTV, doctorEduTV, doctorProTV, doctorCateTV, doctorAddTV;
        final ImageButton doctorCallBtn, doctorEmailBtn, closeDoctorView;
        doctorNameTV = dialog.findViewById(R.id.doctorNameTV);
        doctorEduTV = dialog.findViewById(R.id.doctorEduTV);
        doctorProTV = dialog.findViewById(R.id.doctorProTV);
        doctorCateTV = dialog.findViewById(R.id.doctorCateTV);
        doctorAddTV = dialog.findViewById(R.id.doctorAddTV);
        doctorCallBtn = dialog.findViewById(R.id.doctorCallBtn);
        doctorEmailBtn = dialog.findViewById(R.id.doctorEmailBtn);
        closeDoctorView = dialog.findViewById(R.id.closeDoctorView);
        searchDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final DoctorModel doctorModel = doctorDataSource.getDoctorInfoByName(doctorName[position]);

                doctorNameTV.setText(doctorModel.getDoctorName());
                doctorEduTV.setText(doctorModel.getDoctorEducation());
                doctorProTV.setText(doctorModel.getDoctorProfession());
                doctorCateTV.setText(doctorModel.getDoctorCategory());
                doctorAddTV.setText(doctorModel.getDoctorAddress());
                closeDoctorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                doctorCallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        controller.dialPhoneNumber(doctorModel.getDoctorMobile());
                    }
                });
                doctorEmailBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"+doctorModel.getDoctorEmail()));
                        if (intent.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(intent);
                        }
                    }
                });
                dialog.show();
//                Toast.makeText(context, doctorModel.getDoctorMobile()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnClickListener doctorListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller.gotoDoctorList();
        }
    };

    View.OnClickListener appointmentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller.gotoAppointment();
        }
    };

    View.OnClickListener adminListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (userPreference.getLoginStatus()){
                controller.gotoAdminPanel();
            }else {
                controller.gotoLoginPage();
            }
        }
    };

    View.OnClickListener doctorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller.gotoLoginPage();
        }
    };
}
