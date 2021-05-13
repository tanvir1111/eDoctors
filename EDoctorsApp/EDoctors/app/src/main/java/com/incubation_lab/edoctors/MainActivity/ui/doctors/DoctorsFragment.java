package com.incubation_lab.edoctors.MainActivity.ui.doctors;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incubation_lab.edoctors.MainActivity.ui.doctors.recycler.DoctorsAdapter;
import com.incubation_lab.edoctors.R;

import org.jetbrains.annotations.NotNull;

public class DoctorsFragment extends Fragment {

    private DoctorsViewModel mViewModel;
    private RecyclerView doctorRecyclerView;

    public static DoctorsFragment newInstance() {
        return new DoctorsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_doctors, container, false);
        doctorRecyclerView = root.findViewById(R.id.doctor_recycler);
        doctorRecyclerView.setAdapter(new DoctorsAdapter(getContext()));
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));




        return root;
    }


}