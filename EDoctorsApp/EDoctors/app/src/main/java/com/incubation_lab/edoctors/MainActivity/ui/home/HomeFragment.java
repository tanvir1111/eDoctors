package com.incubation_lab.edoctors.MainActivity.ui.home;

import static com.incubation_lab.edoctors.StaticData.LoggedInUserData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.incubation_lab.edoctors.R;

import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView greetText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        greetText = root.findViewById(R.id.greet_text);

        Date date = new Date();
        int h = date.getHours();
        String userName =  LoggedInUserData.getValue().getFirstName();

        if(h<3 || h>21)
            greetText.setText("Good Night\n" + userName);
        else if(h<12)
            greetText.setText("Good Morning\n" + userName);
        else if(h<18)
            greetText.setText("Good Afternoon\n" + userName);
        else
            greetText.setText("Good Evening\n" + userName);



        return root;
    }
}