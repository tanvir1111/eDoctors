package com.incubation_lab.edoctors.Login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.incubation_lab.edoctors.MainActivity.MainActivity;
import com.incubation_lab.edoctors.R;

public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_login, container, false);
        Button signInBtn= root.findViewById(R.id.sing_in);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        return root;
    }
}
