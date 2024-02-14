package com.example.hashdash.BottomNav;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.hashdash.R;

public class BottomNavActivity extends AppCompatActivity {

    LinearLayout home_LL,points_LL,order_LL,profile_LL;
    ImageView homeIcon,pointsIcon,ordersIcon,profileIcon;
    TextView homeText,pointsText,ordersText,profileText;

    int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();

        }

        home_LL = findViewById(R.id.home_LL);
        points_LL = findViewById(R.id.points_LL);
        order_LL = findViewById(R.id.order_LL);
        profile_LL = findViewById(R.id.profile_LL);

        homeIcon = findViewById(R.id.homeIcon);
        pointsIcon = findViewById(R.id.pointsIcon);
        ordersIcon = findViewById(R.id.ordersIcon);
        profileIcon = findViewById(R.id.profileIcon);

        homeText = findViewById(R.id.homeText);
        pointsText = findViewById(R.id.pointsText);
        ordersText = findViewById(R.id.ordersText);
        profileText = findViewById(R.id.profileText);


        home_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedTab != 1){

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            HomeFragment.class,null).commit();

                    //unselect the other tabs
                    points_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));
                    order_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));
                    profile_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));


                    pointsIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    ordersIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    profileIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));


                    pointsText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    ordersText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    profileText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));

                    //select the home tab

                    home_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma));
                    homeIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.black));
                    homeText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.black));


                    selectedTab = 1;

                }

            }
        });


        points_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(selectedTab != 2){

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            PointsFragment.class,null).commit();


                    //unselect the other tabs
                    home_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));
                    order_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));
                    profile_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));


                    homeIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    ordersIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    profileIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));


                    homeText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    ordersText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    profileText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));

                    //select the home tab

                    points_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma));
                    pointsIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.black));
                    pointsText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.black));


                    selectedTab = 2;

                }


            }
        });

        order_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(selectedTab != 3){

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            OrdersFragment.class,null).commit();


                    //unselect the other tabs
                    home_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));
                    points_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));
                    profile_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));


                    homeIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    pointsIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    profileIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));


                    homeText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    pointsText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    profileText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));

                    //select the home tab

                    order_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma));
                    ordersIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.black));
                    ordersText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.black));



                    selectedTab = 3;

                }
            }
        });

        profile_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab != 4){

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            ProfileFragment.class,null).commit();

                    //unselect the other tabs
                    home_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));
                    points_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));
                    order_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma_transparent));


                    homeIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    pointsIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    ordersIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.white));


                    homeText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    pointsText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));
                    ordersText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.white));

                    //select the home tab

                    profile_LL.setBackground(ContextCompat.getDrawable(BottomNavActivity.this,R.drawable.item_selector_figma));
                    profileIcon.setColorFilter(ContextCompat.getColor(BottomNavActivity.this, R.color.black));
                    profileText.setTextColor(ContextCompat.getColor(BottomNavActivity.this, R.color.black));


                    selectedTab = 4;

                }


            }
        });

    }


}