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


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private TextInputLayout user, pass;
    private Button loginBtn;

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

        loginBtn.setOnClickListener(loginListener);

    }

    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), user.getEditText().getText().toString()+" "+pass.getEditText().getText().toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
