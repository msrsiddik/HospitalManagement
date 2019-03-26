package com.zerone.hospitalmanagement;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zerone.hospitalmanagement.Model.DoctorModel;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {
    private List<DoctorModel> doctorModelList;

    public DoctorAdapter(List<DoctorModel> doctorModelList) {
        this.doctorModelList = doctorModelList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doctor_row,viewGroup,false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder doctorViewHolder, int i) {
        final DoctorModel doctorModel = doctorModelList.get(i);

        doctorViewHolder.doctorNameTV.setText(doctorModel.getDoctorName());
        doctorViewHolder.doctorEduTV.setText(doctorModel.getDoctorEducation());
        doctorViewHolder.doctorProTV.setText(doctorModel.getDoctorProfession());
        doctorViewHolder.doctorCateTV.setText(doctorModel.getDoctorCategory());
        doctorViewHolder.doctorAddTV.setText(doctorModel.getDoctorAddress());
    }

    @Override
    public int getItemCount() {
        return doctorModelList.size();
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView doctorNameTV, doctorEduTV, doctorProTV, doctorCateTV, doctorAddTV;
        Button doctorCallBtn, doctorEmailBtn;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorNameTV = itemView.findViewById(R.id.doctorNameTV);
            doctorEduTV = itemView.findViewById(R.id.doctorEduTV);
            doctorProTV = itemView.findViewById(R.id.doctorProTV);
            doctorCateTV = itemView.findViewById(R.id.doctorCateTV);
            doctorAddTV = itemView.findViewById(R.id.doctorAddTV);
            doctorCallBtn = itemView.findViewById(R.id.doctorCallBtn);
            doctorEmailBtn = itemView.findViewById(R.id.doctorEmailBtn);
        }
    }
}
