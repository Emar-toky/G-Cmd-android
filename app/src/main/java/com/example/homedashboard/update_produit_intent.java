package com.example.homedashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class update_produit_intent extends AppCompatActivity {
    private TextInputEditText produit_designation,produit_prix;
    Button btn_update_produit;
    Button btn_delete_produit;
    private String designation="";
    private int prix;
    private int idProduit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_produit_intent);

        idProduit = getIntent().getIntExtra("id_produit",0);
        designation = getIntent().getStringExtra("designation");
        prix = getIntent().getIntExtra("prix",0);
        produit_designation = findViewById(R.id.produit_designation);
        produit_prix = findViewById(R.id.produit_prix);
        btn_update_produit=findViewById(R.id.btn_update_produit);
        btn_delete_produit = findViewById(R.id.btn_delete_produit);
        produit_designation.setText(designation);
        produit_prix.setText("" +prix);

        btn_update_produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(idProduit,createRequest());
                startActivity(new Intent(getApplicationContext(), produitsIntent.class));
            }
        });

        btn_delete_produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(idProduit);
                startActivity(new Intent(getApplicationContext(), produitsIntent.class));
            }
        });
    }

    private void deleteData(int id) {
        Call<Void> call = Retrofit_ajout_client.getApiClient().deleteDataProduit(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(update_produit_intent.this,"Delete success code " +response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(update_produit_intent.this,"request error code" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(update_produit_intent.this,"Data Deleted " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public ProduitsRequest createRequest(){
        ProduitsRequest produitsRequest = new ProduitsRequest();
        produitsRequest.setDesignation(produit_designation.getText().toString());
        produitsRequest.setPu(Integer.parseInt(produit_prix.getText().toString()));
        return produitsRequest;
    }

    private void updateData(int id, ProduitsRequest produitsRequest) {
        Call<Produit> call = Retrofit_ajout_client.getApiClient().updateData(id,produitsRequest);
        call.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Call<Produit> call, Response<Produit> response) {
                if(response.isSuccessful()){
                    Toast.makeText(update_produit_intent.this,"Data updated success code " +response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(update_produit_intent.this,"request error code" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produit> call, Throwable t) {
                    Toast.makeText(update_produit_intent.this,"Data inserted " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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