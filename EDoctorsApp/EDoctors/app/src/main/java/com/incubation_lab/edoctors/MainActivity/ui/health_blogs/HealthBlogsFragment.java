package com.incubation_lab.edoctors.MainActivity.ui.health_blogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.incubation_lab.edoctors.R;

public class HealthBlogsFragment extends Fragment {

    private HealthBlogsViewModel healthBlogsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        healthBlogsViewModel =
                new ViewModelProvider(this).get(HealthBlogsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_health_blogs, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        healthBlogsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}