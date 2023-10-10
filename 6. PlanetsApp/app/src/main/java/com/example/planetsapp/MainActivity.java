package com.example.planetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);


        ArrayList<Planet> items= new ArrayList<>();
        items.add(new Planet("Earth","1",R.drawable.earth));
        items.add(new Planet("Mars","5",R.drawable.mars));



        PlanetAdapter planetAdapter = new PlanetAdapter(items,this);
        listView.setAdapter(planetAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, planetAdapter.getItem(i).planetName, Toast.LENGTH_SHORT).show();
            }
        });



    }
}