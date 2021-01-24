package com.example.homedashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class facture_d_un_clientAdapter extends RecyclerView.Adapter<facture_d_un_clientAdapter.ViewHolder> {
    private ArrayList<Facture> factures = new ArrayList<>();
    private ArrayList<FactureTotal> factureTotals = new ArrayList<>();
    private Context context;
    public facture_d_un_clientAdapter(Context context, ArrayList<Facture> factures){
        this.factures= factures;
        this.context=context;
    }

    @NonNull
    @Override
    public facture_d_un_clientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facture_d_un_client_list_item,parent,false);

        return new facture_d_un_clientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull facture_d_un_clientAdapter.ViewHolder holder, int position) {
        final Facture movie = factures.get(position);
        holder.designation_produit.setText(movie.getDesignation());
        holder.pu_produit.setText(""+movie.getPu());
        holder.quantite_produit.setText(""+movie.getQuantite());
        holder.total_produit.setText(""+movie.getTotalKely());
    }

    @Override
    public int getItemCount() {
        return factures.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView designation_produit,pu_produit,quantite_produit,total_produit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            designation_produit = itemView.findViewById(R.id.designation_produit);
            pu_produit = itemView.findViewById(R.id.pu_produit);
            quantite_produit = itemView.findViewById(R.id.quantite_produit);
            total_produit = itemView.findViewById(R.id.total_produit);
        }
    }
}
