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

public class FactureTotal_Adapter extends RecyclerView.Adapter<FactureTotal_Adapter.ViewHolder> {
    private ArrayList<FactureTotal> factureTotals = new ArrayList<>();
    private Context context;
    public FactureTotal_Adapter(Context context, ArrayList<FactureTotal> factureTotals){
        this.factureTotals= factureTotals;
        this.context=context;
    }


    @NonNull
    @Override
    public FactureTotal_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facture_d_un_client_list_item,parent,false);

        return new FactureTotal_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactureTotal_Adapter.ViewHolder holder, int position) {
        final FactureTotal movie = factureTotals.get(position);
        holder.totalbe_produit.setText(""+movie.getTotal());
    }

    @Override
    public int getItemCount() {
        return factureTotals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView totalbe_produit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
