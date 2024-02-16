package com.example.corontinesapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.withCreated
import com.example.corontinesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var counter:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.apply {

            button.setOnClickListener(View.OnClickListener {
                text1.text = counter++.toString()


            })


            button2.setOnClickListener(View.OnClickListener {

                //with using coroutines
                //Dispatchers help coroutines in deciding the thread on which the work has to be done.

                CoroutineScope(Dispatchers.IO).launch {


                    //2
                    //runDownloadBigFileFromServer()

                    //3
                    //Switching between coroutines
                    runDownloadBigAndShowingDataIntoMainThread()

                }


                //1
                /* //without using coroutines
                runDownloadBigFileFromServer()*/





            })

        }


    }


    private fun runDownloadBigFileFromServer() {

        for (i in 1..100000){
            Log.i("TAGY","Downloading $i in ${Thread.currentThread().name}")
        }

    }



    
    private suspend fun runDownloadBigAndShowingDataIntoMainThread() {

        for (i in 1..100000){
            Log.i("TAGY","Downloading $i in ${Thread.currentThread().name}")

            withCreated {
                binding.text2.text = "$i in ${Thread.currentThread().name}"

            }
        }

    }
}