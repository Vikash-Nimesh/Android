package com.example.marghclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.volcaniccoder.bottomify.BottomifyNavigationView;
import com.volcaniccoder.bottomify.OnNavigationItemChangeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();

        }


        BottomifyNavigationView bottomify = findViewById(R.id.bottomify_nav);
        bottomify.setOnNavigationItemChangedListener(new OnNavigationItemChangeListener() {
            @Override
            public void onNavigationItemChanged(@NonNull BottomifyNavigationView.NavigationItem navigationItem) {

                if(navigationItem.getPosition() == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            SavedFragment.class,null).commit();
                } else if (navigationItem.getPosition() == 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            ProfileFragment.class,null).commit();

                }
                else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new HomeFragment()).commit();
                }

            }
        });

    }
}