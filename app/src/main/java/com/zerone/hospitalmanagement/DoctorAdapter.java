package com.zerone.hospitalmanagement;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zerone.hospitalmanagement.Database.DoctorDataSource;
import com.zerone.hospitalmanagement.Model.DoctorModel;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {
    private List<DoctorModel> doctorModelList;
    private Context context;
    private DoctorDataSource doctorDataSource;
    private UserPreference userPreference;
    private FragmentController controller;
    private Dialog dialog;

    public DoctorAdapter(List<DoctorModel> doctorModelList, Context context) {
        this.doctorModelList = doctorModelList;
        this.context = context;
        doctorDataSource = new DoctorDataSource(context);
        userPreference = new UserPreference(context);
        controller = (FragmentController) context;
        dialog = new Dialog(context);
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
        final int id = i;

        doctorViewHolder.doctorNameTV.setText(doctorModel.getDoctorName());
        doctorViewHolder.doctorEduTV.setText(doctorModel.getDoctorEducation());
        doctorViewHolder.doctorProTV.setText(doctorModel.getDoctorProfession());
        doctorViewHolder.doctorCateTV.setText(doctorModel.getDoctorCategory());
        doctorViewHolder.doctorAddTV.setText(doctorModel.getDoctorAddress());
        doctorViewHolder.doctorCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.dialPhoneNumber(doctorModel.getDoctorMobile());
            }
        });

        doctorViewHolder.doctorEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+doctorModel.getDoctorEmail()));
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            }
        });

        if (userPreference.getLoginStatus()) {
            doctorViewHolder.moreOptionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.setContentView(R.layout.dialog_confirm);
                    final TextView no = dialog.findViewById(R.id.dialog_no);
                    final TextView yes = dialog.findViewById(R.id.dialog_yes);
                    PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                    MenuInflater inflater = popupMenu.getMenuInflater();
                    inflater.inflate(R.menu.doctor_option_menu, popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if (menuItem.getItemId() == R.id.edit_item) {
                                int doctorId = doctorModel.getId();
                                controller.gotoDoctorEditForm(doctorId);
                            } else if (menuItem.getItemId() == R.id.delete_item) {

                                no.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int deleteRowId = doctorDataSource.deleteDoctorById(doctorModel.getId());
                                        if (deleteRowId > 0){
                                            Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();
                                            doctorModelList.remove(id);
                                            notifyDataSetChanged();
                                            dialog.dismiss();
                                        }
                                    }
                                });
                                dialog.show();
                            }
                            return false;
                        }
                    });
                }
            });
        } else {
            doctorViewHolder.moreOptionBtn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return doctorModelList.size();
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView doctorNameTV, doctorEduTV, doctorProTV, doctorCateTV, doctorAddTV;
        ImageButton doctorCallBtn, doctorEmailBtn;
        TextView moreOptionBtn;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorNameTV = itemView.findViewById(R.id.doctorNameTV);
            doctorEduTV = itemView.findViewById(R.id.doctorEduTV);
            doctorProTV = itemView.findViewById(R.id.doctorProTV);
            doctorCateTV = itemView.findViewById(R.id.doctorCateTV);
            doctorAddTV = itemView.findViewById(R.id.doctorAddTV);
            doctorCallBtn = itemView.findViewById(R.id.doctorCallBtn);
            doctorEmailBtn = itemView.findViewById(R.id.doctorEmailBtn);
            moreOptionBtn = itemView.findViewById(R.id.moreOptionBtn);
        }
    }
}
