package com.example.frenchteacherapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun sayColorName(view: View){
        val clickedButton : Button = view as Button

        val resourceId = resources.getIdentifier(clickedButton.tag.toString(), "raw", packageName)
        Log.i(TAG,clickedButton.tag.toString())
        val mediaPlayer = MediaPlayer.create(this, resourceId)

        mediaPlayer.start()

    }


}

