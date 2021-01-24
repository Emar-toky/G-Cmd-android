package com.example.homedashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProduitsRequest {
    @SerializedName("Designation")
    @Expose
    private String Designation;
    private int Pu;

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public int getPu() {
        return Pu;
    }

    public void setPu(int pu) {
        Pu = pu;
    }
}
