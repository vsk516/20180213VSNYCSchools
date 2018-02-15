package com.poc.nycschools.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NYCInfo implements Serializable {

    @SerializedName("dbn")
    private String dbn;


    @SerializedName("school_name")
    private String name;

    @SerializedName("phone_number")
    private String mobile;

    @SerializedName("school_email")
    private String email;

    @SerializedName("primary_address_line_1")
    private String address;

    @SerializedName("state_code")
    private String stateCode;

    @SerializedName("total_students")
    private String totalStudent;

    @SerializedName("website")
    private String website;

    @SerializedName("zip")
    private String zipCode;

    @SerializedName("latitude")
    private String lat;

    @SerializedName("longitude")
    private String lng;

    @SerializedName("city")
    private String city;

    public String getName() {
        return name;
    }

    public String getDbn() {
        return dbn;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getTotalStudent() {
        return totalStudent;
    }

    public String getWebsite() {
        return website;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getCity() {
        return city;
    }
}
