package com.example.topgames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopGamesAdapter extends RecyclerView.Adapter<TopGamesAdapter.ViewHolder> {

    ArrayList<TopGames> items;
    Context context;

    public TopGamesAdapter(ArrayList<TopGames> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopGames topGames = items.get(position);

        holder.textviewCard.setText(topGames.getGameName());
        holder.cardviewImg.setImageResource(topGames.getGameImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, topGames.getGameName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textviewCard;
        ImageView cardviewImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textviewCard = itemView.findViewById(R.id.textviewCard);
            cardviewImg = itemView.findViewById(R.id.cardviewImg);
        }
    }
}
