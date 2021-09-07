package com.incubation_lab.edoctors.MainActivity.ui.doctors;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.incubation_lab.edoctors.Models.DoctorDataModel;
import com.incubation_lab.edoctors.R;
import com.squareup.picasso.Picasso;

import static com.incubation_lab.edoctors.Repository.Remote.RetroInstance.baseUrl;
import static com.incubation_lab.edoctors.StaticData.DOCTOR_BUNDLE_KEY;

public class ViewDoctorProfileFragment extends Fragment {
    private TextView tvName,tvFee,tvDesignation,tvDegree,tvAbout;
    private ImageView ivDoctorImage;
    private RatingBar rbDoctorRating;


    public ViewDoctorProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_view_doctor_profile, container, false);

        DoctorDataModel doctorData = (DoctorDataModel) getArguments().getSerializable(DOCTOR_BUNDLE_KEY);
        Toast.makeText(getContext(), doctorData.getName(), Toast.LENGTH_SHORT).show();
        tvName = root.findViewById(R.id.doctor_profile_name);
        tvFee = root.findViewById(R.id.doctor_profile_fee);
        tvDesignation = root.findViewById(R.id.doctor_profile_designation);
        tvDegree = root.findViewById(R.id.doctor_profile_degree);
        tvAbout = root.findViewById(R.id.doctor_profile_about);
        ivDoctorImage = root.findViewById(R.id.doctor_profile_image);
        rbDoctorRating = root.findViewById(R.id.doctor_profile_ratingBar);

        tvName.setText(doctorData.getName());
        tvFee.setText(doctorData.getFee());
        tvDesignation.setText(doctorData.getCurrentDesignation());
        tvDegree.setText(doctorData.getQualifications());
        tvAbout.setText(doctorData.getBio());
        Picasso.get().load(baseUrl+"/"+doctorData.getImageUrl()).into(ivDoctorImage);
        rbDoctorRating.setRating(Float.parseFloat(doctorData.getRating()));




        return root;
    }
}