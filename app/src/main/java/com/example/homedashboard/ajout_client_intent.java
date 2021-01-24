package com.example.homedashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ajout_client_intent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextInputEditText nom_client;
    Button btn_insert_client;

    String[] courses = { "C", "Data structures",
            "Interview prep", "Algorithms",
            "DSA with java", "OS" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_client_intent);
        nom_client = findViewById(R.id.nom_client);
        btn_insert_client = findViewById(R.id.btn_insert_client);


        btn_insert_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(createRequest());
                startActivity(new Intent(getApplicationContext(), clientIntent.class));
            }
        });
    }



    public ClientRequest createRequest(){
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setNomClient(nom_client.getText().toString());
        return clientRequest;
    }

    private void saveData(ClientRequest clientRequest) {
        Call<Client> call = Retrofit_ajout_client.getApiClient().saveclientData(clientRequest);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ajout_client_intent.this,"Data inserted success code " +response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ajout_client_intent.this,"request error code" +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(ajout_client_intent.this,"Data inserted " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),
                courses[position],
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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