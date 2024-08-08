package com.gurulabs.notificationsender.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.gurulabs.notificationsender.MainActivity
import com.gurulabs.notificationsender.R
import com.gurulabs.notificationsender.adapter.NoteAdapter
import com.gurulabs.notificationsender.databinding.FragmentNewNoteBinding
import com.gurulabs.notificationsender.model.Note
import com.gurulabs.notificationsender.viewmodel.NoteViewModel
import com.google.android.material.appbar.MaterialToolbar


class NewNoteFragment : Fragment(R.layout.fragment_new_note), MenuProvider {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var notesAdapter: NoteAdapter


    private lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        toolbar.title = ""
        // Set the Toolbar as the action bar
        (activity as MainActivity).setSupportActionBar(toolbar)

         val menuHost: MenuHost = requireActivity()
         menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)


        notesViewModel = (activity as MainActivity).noteViewModel
        this.view = view


    }

    private fun saveNote(view: View) {
        val enteredTitle = binding.noteTitleEditText.text.toString().trim()
        val enteredDesc = binding.noteDescEditText.text.toString().trim()

        if (enteredTitle.isNotEmpty()) {

            val note = Note(0, enteredTitle, enteredDesc)
            notesViewModel.addNote(note)

            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)

        } else {
            Toast.makeText(context, "Enter Title", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.new_note_menu, menu)




    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.save_note_menu-> {
                saveNote(view)
                true
            }


            else -> {false}
        }

    }

}