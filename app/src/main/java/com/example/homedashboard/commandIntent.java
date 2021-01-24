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

public class commandIntent extends AppCompatActivity {
    ImageView ajout_copmmande_btn;
    private RecyclerView commande_recyclerview;
    private CommandeAdapter commandeAdapter;
    ArrayList<Cmd> cmds = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_intent);
        ajout_copmmande_btn = findViewById(R.id.ajout_copmmande_btn);
        ajout_copmmande_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ajout_commande_intent.class));
            }
        });

        commande_recyclerview = findViewById(R.id.commande_recyclerview);
        commande_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        getCommandeResponse();
    }

    private void getCommandeResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient apiclient = retrofit.create(ApiClient.class);
        Call<List<Cmd>> call = apiclient.getCmd();
        call.enqueue(new Callback<List<Cmd>>() {
            @Override
            public void onResponse(Call<List<Cmd>> call, Response<List<Cmd>> response) {
                if (response.isSuccessful()){
                    cmds = new ArrayList<>(response.body());
                    commandeAdapter = new CommandeAdapter(commandIntent.this,cmds);
                    commande_recyclerview.setAdapter(commandeAdapter);
                    Toast.makeText(commandIntent.this,"Connection Success code " +response.code(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(commandIntent.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cmd>> call, Throwable t) {
                Toast.makeText(commandIntent.this,"Connection failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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