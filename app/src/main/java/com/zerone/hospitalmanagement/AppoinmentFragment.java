package com.zerone.hospitalmanagement;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.zerone.hospitalmanagement.Database.DoctorDataSource;
import com.zerone.hospitalmanagement.Database.PatientDataSource;
import com.zerone.hospitalmanagement.Model.PatientModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppoinmentFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Toolbar toolbar;
    private TextInputLayout patient_NameInput, patient_AgeInput, patient_AddressInput;
    private RadioGroup genderGroup;
    private String gender = "";
    private Spinner chooseCategory, chooseDoctor;
    private Button patient_confirmBtn;

    private List<String> doctorCategory=new ArrayList<>();
    private String[] doctorName=null;
    private DoctorDataSource doctorDataSource;
    private String category = null;
    private String doctor = null;
    private PatientDataSource patientDataSource;

    private FragmentController fragmentController;

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
        genderGroup = view.findViewById(R.id.genderGroup);
        patient_AgeInput = view.findViewById(R.id.patient_AgeInput);
        patient_AddressInput = view.findViewById(R.id.patient_AddressInput);
        chooseCategory = view.findViewById(R.id.chooseCategory);
        chooseDoctor = view.findViewById(R.id.chooseDoctor);
        patient_confirmBtn = view.findViewById(R.id.patient_confirmBtn);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Appointment");
        toolbar.setTitleTextColor(Color.WHITE);

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.maleGender:
                        gender = "Male";
                        break;
                    case R.id.femaleGender:
                        gender = "Female";
                        break;
                }
            }
        });

        spinnerItemSet();

        patient_confirmBtn.setOnClickListener(confirmBtnListener);

        fragmentController = (FragmentController) getActivity();

    }

    View.OnClickListener confirmBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (category.equals("Category")){
                Toast.makeText(getContext(), "Select Category and try again", Toast.LENGTH_LONG).show();
            }else {
                String name = patient_NameInput.getEditText().getText().toString();
                String age = patient_AgeInput.getEditText().getText().toString();
                String address = patient_AddressInput.getEditText().getText().toString();

                PatientModel patientModel = new PatientModel(name, gender, age, address, doctor);
                patientDataSource = new PatientDataSource(getContext());
                patientDataSource.insertNewPatient(patientModel);

                Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();

                fragmentController.gotoHomeFragment();
            }
        }
    };

    void spinnerItemSet(){
        doctorDataSource = new DoctorDataSource(getContext());
        doctorCategory.add(0,"Category");
        for (int i = 1; i <= doctorDataSource.getCategory().size(); i++) {
            doctorCategory.add(doctorDataSource.getAlldoctorInfoCollectList().get(i).getDoctorCategory());
        }

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,doctorCategory);

        chooseCategory.setAdapter(adapterCategory);

        chooseCategory.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getItemAtPosition(position).toString().equals("Category")){
            category = parent.getItemAtPosition(position).toString();
            chooseDoctor.setVisibility(View.INVISIBLE);
        }else {
            chooseDoctor.setVisibility(View.VISIBLE);
            category = parent.getItemAtPosition(position).toString();

            doctorName = new String[doctorDataSource.getDoctorNameByCategory(category).size()];
            for (int i = 0; i < doctorName.length; i++) {
                doctorName[i] = doctorDataSource.getDoctorNameByCategory(category).get(i).getDoctorName();
            }
            ArrayAdapter<String> adapterDoctor = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, doctorName);
            chooseDoctor.setAdapter(adapterDoctor);

            doctor = chooseDoctor.getSelectedItem().toString();
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
