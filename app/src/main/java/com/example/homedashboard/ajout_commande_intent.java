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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ajout_commande_intent extends AppCompatActivity {
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Produit> produits = new ArrayList<>();

    private Spinner spinnerclient,produitspinner;
    TextInputEditText quantite_cmd;
    Button btn_insert_command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_commande_intent);
        spinnerclient = findViewById(R.id.clientspinner);
        produitspinner = findViewById(R.id.produitspinner);
        quantite_cmd = findViewById(R.id.quantite_cmd);
        btn_insert_command = findViewById(R.id.btn_insert_command);
        //Spinner
        getClientSpinner();
        getProduitSpinner();
        btn_insert_command.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData(createRequest());
                startActivity(new Intent(getApplicationContext(), commandIntent.class));
            }
        });
    }

    //spinnerclient.getSelectedItem().toString()
    public CommandeRequest createRequest(){
        Produit produitsRequest = new Produit();
        Produit selectedItem1 = produits.get(produitspinner.getSelectedItemPosition());
        int idproduit = selectedItem1.getId();
        produitsRequest.setId(idproduit);

        //produitsRequest.setId(Integer.parseInt(produitspinner.getSelectedItem().toString()));

        Client clientRequest = new Client();
        //fomba atao refa isafidy ny iray am liste deroulante(idcli sy nomcli no eo d lasa ny idcli no halaina)
        Client selectedItem = clients.get(spinnerclient.getSelectedItemPosition());
        int id = selectedItem.getId();
        clientRequest.setId(id);

        CommandeRequest commandeRequest = new CommandeRequest();
        commandeRequest.setQuantite(Integer.parseInt(quantite_cmd.getText().toString()));
        commandeRequest.setClient("/api/clients/"+clientRequest);
        commandeRequest.setProduit("/api/produits/"+produitsRequest);
        return commandeRequest;
    }

    private void SaveData(CommandeRequest commandeRequest) {
        Call<Commande> call = Retrofit_ajout_client.getApiClient().savecommandetData(commandeRequest);
        call.enqueue(new Callback<Commande>() {
            @Override
            public void onResponse(Call<Commande> call, Response<Commande> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ajout_commande_intent.this,"Data inserted success code " +response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ajout_commande_intent.this,"request error code" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Commande> call, Throwable t) {
                Toast.makeText(ajout_commande_intent.this,"Data inserted " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                ArrayAdapter<Produit> adapter = new ArrayAdapter<Produit>(ajout_commande_intent.this, android.R.layout.simple_spinner_item, produits);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                produitspinner.setAdapter(adapter);
            }else {
                Toast.makeText(ajout_commande_intent.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {

            }
        });
    }

    private void getClientSpinner( ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient apiclient = retrofit.create(ApiClient.class);
        Call<List<Client>> call = apiclient.getClient();
        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()){
                    clients = new ArrayList<>(response.body());
                    ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(ajout_commande_intent.this, android.R.layout.simple_spinner_item, clients);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerclient.setAdapter(adapter);

                }else {
                    Toast.makeText(ajout_commande_intent.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {

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