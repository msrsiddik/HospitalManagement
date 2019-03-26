package com.zerone.hospitalmanagement;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private TextInputLayout user, pass;
    private Button loginBtn;
    private FragmentController controller;
    private UserPreference userPreference;

    private DoctorDataSource doctorDataSource;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = view.findViewById(R.id.user);
        pass = view.findViewById(R.id.pass);
        loginBtn = view.findViewById(R.id.loginBtn);

        userPreference = new UserPreference(getContext());

        doctorDataSource = new DoctorDataSource(getContext());

        loginBtn.setOnClickListener(loginListener);

    }

    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = user.getEditText().getText().toString();
            String password = pass.getEditText().getText().toString();

            for (int i = 0; i < doctorDataSource.doctorUserModels().size(); i++) {

                String email = doctorDataSource.doctorUserModels().get(i).getEmail();
                String pass = doctorDataSource.doctorUserModels().get(i).getPassword();

                if (username.equals("admin") && password.equals("admin")){
                    userPreference.setLoginStatus(true);
                    controller = (FragmentController) getActivity();
                    controller.gotoAdminPanel();
                }
                else if (username.equals(email) && password.equals(pass)){
                    userPreference.setLoginStatus(true);
                    controller = (FragmentController) getActivity();
                    controller.gotoPatientList(doctorDataSource.getAlldoctorInfoCollectList().get(i).getDoctorName());
                }
                else if (!username.equals(email) && !password.equals(pass)){
                    Toast.makeText(getActivity(), "wrong user", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
