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

public class FactureAdapter extends RecyclerView.Adapter<FactureAdapter.ViewHolder> {
    private ArrayList<Client> clients = new ArrayList<>();
    private Context context;

    public FactureAdapter(Context context, ArrayList<Client> clients){
        this.clients= clients;
        this.context=context;
    }

    @NonNull
    @Override
    public FactureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_facture_list_item,parent,false);

        return new FactureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactureAdapter.ViewHolder holder, int position) {
        final Client movie = clients.get(position);
        holder.client_nom.setText(movie.getNomClient());
        holder.client_id.setText("CLI" +movie.getId());
        holder.clientClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDetail = new Intent(context, facture_d_un_client.class);
                goDetail.putExtra("nomClient", movie.getNomClient());
                goDetail.putExtra("idClient",movie.getId());
                context.startActivity(goDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView client_nom,client_id;
        LinearLayout clientClick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clientClick=itemView.findViewById(R.id.clientClick);
            client_nom= itemView.findViewById(R.id.client_nom);
            client_id= itemView.findViewById(R.id.client_id);
        }
    }
}
