package com.example.homedashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class update_client_intent extends AppCompatActivity {
    private TextInputEditText nom_client;
    Button btn_update_client;
    Button btn_delete_client;
    private String nomClient="";
    private int idClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client_intent);

        nomClient= getIntent().getStringExtra("nomClient");
        idClient= getIntent().getIntExtra("idClient",0); //ity no variable id anilay client ampiasaina refa manao edit na delete
        nom_client = findViewById(R.id.nom_client);
        btn_update_client = findViewById(R.id.btn_update_client);
        btn_delete_client = findViewById(R.id.btn_delete_client);
        nom_client.setText(nomClient);


        btn_update_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(idClient,createRequest());
                startActivity(new Intent(getApplicationContext(), clientIntent.class));
            }
        });

        btn_delete_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(idClient);
                startActivity(new Intent(getApplicationContext(), clientIntent.class));
            }
        });

    }

    private void deleteData(int id) {
        Call<Void> call = Retrofit_ajout_client.getApiClient().deleteData(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(update_client_intent.this,"Delete success code " +response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(update_client_intent.this,"request error code" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(update_client_intent.this,"Data Deleted " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public ClientRequest createRequest(){
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setNomClient(nom_client.getText().toString());
        return clientRequest;
    }

    private void updateData(int id,ClientRequest clientRequest) {
        Call<Client> call = Retrofit_ajout_client.getApiClient().updateData(id,clientRequest);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(update_client_intent.this,"Data updated success code " +response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(update_client_intent.this,"request error code" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(update_client_intent.this,"Data inserted " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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