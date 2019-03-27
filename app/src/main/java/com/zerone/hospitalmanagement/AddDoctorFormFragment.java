package com.zerone.hospitalmanagement;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.zerone.hospitalmanagement.Database.DoctorDataSource;
import com.zerone.hospitalmanagement.Model.DoctorModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddDoctorFormFragment extends Fragment {
    private TextInputLayout doc_NameInput, doc_eduDegreeInput, doc_professionInput, doc_categoryInput,
            doc_addressInput, doc_mobileInput, doc_emailInput, doc_passwordInput;
    private Button doc_confirmBtn, doc_updateBtn;

    private FragmentController controller;
    private DoctorDataSource dataSource;
    private int doctorID;

    private Context context;

    public AddDoctorFormFragment() {
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
        return inflater.inflate(R.layout.fragment_add_doctor_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doc_NameInput = view.findViewById(R.id.doc_NameInput);
        doc_eduDegreeInput = view.findViewById(R.id.doc_eduDegreeInput);
        doc_professionInput = view.findViewById(R.id.doc_professionInput);
        doc_categoryInput = view.findViewById(R.id.doc_categoryInput);
        doc_addressInput = view.findViewById(R.id.doc_addressInput);
        doc_mobileInput = view.findViewById(R.id.doc_mobileInput);
        doc_emailInput = view.findViewById(R.id.doc_emailInput);
        doc_passwordInput = view.findViewById(R.id.doc_passwordInput);
        doc_confirmBtn = view.findViewById(R.id.doc_confirmBtn);
        doc_updateBtn = view.findViewById(R.id.doc_updateBtn);

        dataSource = new DoctorDataSource(getContext());
        controller = (FragmentController) getActivity();

        doc_confirmBtn.setOnClickListener(confirmBtnListener);

        doc_updateBtn.setOnClickListener(updateBtnListener);

        editDoctorInfo();

    }

    View.OnClickListener confirmBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = doc_NameInput.getEditText().getText().toString();
            String edu = doc_eduDegreeInput.getEditText().getText().toString();
            String pro = doc_professionInput.getEditText().getText().toString();
            String cate = doc_categoryInput.getEditText().getText().toString();
            String address = doc_addressInput.getEditText().getText().toString();
            String mobile = doc_mobileInput.getEditText().getText().toString();
            String email = doc_emailInput.getEditText().getText().toString();
            String pass = doc_passwordInput.getEditText().getText().toString();

            final DoctorModel doctorModel = new DoctorModel(name,edu,pro,cate,address,mobile,email,pass);

            long inserRow = dataSource.insertNewDoctor(doctorModel);

            if (inserRow == -1){
                Toast.makeText(getActivity(), "Doctor Save failed!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getActivity(), "Doctor Saved", Toast.LENGTH_SHORT).show();
                controller.gotoAdminPanel();
            }
        }
    };

    private void editDoctorInfo() {
        try {
            doctorID = Integer.parseInt(getArguments().getString("doctorId"));
            final DoctorModel doctorModel = dataSource.getDoctorInfoById(doctorID);
            doc_NameInput.getEditText().setText(doctorModel.getDoctorName());
            doc_eduDegreeInput.getEditText().setText(doctorModel.getDoctorEducation());
            doc_professionInput.getEditText().setText(doctorModel.getDoctorProfession());
            doc_categoryInput.getEditText().setText(doctorModel.getDoctorCategory());
            doc_addressInput.getEditText().setText(doctorModel.getDoctorAddress());
            doc_mobileInput.getEditText().setText(doctorModel.getDoctorMobile());
            doc_emailInput.getEditText().setText(doctorModel.getDoctorEmail());
            doc_passwordInput.getEditText().setText(doctorModel.getDoctorPassword());

            doc_confirmBtn.setVisibility(View.INVISIBLE);
            doc_updateBtn.setVisibility(View.VISIBLE);

        }catch (Exception e){

        }
    }

    View.OnClickListener updateBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = doc_NameInput.getEditText().getText().toString();
            String edu = doc_eduDegreeInput.getEditText().getText().toString();
            String pro = doc_professionInput.getEditText().getText().toString();
            String cate = doc_categoryInput.getEditText().getText().toString();
            String address = doc_addressInput.getEditText().getText().toString();
            String mobile = doc_mobileInput.getEditText().getText().toString();
            String email = doc_emailInput.getEditText().getText().toString();
            String pass = doc_passwordInput.getEditText().getText().toString();

            final DoctorModel doctorModel = new DoctorModel(doctorID,name,edu,pro,cate,address,mobile,email,pass);
            int updateRow = dataSource.updateDoctorInfo(doctorModel);
            if (updateRow >0){
                Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show();
                controller.gotoDoctorList();
            }
        }
    };
}
