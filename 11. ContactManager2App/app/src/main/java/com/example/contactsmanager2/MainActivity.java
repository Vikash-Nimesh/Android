package com.example.contactsmanager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactsmanager2.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ContactDataAdapter contactDataAdapter;
    ArrayList<Contact> contactArrayList;




    //Binding
    MainActivityClickHandlers mainActivityClickHandlers;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Data Binding
        activityMainBinding  = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityClickHandlers = new MainActivityClickHandlers(this);

        activityMainBinding.setClickHandler(mainActivityClickHandlers);


        //Recycle View

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        contactDataAdapter = new ContactDataAdapter(contactArrayList,this);
        recyclerView.setAdapter(contactDataAdapter);




    }



    public class MainActivityClickHandlers{
        Context context;

        public MainActivityClickHandlers(Context context) {
            this.context = context;
        }


        public void onFABClicked(View view){

            Toast.makeText(context, "oho ji", Toast.LENGTH_SHORT).show();
        }
    }
}