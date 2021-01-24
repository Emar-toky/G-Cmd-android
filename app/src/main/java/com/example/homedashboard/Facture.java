package com.example.homedashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Facture {
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("nom_client")
    @Expose
    private String nomClient;
    @SerializedName("produit_id")
    @Expose
    private String produitId;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("pu")
    @Expose
    private String pu;
    @SerializedName("quantite")
    @Expose
    private String quantite;
    @SerializedName("total_kely")
    @Expose
    private String totalKely;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getProduitId() {
        return produitId;
    }

    public void setProduitId(String produitId) {
        this.produitId = produitId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getTotalKely() {
        return totalKely;
    }

    public void setTotalKely(String totalKely) {
        this.totalKely = totalKely;
    }
}
