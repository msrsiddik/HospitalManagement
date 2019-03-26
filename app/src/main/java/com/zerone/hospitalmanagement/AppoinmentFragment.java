package com.zerone.hospitalmanagement;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.zerone.hospitalmanagement.Database.DoctorDataSource;
import com.zerone.hospitalmanagement.Database.PatientDataSource;
import com.zerone.hospitalmanagement.Model.DoctorModel;
import com.zerone.hospitalmanagement.Model.PatientModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppoinmentFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private TextInputLayout patient_NameInput, patient_GenderInput, patient_AgeInput, patient_AddressInput;
    private Spinner chooseDoctor;
    private Button patient_confirmBtn;

    private String[] doctorName;
    private DoctorDataSource doctorDataSource;
    private String doctor = "";
    private PatientDataSource patientDataSource;

    public AppoinmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appoinment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        patient_NameInput = view.findViewById(R.id.patient_NameInput);
        patient_GenderInput = view.findViewById(R.id.patient_GenderInput);
        patient_AgeInput = view.findViewById(R.id.patient_AgeInput);
        patient_AddressInput = view.findViewById(R.id.patient_AddressInput);
        chooseDoctor = view.findViewById(R.id.chooseDoctor);
        patient_confirmBtn = view.findViewById(R.id.patient_confirmBtn);

        spinnerItemSet();

        patient_confirmBtn.setOnClickListener(confirmBtnListener);

    }

    View.OnClickListener confirmBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = patient_NameInput.getEditText().getText().toString();
            String gender = patient_GenderInput.getEditText().getText().toString();
            String age = patient_AgeInput.getEditText().getText().toString();
            String address = patient_AddressInput.getEditText().getText().toString();

            PatientModel patientModel = new PatientModel(name,gender,age,address,doctor);
            patientDataSource = new PatientDataSource(getContext());
            patientDataSource.insertNewPatient(patientModel);

            Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
        }
    };

    void spinnerItemSet(){
        doctorDataSource = new DoctorDataSource(getContext());
        doctorName = new String[doctorDataSource.getAlldoctorInfoCollectList().size()];
        for (int i = 0; i < doctorDataSource.getAlldoctorInfoCollectList().size(); i++) {
            doctorName[i] = doctorDataSource.getAlldoctorInfoCollectList().get(i).getDoctorName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,doctorName);
        chooseDoctor.setAdapter(adapter);

        chooseDoctor.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        doctor = doctorName[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
