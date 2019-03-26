package com.zerone.hospitalmanagement;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerone.hospitalmanagement.Database.DoctorDataSource;
import com.zerone.hospitalmanagement.Model.DoctorModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorListFragment extends Fragment {
    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    private DoctorDataSource doctorDataSource;
    private List<DoctorModel> doctorModelList = new ArrayList<>();

    public DoctorListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.doctorListView);

        doctorDataSource = new DoctorDataSource(getActivity());
        doctorModelList = doctorDataSource.getAlldoctorInfoCollectList();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        doctorAdapter = new DoctorAdapter(doctorModelList);
        recyclerView.setAdapter(doctorAdapter);

    }
}
