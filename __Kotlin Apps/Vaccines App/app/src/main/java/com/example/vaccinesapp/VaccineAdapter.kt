package com.example.vaccinesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class VaccineAdapter(val vaccineList:ArrayList<VaccineModel>)
        :RecyclerView.Adapter<VaccineAdapter.myViewHolder>() {


    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var img : ImageView
        lateinit var name : TextView

        init {
            img = itemView.findViewById(R.id.imageView)
            name = itemView.findViewById(R.id.textView)
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_list_item,parent,false)
        return myViewHolder(v)
    }

    override fun getItemCount(): Int {
        return vaccineList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.name.setText(vaccineList[position].name)
        holder.img.setImageResource(vaccineList[position].img)

        holder.img.setOnClickListener(View.OnClickListener {

            Toast.makeText(holder.itemView.context,"You clicked on : ${vaccineList[position].name}",Toast.LENGTH_SHORT).show()


        })

    }
}