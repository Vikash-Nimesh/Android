package com.example.contactsmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.contactsmanagerapp.db.Contacts;
import com.example.contactsmanagerapp.db.ContractsRoomDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextInputEditText enterNameField, enterEmailField;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterNameField = findViewById(R.id.enterNameField);
        enterEmailField = findViewById(R.id.enterEmailField);

        button = findViewById(R.id.button);

        showDataFromDatabase(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredName = enterNameField.getText().toString();
                String enteredEmail = enterEmailField.getText().toString();

                insertIntoDatabase(MainActivity.this, enteredName, enteredEmail);

            }
        });


    }

    void insertIntoDatabase(Context context, String enterNameField, String enterEmailField){
        Thread thread  = new Thread(new Runnable() {
            @Override
            public void run() {
                Contacts contact = new Contacts();
                contact.setContact_name(enterNameField);
                contact.setContact_email(enterEmailField);
                ContractsRoomDatabase.getInstance(context).contactsDAO().insertContact(contact);

            }
        });
        thread.start();
    }

    void showDataFromDatabase(Context context){
        Thread thread  = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Contacts> contactsList =  ContractsRoomDatabase.getInstance(context).contactsDAO().getAllContacts();
                Log.i("contactsList",contactsList.toString());

            }
        });
        thread.start();
    }


}