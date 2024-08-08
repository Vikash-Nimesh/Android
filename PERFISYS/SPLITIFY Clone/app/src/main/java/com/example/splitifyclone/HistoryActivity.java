package com.example.splitifyclone;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<Expense> expenseArrayList;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        expenseArrayList = new ArrayList<>();
        expenseArrayList.add(new Expense("Clothing",20000,"12-04-2024 11:01"));
        expenseArrayList.add(new Expense("Bike Repair  ",15000,"11-04-2024 11:01"));
        expenseArrayList.add(new Expense("Buy A.C.",31000,"19-03-2024 11:01"));
        expenseArrayList.add(new Expense("Groceries",1000,"13-04-2024 11:01"));


        recyclerView = findViewById(R.id.recyclerView);


        ExpenseAdapter expenseAdapter = new ExpenseAdapter(expenseArrayList,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(expenseAdapter);

    }
}