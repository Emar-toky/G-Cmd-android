package com.example.homedashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChiffreAdapter extends RecyclerView.Adapter<ChiffreAdapter.ViewHolder> {
    private ArrayList<Chiffre> chiffres = new ArrayList<>();
    private Context context;
    public ChiffreAdapter(Context context, ArrayList<Chiffre> chiffres){
        this.chiffres= chiffres;
        this.context=context;
    }
    @NonNull
    @Override
    public ChiffreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chiffre_affaire_list_item,parent,false);

        return new ChiffreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiffreAdapter.ViewHolder holder, int position) {
        final Chiffre movie = chiffres.get(position);
        holder.numero_cli.setText("CLI" + movie.getId());
        holder.nom_du_cli.setText(movie.getNomClient());
        holder.chiffre_affaire.setText(""+movie.getChiffreAffaire());
    }

    @Override
    public int getItemCount() {
        return chiffres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //int client_id;
        //String clientId=String.valueOf(client_id);
        TextView numero_cli,nom_du_cli,chiffre_affaire;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numero_cli=itemView.findViewById(R.id.numero_cli);
            nom_du_cli= itemView.findViewById(R.id.nom_du_cli);
            chiffre_affaire= itemView.findViewById(R.id.chiffre_affaire);
        }
    }
}
