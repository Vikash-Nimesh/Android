package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val luckyNumberText:TextView = findViewById(R.id.lucky_number_txt)

        val randomNumber = generateRandomNumber()
        val username = receiveUserName()

        Toast.makeText(this,"My name is " + username,Toast.LENGTH_LONG).show()

        luckyNumberText.setText("Hello $username your Lucky Number is $randomNumber")

        val sharebutton:Button = findViewById(R.id.share_btn)

        sharebutton.setOnClickListener(View.OnClickListener {

            val shareIntent: Intent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain")


            shareIntent.putExtra(Intent.EXTRA_SUBJECT,username + " got lucky today!");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "His lucky number is: "+randomNumber);

            startActivity(Intent.createChooser(shareIntent,"Choose a Platform"));



        })

    }

    fun receiveUserName(): String? {
        var username = intent.getStringExtra("name")
        return username

    }

    fun generateRandomNumber():Int{
        val random = Random.nextInt(0,1000)
        return random


    }
}