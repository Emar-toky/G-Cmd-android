package com.example.homedashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chiffre {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nom_client")
    @Expose
    private String nomClient;
    @SerializedName("chiffre_affaire")
    @Expose
    private String chiffreAffaire;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getChiffreAffaire() {
        return chiffreAffaire;
    }

    public void setChiffreAffaire(String chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }
}
