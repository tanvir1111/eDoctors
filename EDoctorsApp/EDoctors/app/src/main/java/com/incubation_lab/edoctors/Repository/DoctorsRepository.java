package com.incubation_lab.edoctors.Repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.incubation_lab.edoctors.Models.DoctorDataModel;
import com.incubation_lab.edoctors.Repository.Remote.RetroInstance;
import com.incubation_lab.edoctors.Repository.Remote.RetroInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsRepository {
    private Application application;
    private RetroInterface retroInterface;


    public static MutableLiveData<ArrayList<DoctorDataModel>> allDoctors =new MutableLiveData<>();


    public DoctorsRepository(Application application) {
        retroInterface= RetroInstance.getRetro();
        this.application= application;


    }
    public void fetchAllDoctors(){
        Call<ArrayList<DoctorDataModel>> call = retroInterface.getAllDoctors();
        call.enqueue(new Callback<ArrayList<DoctorDataModel>> () {
            @Override
            public void onResponse(Call<ArrayList<DoctorDataModel>> call, Response<ArrayList<DoctorDataModel>> response) {
                if(response.isSuccessful()){
                   allDoctors.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DoctorDataModel>> call, Throwable t) {

                Toast.makeText(application, "Network error", Toast.LENGTH_SHORT).show();

            }
        });
    }
    
    public LiveData<ArrayList<DoctorDataModel>> getAllDoctors() {
        return allDoctors;
    }
}
