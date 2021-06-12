package com.incubation_lab.edoctors.MainActivity.ui.doctors.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.incubation_lab.edoctors.Models.DoctorDataModel;
import com.incubation_lab.edoctors.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.incubation_lab.edoctors.Repository.Remote.RetroInstance.baseUrl;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsViewHolder> {
    private Context context;
    private ArrayList<DoctorDataModel> doctors;

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

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DoctorsViewHolder holder, int position) {

        holder.name.setText(doctors.get(position).getName());
        holder.speciality.setText(doctors.get(position).getSpeciality());
        holder.currentDesignation.setText(doctors.get(position).getCurrentDesignation());
        holder.feeText.setText(doctors.get(position).getFee() + " BDT");
        Picasso.get().load(baseUrl +"/"+ doctors.get(position).getImageUrl()).placeholder(R.drawable.icon_doctor).into(holder.doctorImage);



    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }


}
