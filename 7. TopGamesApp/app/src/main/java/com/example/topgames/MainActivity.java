package com.example.topgames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TopGames> gamesList;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gamesList = new ArrayList<>();
        gamesList.add(new TopGames("Horizon Chase",R.drawable.card1));
        gamesList.add(new TopGames("PUBG",R.drawable.card2));
        gamesList.add(new TopGames("Head Ball 2",R.drawable.card3));

        gamesList.add(new TopGames("Fifa 2022",R.drawable.card5));
        gamesList.add(new TopGames("Fortnite",R.drawable.card6));
        gamesList.add(new TopGames("Hooked on You",R.drawable.card4));

        recyclerView = findViewById(R.id.recyclerView);


        TopGamesAdapter topGamesAdapter = new TopGamesAdapter(gamesList,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(topGamesAdapter);



    }
}