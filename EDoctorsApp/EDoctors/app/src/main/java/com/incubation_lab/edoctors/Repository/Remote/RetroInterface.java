package com.incubation_lab.edoctors.Repository.Remote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

import com.incubation_lab.edoctors.Models.DoctorDataModel;
import com.incubation_lab.edoctors.Models.UserDataModel;
import com.incubation_lab.edoctors.Models.UserImageModel;

import java.util.ArrayList;

public interface RetroInterface {

    @POST("/api/user/register")
    Call<UserDataModel> register(@Body UserDataModel userDataModel);

    @POST("/api/user/login")
    Call<UserDataModel> login(@Body UserDataModel userDataModel);
    @POST("/api/user/validateToken")
    Call<UserDataModel> validateToken(@Body UserDataModel token);
    @POST("/api/user/findPhone")
    Call<UserDataModel> findByPhone(@Body UserDataModel phone);
    @PATCH("/api/user/updatePassword")
    Call<UserDataModel> updatePassword(@Body UserDataModel userDataModel);

    @PATCH("/api/user/updatePicture")
    Call<UserImageModel> updatePicture(@Body UserImageModel encodedImage);

    @GET("/api/doctor/getAllDoctors")
    Call<ArrayList<DoctorDataModel>> getAllDoctors();

}
