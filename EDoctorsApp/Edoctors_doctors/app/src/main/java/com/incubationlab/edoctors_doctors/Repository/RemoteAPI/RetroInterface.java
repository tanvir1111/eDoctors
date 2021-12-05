package com.incubationlab.edoctors_doctors.Repository.RemoteAPI;



import com.incubationlab.edoctors_doctors.Models.DoctorDataModel;
import com.incubationlab.edoctors_doctors.Models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroInterface {

    @POST("/api/doctor/register")
    Call<ResponseModel> register(@Body DoctorDataModel doctorDataModel);
    @POST("/api/doctor/login")
    Call<DoctorDataModel> login(@Body DoctorDataModel doctorDataModel);
    @POST("/api/doctor/findDoctorByPhone/{doctor_phone}")
    Call<ResponseModel> findDoctorByPhone(@Path("doctor_phone") String doctor_phone);
    @POST("/api/doctor/login/{token}")
    Call<DoctorDataModel> loginWithToken(@Path("token") String token);
}
