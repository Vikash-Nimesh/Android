package com.example.hashdash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hashdash.BottomNav.BottomNavActivity;

public class HomeActivity extends AppCompatActivity {

    Button openRazorPayBtn, openBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        openRazorPayBtn = findViewById(R.id.razor_pay_btn);
        openBottomNav = findViewById(R.id.bottom_nav_btn);

        openRazorPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RazorPayActivity.class);
                startActivity(intent);

            }
        });

        openBottomNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BottomNavActivity.class);
                startActivity(intent);

            }
        });


    }
}