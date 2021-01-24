package com.example.homedashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientRequest {
    @SerializedName("nomClient")
    @Expose
    private String nomClient;



    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
}
