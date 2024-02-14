package com.mo.counterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    MainActivityViewModel mainActivityViewModel;

    private TextView textView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);



        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);


        textView.setText(String.valueOf(mainActivityViewModel.getInitialCounter()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textView.setText(String.valueOf(mainActivityViewModel.increaseCounter()));
            }
        });





    }

}