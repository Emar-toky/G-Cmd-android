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

public class ajout_produit_intent extends AppCompatActivity {
    TextInputEditText produit_designation,produit_prix;
    Button btn_insert_produit;
    private int prix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_produit_intent);

        produit_designation =findViewById(R.id.produit_designation);
        produit_prix=findViewById(R.id.produit_prix);
        btn_insert_produit=findViewById(R.id.btn_insert_produit);

        btn_insert_produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataProduit(createRequest());
                startActivity(new Intent(getApplicationContext(), produitsIntent.class));
            }
        });
    }

    public ProduitsRequest createRequest(){
        ProduitsRequest produitsRequest = new ProduitsRequest();
        produitsRequest.setDesignation(produit_designation.getText().toString());
        produitsRequest.setPu(Integer.parseInt(produit_prix.getText().toString()));
        return produitsRequest;
    }

    private void saveDataProduit(ProduitsRequest produitsRequest) {
        Call<Produit> call = Retrofit_ajout_client.getApiClient().saveproduitData(produitsRequest);
        call.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Call<Produit> call, Response<Produit> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ajout_produit_intent.this,"Data inserted success code " +response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ajout_produit_intent.this,"request error code" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produit> call, Throwable t) {
                Toast.makeText(ajout_produit_intent.this,"Data inserted " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
