package com.example.homedashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommandeAdapter extends RecyclerView.Adapter<CommandeAdapter.ViewHolder> {
    private ArrayList<Cmd> cmds = new ArrayList<>();
    ArrayList<Client> clients = new ArrayList<>();
    private Context context;

    public CommandeAdapter(Context context, ArrayList<Cmd> cmds){
        this.cmds= cmds;
        this.context=context;
    }

    @NonNull
    @Override
    public CommandeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commande_list_item,parent,false);

        return new CommandeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandeAdapter.ViewHolder holder, int position) {
        final Cmd movie = cmds.get(position);
        holder.commande_id.setText(""+movie.getId());
        holder.cmd_quantite.setText(""+movie.getQuantite());
        holder.client_id.setText(movie.getNomClient());
        holder.produit_id.setText(movie.getDesignation());
        holder.CommandeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDetail = new Intent(context, update_commande_intent.class);
                goDetail.putExtra("idCommande", movie.getId());
                goDetail.putExtra("quantite", movie.getQuantite());
                context.startActivity(goDetail);
            }
        });

    }


    @Override
    public int getItemCount() {
        return cmds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView commande_id,produit_id,client_id,cmd_quantite;
        LinearLayout CommandeClick;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commande_id = itemView.findViewById(R.id.commande_id);
            produit_id = itemView.findViewById(R.id.produit_id);
            client_id = itemView.findViewById(R.id.client_id);
            cmd_quantite = itemView.findViewById(R.id.cmd_quantite);
            CommandeClick = itemView.findViewById(R.id.CommandeClick);
        }
    }
}
