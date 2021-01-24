package com.example.homedashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {
    TextView client_nom,client_id;
    private ArrayList<Client> clients = new ArrayList<>();
    private Context context;

    public ClientAdapter(Context context, ArrayList<Client> clients){
        this.clients= clients;
        this.context=context;
    }

    @NonNull
    @Override
    public ClientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_list_item,parent,false);

        return new ClientAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Client movie = clients.get(position);
        holder.client_nom.setText(movie.getNomClient());
        holder.client_id.setText("CLI" +movie.getId());
        holder.clientClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mipasse donnée am intent update_client_intent
                Intent goDetail = new Intent(context, update_client_intent.class);
                goDetail.putExtra("nomClient", movie.getNomClient());
                goDetail.putExtra("idClient",movie.getId());
                context.startActivity(goDetail);
            }
        });
        holder.menu_option_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }

    public void showPopup(View view){
        PopupMenu popupMenu = new PopupMenu(context,view);
        popupMenu.inflate(R.menu.menu_options_client);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.delete_client:
                        Toast.makeText(context, "Delete cliked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.update_client:
                        Toast.makeText(context, "Update cliked", Toast.LENGTH_SHORT).show();

                        break;
                }

                return false;
            }
        });

        popupMenu.show();

    }


    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //int client_id;
        //String clientId=String.valueOf(client_id);
        TextView client_nom,client_id;
        LinearLayout clientClick;
        ImageView menu_option_client;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clientClick=itemView.findViewById(R.id.clientClick);
            client_nom= itemView.findViewById(R.id.client_nom);
            client_id= itemView.findViewById(R.id.client_id);
            menu_option_client = itemView.findViewById(R.id.menu_option_client);
        }
    }
}
