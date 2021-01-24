package com.example.homedashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commande {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("quantite")
    @Expose
    private Integer quantite;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("produit")
    @Expose
    private String produit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }
}
