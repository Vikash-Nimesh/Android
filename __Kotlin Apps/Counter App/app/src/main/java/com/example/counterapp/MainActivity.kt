package com.example.counterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var initialCounter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView : TextView = findViewById(R.id.textView)
        val button : Button = findViewById(R.id.button)

        button.setOnClickListener(){

            textView.setText(""+increaseCounter())

        }



    }

    fun increaseCounter() : Int{
        return  initialCounter++

    }
}