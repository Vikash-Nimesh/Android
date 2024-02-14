package com.example.greetingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et :EditText = findViewById(R.id.editTextText)
        val button :Button = findViewById(R.id.button)


        button.setOnClickListener(){
            val etText : String = et.text.toString()
            
            Toast.makeText(this, "Hello $etText",Toast.LENGTH_SHORT).show()

        }


    }
}