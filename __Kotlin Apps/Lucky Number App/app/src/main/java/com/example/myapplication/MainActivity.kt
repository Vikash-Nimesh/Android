package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val edittext: EditText = findViewById(R.id.edit_text)
        val btn: Button = findViewById(R.id.btn)

        btn.setOnClickListener(View.OnClickListener {
            var editTextString = edittext.text.toString()

            Toast.makeText(this,editTextString,Toast.LENGTH_LONG).show()

            val intent : Intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("name",editTextString)
            startActivity(intent)


        })

    }
}