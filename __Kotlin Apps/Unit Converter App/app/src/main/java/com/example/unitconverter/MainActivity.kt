package com.example.unitconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText:EditText = findViewById(R.id.editText)
        val button:Button = findViewById(R.id.button)
        val textView:TextView = findViewById(R.id.textView)

        button.setOnClickListener(){

            val editTextDouble = editText.text.toString().toDouble()

            textView.setText(""+makeConversion(editTextDouble))



        }



    }

    fun makeConversion(kilos:Double): Double{


        // 1 kilo = 2.20462 pounds
        return kilos * 2.20462
    }
}