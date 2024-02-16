package com.example.notetakingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.databinding.NoteLayoutBinding
import com.example.notetakingapp.fragments.HomeFragmentDirections
import com.example.notetakingapp.model.Note
import kotlin.random.Random


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder (val itemBinding:NoteLayoutBinding)
        :RecyclerView.ViewHolder(itemBinding.root)

        private val differCallBack = object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id && oldItem.noteTitle == newItem.noteTitle
                        && oldItem.noteBody == newItem.noteBody
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }


        }

        val differ = AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(NoteLayoutBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.tvNoteTitle.text = currentNote.noteTitle
        holder.itemBinding.tvNoteDesc.text = currentNote.noteBody

        holder.itemView.setOnClickListener(View.OnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            it.findNavController().navigate(direction)

        })


    }


}