package com.example.contactsmanager2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddNewConctactActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_conctact);
    }




    public class AddNewContactClickHandler {

        Context context;

        public AddNewContactClickHandler(Context context) {
            this.context = context;
        }


        public void onSubmitClick(View view){

            Toast.makeText(context, "hogya ji", Toast.LENGTH_SHORT).show();
        }
    }
}