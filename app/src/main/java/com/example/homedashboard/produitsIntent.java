package com.example.homedashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class produitsIntent extends AppCompatActivity {
    ArrayList<Produit> produits = new ArrayList<>(); 
    private ProduitAdapter produitAdapter;
    private RecyclerView produit_recyclerview;
    ImageView ajout_produit_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produits_intent);
        ajout_produit_btn = findViewById(R.id.ajout_produit_btn);
        ajout_produit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ajout_produit_intent.class));
            }
        });


        produit_recyclerview = findViewById(R.id.produit_recyclerview);
        produit_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        getProduitResponse();
    }

    private void getProduitResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient apiclient = retrofit.create(ApiClient.class);
        Call<List<Produit>> call = apiclient.getProduit();
        call.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                if (response.isSuccessful()){
                    produits = new ArrayList<>(response.body());
                    produitAdapter = new ProduitAdapter(produitsIntent.this,produits );
                    produit_recyclerview.setAdapter(produitAdapter);
                    Toast.makeText(produitsIntent.this,"Connection Success code " +response.code(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(produitsIntent.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {
                Toast.makeText(produitsIntent.this,"Connection failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show(); 
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
