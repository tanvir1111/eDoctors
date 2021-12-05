package com.incubationlab.edoctors_doctors.Main.ui.HealhBlogs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incubationlab.edoctors_doctors.R;


public class HealthBlogsFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_blogs, container, false);
    }
}