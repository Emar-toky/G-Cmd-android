package com.example.homedashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class facture_d_un_client extends AppCompatActivity {
    ArrayList<Facture> factures = new ArrayList<>();
    ArrayList<FactureTotal> factureTotals = new ArrayList<>();
    private RecyclerView facture_d_un_client_recyclerview;
    private facture_d_un_clientAdapter facture_d_un_clientAdapter;
    private FactureTotal_Adapter factureTotal_adapter;
    private int idClient;
    private String nom="";
    TextView total_total,nom_clien,num_cli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facture_d_un_client);
        idClient = getIntent().getIntExtra("idClient",0);
        nom = getIntent().getStringExtra("nomClient");
        nom_clien = findViewById(R.id.nom_clien);
        num_cli = findViewById(R.id.num_cli);
        nom_clien.setText(nom);
        num_cli.setText("CLI"+idClient);

        facture_d_un_client_recyclerview = findViewById(R.id.facture_d_un_client_recyclerview);
        facture_d_un_client_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        total_total = findViewById(R.id.total_total);
        getFacture(idClient);
        getFactureTotal(idClient);
    }

    private void getFactureTotal(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient apiclient = retrofit.create(ApiClient.class);
        Call<List<FactureTotal>> call = apiclient.getFactureTotal(id);
        call.enqueue(new Callback<List<FactureTotal>>() {
            @Override
            public void onResponse(Call<List<FactureTotal>> call, Response<List<FactureTotal>> response) {
                if(response.isSuccessful()){
                    List<FactureTotal> factureTotals = response.body();
                    for (FactureTotal factureTotal : factureTotals){
                        String content ="";
                        content +="TOTAL " +factureTotal.getTotal();
                        total_total.append(content);
                    }
                }else {
                    Toast.makeText(facture_d_un_client.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FactureTotal>> call, Throwable t) {
                Toast.makeText(facture_d_un_client.this,"Connection failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getFacture(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient apiclient = retrofit.create(ApiClient.class);
        Call<List<Facture>> call = apiclient.getFacture(id);
        call.enqueue(new Callback<List<Facture>>() {
            @Override
            public void onResponse(Call<List<Facture>> call, Response<List<Facture>> response) {
            if (response.isSuccessful()){
                factures = new ArrayList<>(response.body());
                facture_d_un_clientAdapter = new facture_d_un_clientAdapter(facture_d_un_client.this,factures);
                facture_d_un_client_recyclerview.setAdapter(facture_d_un_clientAdapter);
                Toast.makeText(facture_d_un_client.this,"Connection Success code " +response.code(),Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(facture_d_un_client.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
            }

            }

            @Override
            public void onFailure(Call<List<Facture>> call, Throwable t) {
                Toast.makeText(facture_d_un_client.this,"Connection failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
