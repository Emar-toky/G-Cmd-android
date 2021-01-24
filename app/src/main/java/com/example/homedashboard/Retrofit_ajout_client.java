package com.example.homedashboard;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_ajout_client {

   private static Retrofit getRetrofit(){
      HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
      Retrofit retrofit = new Retrofit.Builder()
           .baseUrl("http://192.168.137.1:8000/api/")
           .addConverterFactory(GsonConverterFactory.create())
           .client(okHttpClient)
           .build();

   return retrofit;
   }

   public  static ApiClient getApiClient(){
      ApiClient apiClient = getRetrofit().create(ApiClient.class);
      return apiClient;
   }

}
