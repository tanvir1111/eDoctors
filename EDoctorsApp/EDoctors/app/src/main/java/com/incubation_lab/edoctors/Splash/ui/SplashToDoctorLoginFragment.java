package com.incubation_lab.edoctors.Splash.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.incubation_lab.edoctors.R;

public class SplashToDoctorLoginFragment extends Fragment {
    public static Fragment newInstance() {
        return new SplashToDoctorLoginFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_splash_to_doctor_login, container, false);

        return root;
    }
}
