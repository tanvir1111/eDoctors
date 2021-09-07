package com.incubation_lab.edoctors.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DoctorDataModel implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bmdc")
    @Expose
    private String bmdc;
    @SerializedName("designation")
    @Expose
    private String currentDesignation;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("qualifications")
    @Expose
    private String qualifications;
    @SerializedName("speciality")
    @Expose
    private String speciality;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("serverMsg")
    @Expose
    private String serverMsg;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("rating")
    @Expose
    private String rating;

    public String getName() {
        return name;
    }

    public String getBmdc() {
        return bmdc;
    }

    public String getCurrentDesignation() {
        return currentDesignation;
    }

    public String getBio() {
        return bio;
    }

    public String getQualifications() {
        return qualifications;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getFee() {
        return fee;
    }

    public String getPhone() {
        return phone;
    }

    public String getServerMsg() {
        return serverMsg;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getRating() {
        return rating;
    }

}
