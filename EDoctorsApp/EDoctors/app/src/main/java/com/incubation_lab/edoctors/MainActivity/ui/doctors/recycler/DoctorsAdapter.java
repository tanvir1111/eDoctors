package com.incubation_lab.edoctors.MainActivity.ui.doctors.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.incubation_lab.edoctors.R;

import org.jetbrains.annotations.NotNull;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsViewHolder> {
    Context context;
    public DoctorsAdapter(Context context) {
        this.context = context;
    }




    @NonNull
    @NotNull
    @Override
    public DoctorsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_child,parent,false);
        DoctorsViewHolder viewholder= new DoctorsViewHolder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DoctorsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }


}
