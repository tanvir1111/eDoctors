package com.incubation_lab.edoctors.MainActivity.ui.doctors.recycler;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.incubation_lab.edoctors.Models.DoctorDataModel;
import com.incubation_lab.edoctors.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.incubation_lab.edoctors.Repository.Remote.RetroInstance.BASE_URL;
import static com.incubation_lab.edoctors.StaticData.DOCTOR_BUNDLE_KEY;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsViewHolder> {
    private Context context;
    private ArrayList<DoctorDataModel> doctors;
    private ViewGroup parentGroup;


    public DoctorsAdapter(Context context, ArrayList<DoctorDataModel> doctors) {
        this.context = context;
        this.doctors = doctors;
    }


    @NonNull
    @NotNull
    @Override
    public DoctorsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_child,parent,false);
        DoctorsViewHolder viewHolder= new DoctorsViewHolder(view);
        parentGroup = parent;


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DoctorsViewHolder holder, int position) {

        holder.name.setText(doctors.get(position).getName());
        holder.speciality.setText(doctors.get(position).getSpeciality());
        holder.currentDesignation.setText(doctors.get(position).getCurrentDesignation());
        holder.feeText.setText(doctors.get(position).getFee() + " BDT");
        Picasso.get().load(BASE_URL +"/"+ doctors.get(position).getImageUrl()).placeholder(R.drawable.icon_doctor).into(holder.doctorImage);
        holder.ratingBar.setRating(Float.parseFloat(doctors.get(position).getRating()));

        Bundle bundle = new Bundle();
        bundle.putSerializable(DOCTOR_BUNDLE_KEY,doctors.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.createNavigateOnClickListener(R.id.navigation_doctor_profile,bundle).onClick(holder.itemView);

            }
        });



    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }


}
