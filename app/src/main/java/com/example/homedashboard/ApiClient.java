package com.example.homedashboard;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient {
    //Client
    @GET("clients")
    Call<List<Client>> getClient();

    @POST("clients")
    Call<Client> saveclientData(@Body ClientRequest clientRequest);

    @PUT("clients/{id}")
    Call<Client> updateData(@Path("id") int id,@Body ClientRequest clientRequest);

    @DELETE("clients/{id}")
    Call<Void> deleteData(@Path("id") int id);
    //EndClient



    //Produits
    @GET("produits")
    Call<List<Produit>> getProduit();

    @POST("produits")
    Call<Produit> saveproduitData(@Body ProduitsRequest produitsRequest);

    @PUT("produits/{id}")
    Call<Produit> updateData(@Path("id") int id,@Body ProduitsRequest produitsRequest);

    @DELETE("produits/{id}")
    Call<Void> deleteDataProduit(@Path("id") int id);
    //EndProduit



    //Commande
    //Client
    @GET("commandes")
    Call<List<Commande>> getCommande();

    @POST("commandes")
    Call<Commande> savecommandetData(@Body CommandeRequest commandeRequest);

    @GET(" ")
    Call<Client> getClienko();

    @GET(" ")
    Call<Produit> produiko();

    @PUT("commandes/{id}")
    Call<Commande> updateDataCommande(@Path("id") int id,@Body CommandeRequest commandeRequest);

    @DELETE("commandes/{id}")
    Call<Void> deleteDataCommande(@Path("id") int id);



    //Facture
    @GET("facture/{id}")
    Call<List<Facture>> getFacture(@Path("id") int id);
    //total de facture
    @GET("factureTotal/{id}")
    Call<List<FactureTotal>> getFactureTotal(@Path("id") int id);

    //Chifrre d'affaire
    @GET("chiffre-affaire")
    Call<List<Chiffre>> getChiffre();

    //cmd
    @GET("cmd")
    Call<List<Cmd>> getCmd();

}
