package com.example.homedashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartItent extends AppCompatActivity {
    PieChart piechart;
    ArrayList<Chiffre> chiffres = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_itent);
        piechart = findViewById(R.id.piechart);

        piechart.setUsePercentValues(true);
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(5,10,5,5);

        piechart.setDragDecelerationFrictionCoef(0.95f);
        piechart.setDrawHoleEnabled(true);
        piechart.setHoleColor(Color.WHITE);
        piechart.setTransparentCircleRadius(61f);



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

                    List<Chiffre> chiffres = response.body();
                    ArrayList<PieEntry> yValues = new ArrayList<>();

                    for (Chiffre chiffre : chiffres){
                        String content ="";
                        String nomclient="";
                        content +="" +chiffre.getChiffreAffaire();
                        nomclient +="" +chiffre.getNomClient();

                        yValues.add(new PieEntry(Integer.parseInt(content),nomclient +" " +  content ));

                        PieDataSet dataSet = new PieDataSet(yValues, "CHIFFRE D'AFFAIRE");
                        dataSet.setSliceSpace(3f);
                        dataSet.setSelectionShift(5f);
                        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                        PieData data = new PieData(dataSet);
                        data.setValueTextSize(10f);
                        data.setValueTextColor(Color.YELLOW);

                        piechart.setData(data);
                    }

                    Toast.makeText(ChartItent.this,"Connection Success code " +response.code(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ChartItent.this,"request error code " +response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Chiffre>> call, Throwable t) {
                Toast.makeText(ChartItent.this,"Connection failed " +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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