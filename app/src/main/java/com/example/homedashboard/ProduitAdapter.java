package com.example.homedashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.ViewHolder>  {
    TextView produit_nom,produit_id;
    TextView produit_prix;
    int prix;
    private ArrayList<Produit> produits = new ArrayList<>();
    private Context context;

    public ProduitAdapter(Context context, ArrayList<Produit> produits){
        this.produits= produits;
        this.context=context;
    }

    @NonNull
    @Override
    public ProduitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produit_list_item,parent,false);

        return new ProduitAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitAdapter.ViewHolder holder, int position) {
        final Produit movie = produits.get(position);
        //affichage donnee am produit_item
        holder.produit_id.setText("PRD" +movie.getId());
        holder.produit_nom.setText(movie.getDesignation());
        holder.produit_prix.setText("" +movie.getPu() + " Ar");
        //ref miclick an le produitclik io dia manao transfere donne am ecran update_produit_intent
        holder.produitClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDetail = new Intent(context, update_produit_intent.class);
                goDetail.putExtra("id_produit", movie.getId());
                goDetail.putExtra("designation",movie.getDesignation());
                goDetail.putExtra("prix", movie.getPu());
                context.startActivity(goDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView produit_nom,produit_id;
        TextView produit_prix;
        LinearLayout produitClick;
        //String clientId=String.valueOf(client_id);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            produit_id=itemView.findViewById(R.id.produit_id);
            produit_nom= itemView.findViewById(R.id.produit_nom);
            produit_prix= itemView.findViewById(R.id.produit_prix);
            produitClick= itemView.findViewById(R.id.produitClick);
            //int prix= Integer.parseInt(produit_prix.toString());
            //String prie=String.valueOf(prix);
        }
    }
}
