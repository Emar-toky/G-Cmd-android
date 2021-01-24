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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class clientIntent extends AppCompatActivity {
    ArrayList<Client> clients = new ArrayList<>();
    private ClientAdapter clientAdapter;
    private RecyclerView client_recyclerview;
    ImageView ajout_client_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_intent);
        ajout_client_btn = findViewById(R.id.ajout_client_btn);
        ajout_client_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ajout_client_intent.class));
            }
        });

        client_recyclerview = findViewById(R.id.client_recyclerview);
        client_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        getClientResponse();
    }

    private void getClientResponse() {
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
                    clientAdapter = new ClientAdapter(clientIntent.this,clients);
                    client_recyclerview.setAdapter(clientAdapter);
                    Toast.makeText(clientIntent.this,"Connection Success code " +response.code(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(clientIntent.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Toast.makeText(clientIntent.this,"Connection failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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