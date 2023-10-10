package com.example.frenchteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button blackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blackBtn = findViewById(R.id.blackBtn);
        blackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int buttonids = view.getId();

        if (buttonids == R.id.blackBtn){
            playSound(R.raw.black);
        }

    }


    public void playSound(int id){
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),id);
        mediaPlayer.start();
    }
}