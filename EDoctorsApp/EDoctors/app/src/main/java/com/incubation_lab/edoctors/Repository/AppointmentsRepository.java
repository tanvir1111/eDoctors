package com.incubation_lab.edoctors.Repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.incubation_lab.edoctors.Models.AppointmentDataModel;
import com.incubation_lab.edoctors.Repository.Remote.RetroInstance;
import com.incubation_lab.edoctors.Repository.Remote.RetroInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentsRepository {
    private Application application;
    private RetroInterface retroInterface;


    public AppointmentsRepository(Application application) {
        retroInterface= RetroInstance.getRetro();
        this.application= application;
//        fetchDoctorAppointmentList("12345");


    }

    private MutableLiveData<ArrayList<AppointmentDataModel>> appointmentList=new MutableLiveData<>();
    private MutableLiveData<String> updateSerialMsg =new MutableLiveData<>();
    private MutableLiveData<String> currentSerial =new MutableLiveData<>();
    public void fetchAppointmentList(String id,String mode) {
        Call<ArrayList<AppointmentDataModel>> call;
        if(mode.equals("doctor")) {
            call = retroInterface.getDoctorAppointmentList(id);
        }
        else {
            call = retroInterface.getPatientAppointmentList(id);
        }
        call.enqueue(new Callback<ArrayList<AppointmentDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AppointmentDataModel>> call, Response<ArrayList<AppointmentDataModel>> response) {
                if(response.isSuccessful() && response.code()==200){
                    appointmentList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AppointmentDataModel>> call, Throwable t) {
                Toast.makeText(application, "Something Went wrong "+ t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

//    public DoctorDataModel getDoctorInformation() {
//
//            Call<DoctorDataModel> call =retroInterface.getSingleDoctorData();
//
//            call.enqueue(new Callback<DoctorDataModel>() {
//                @Override
//                public void onResponse(Call<DoctorDataModel> call, Response<DoctorDataModel> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<DoctorDataModel> call, Throwable t) {
//
//                }
//            });
//
//    }



    public LiveData<ArrayList<AppointmentDataModel>> getDoctorAppointmentList(String doctorId) {
        fetchAppointmentList(doctorId,"doctor");
        return appointmentList;
    }

    public LiveData<ArrayList<AppointmentDataModel>> getPatientAppointmentList(String patientId) {

        fetchAppointmentList(patientId,"patient");
        return appointmentList;
    }

    public LiveData<String>  updateCurrentSerial(AppointmentDataModel appointmentDataModel) {
        updateSerialMsg.setValue("wait");
       Call<AppointmentDataModel> call = retroInterface.updateCurrentSerial(appointmentDataModel);
       call.enqueue(new Callback<AppointmentDataModel>() {
           @Override
           public void onResponse(Call<AppointmentDataModel> call, Response<AppointmentDataModel> response) {
               if(response.isSuccessful() && response.code()==200 ){
                   updateSerialMsg.setValue("success");
               }
               else if(response.code()==500){
                   updateSerialMsg.setValue("failure");
               }
           }

           @Override
           public void onFailure(Call<AppointmentDataModel> call, Throwable t) {
               updateSerialMsg.setValue("failure");

           }
       });
       return updateSerialMsg;
    }

    public LiveData<String> getCurrentSerial(String doctorId) {
        Call<AppointmentDataModel> call = retroInterface.getCurrentSerial(doctorId);
        call.enqueue(new Callback<AppointmentDataModel>() {
            @Override
            public void onResponse(Call<AppointmentDataModel> call, Response<AppointmentDataModel> response) {
                if(response.isSuccessful()){
                    if(response.code() ==200){
                        currentSerial.setValue(response.body().getSerial());
                    }
                }
            }

            @Override
            public void onFailure(Call<AppointmentDataModel> call, Throwable t) {

            }
        });
        return currentSerial;
    }
}
