package com.incubation_lab.edoctors.Login.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.incubation_lab.edoctors.Login.LoginActivity;
import com.incubation_lab.edoctors.R;


public class MobileVerificationFragment extends Fragment implements View.OnClickListener {
    private TextView getOptBtn;
    private Button continueBtn;
    private EditText phoneEditText,otpEditText;

    public MobileVerificationFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View root =  inflater.inflate(R.layout.fragment_mobile_verification, container, false);
        getOptBtn = root.findViewById(R.id.get_otp);
        continueBtn = root.findViewById(R.id.continue_btn);
        phoneEditText = root.findViewById(R.id.phone_no);
        otpEditText = root.findViewById(R.id.otp);

        getOptBtn.setOnClickListener(this);
        continueBtn.setOnClickListener(this);



        return root;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_otp:
                continueBtn.setEnabled(true);
                otpEditText.setEnabled(true);
                break;
            case R.id.continue_btn:
                ((LoginActivity) getActivity()).setFragment(new RegisterFragment());
                break;
        }
    }
}