package com.example.vaccinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv : RecyclerView = findViewById(R.id.RV)
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        //creating data
        val vaccineList : ArrayList<VaccineModel> = ArrayList()
        val v1 = VaccineModel("Vikas",R.drawable.ic_launcher_background)
        val v2 = VaccineModel("is",R.drawable.ic_launcher_background)
        val v3 = VaccineModel("here",R.drawable.ic_launcher_background)
        val v4 = VaccineModel("r",R.drawable.ic_launcher_background)
        val v5 = VaccineModel("u",R.drawable.ic_launcher_background)
        vaccineList.add(v1)
        vaccineList.add(v2)
        vaccineList.add(v3)
        vaccineList.add(v4)
        vaccineList.add(v5)

        //initializing Adapter
        val myAdapter:VaccineAdapter = VaccineAdapter(vaccineList)
        rv.adapter = myAdapter



    }
}