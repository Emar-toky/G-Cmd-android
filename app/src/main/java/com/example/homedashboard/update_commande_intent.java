package com.example.homedashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class update_commande_intent extends AppCompatActivity {
    TextInputEditText quantite_command;
    TextView produit_designation;
    private int idCommand;
    private int quantite;
    private String designation="";
    Button btn_delete_commande,btn_update_commande;
    private Spinner produitspinner;
    ArrayList<Produit> produits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_commande_intent);
        produitspinner = findViewById(R.id.produitspinner);

        idCommand = getIntent().getIntExtra("idCommande",0);
        designation = getIntent().getStringExtra("produits");
        quantite = getIntent().getIntExtra("quantite",0);
        quantite_command = findViewById(R.id.quantite_command);
        produit_designation = findViewById(R.id.produit_designation);
        btn_delete_commande = findViewById(R.id.btn_delete_commande);
        btn_update_commande = findViewById(R.id.btn_update_commande);

        getProduitSpinner();

        //produit_designation.setText(designation);
        quantite_command.setText("" +quantite);
        //delete commande
        btn_delete_commande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(idCommand);
                startActivity(new Intent(getApplicationContext(), commandIntent.class));
            }
        });

        //update commande
        btn_update_commande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(idCommand,createRequest());
                startActivity(new Intent(getApplicationContext(), commandIntent.class));
            }
        });
    }

    public CommandeRequest createRequest(){
        Produit produitsRequest = new Produit();
        Produit selectedItem1 = produits.get(produitspinner.getSelectedItemPosition());
        int idproduit = selectedItem1.getId();
        produitsRequest.setId(idproduit);


        CommandeRequest commandeRequest = new CommandeRequest();
        commandeRequest.setQuantite(Integer.parseInt(quantite_command.getText().toString()));
        commandeRequest.setProduit("/api/produits/"+produitsRequest);
        return commandeRequest;
    }

    private void updateData(int id, CommandeRequest commandeRequest) {
        Call<Commande> call = Retrofit_ajout_client.getApiClient().updateDataCommande(id,commandeRequest);
        call.enqueue(new Callback<Commande>() {
            @Override
            public void onResponse(Call<Commande> call, Response<Commande> response) {
                if(response.isSuccessful()){
                    Toast.makeText(update_commande_intent.this,"Data updated success code " +response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(update_commande_intent.this,"request error code" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Commande> call, Throwable t) {
                Toast.makeText(update_commande_intent.this,"Data inserted " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteData(int id) {
        Call<Void> call = Retrofit_ajout_client.getApiClient().deleteDataCommande(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(update_commande_intent.this,"Delete success code " +response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(update_commande_intent.this,"request error code" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(update_commande_intent.this,"Data Deleted " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProduitSpinner() {
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
                    ArrayAdapter<Produit> adapter = new ArrayAdapter<Produit>(update_commande_intent.this, android.R.layout.simple_spinner_item, produits);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    produitspinner.setAdapter(adapter);
                }else {
                    Toast.makeText(update_commande_intent.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {

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




