package com.example.sharedprefapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

     lateinit var textView:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button : Button = findViewById(R.id.button)
        val editText : EditText = findViewById(R.id.editTextText)

        textView = findViewById(R.id.textView)

        displayDataFromSharedPref()


        button.setOnClickListener(View.OnClickListener {

            val enteredText = editText.text.toString()
            saveToSharedPref(enteredText)

        })




    }
    private fun saveToSharedPref (enteredText:String){
        val sharedPreferences:SharedPreferences = getSharedPreferences("mySharedPref", MODE_PRIVATE)
        val sharedPrefEditor:SharedPreferences.Editor = sharedPreferences.edit()
        sharedPrefEditor.putString("name",enteredText).apply()
    }

    private fun displayDataFromSharedPref (){
        val sharedPreferences:SharedPreferences = getSharedPreferences("mySharedPref", MODE_PRIVATE)
        val name = sharedPreferences.getString("name","")
        textView.text = name
    }
}

