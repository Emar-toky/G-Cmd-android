package com.example.homedashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChiffreIntent extends AppCompatActivity {
    private RecyclerView chiffre_recyclerview;
    ArrayList<Chiffre> chiffres = new ArrayList<>();
    private ChiffreAdapter chiffreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chiffre_intent);
        chiffre_recyclerview = findViewById(R.id.chiffre_recyclerview);
        chiffre_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        getChiffreAffaire();
    }

    private void getChiffreAffaire() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient apiclient = retrofit.create(ApiClient.class);
        Call<List<Chiffre>> call = apiclient.getChiffre();
        call.enqueue(new Callback<List<Chiffre>>() {
            @Override
            public void onResponse(Call<List<Chiffre>> call, Response<List<Chiffre>> response) {
                if(response.isSuccessful()){
                    chiffres = new ArrayList<>(response.body());
                    chiffreAdapter = new ChiffreAdapter(ChiffreIntent.this,chiffres );
                    chiffre_recyclerview.setAdapter(chiffreAdapter);
                    Toast.makeText(ChiffreIntent.this,"Connection Success code " +response.code(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ChiffreIntent.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Chiffre>> call, Throwable t) {
                Toast.makeText(ChiffreIntent.this,"Connection failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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