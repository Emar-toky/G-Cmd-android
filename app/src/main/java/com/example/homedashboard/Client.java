package com.example.homedashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Client {
    private int id;
    @SerializedName("nomClient")
    @Expose
    private String nomClient;

    public int getId() {
        return id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    @Override
    public String toString() {
        return "" +id  +" - " +nomClient;
    }
}

