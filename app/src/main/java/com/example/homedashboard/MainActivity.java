package com.example.homedashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout client_menu;
    LinearLayout produit_menu;
    LinearLayout command_menu;
    LinearLayout facture_menu,chiffre_menu,chart_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //client menu
        client_menu = findViewById(R.id.client_menu);
        client_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), clientIntent.class));
            }
        });

        //produits menu
        produit_menu = findViewById(R.id.prduit_menu);
        produit_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), produitsIntent.class));
            }
        });

        //Command menu
        command_menu= findViewById(R.id.command_menu);
        command_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),commandIntent.class));
            }
        });

        //Facture menu
        facture_menu = findViewById(R.id.facture_menu);
        facture_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),factureIntent.class));
            }
        });


        //Chiffre d'affaire menu
        chiffre_menu = findViewById(R.id.chiffre_menu);
        chiffre_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChiffreIntent.class));
            }
        });

        //Chart Menu
        chart_menu = findViewById(R.id.chart_menu);
        chart_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChartItent.class));
            }
        });
    }
}