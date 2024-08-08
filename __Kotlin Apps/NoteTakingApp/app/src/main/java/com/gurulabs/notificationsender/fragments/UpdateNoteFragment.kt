package com.gurulabs.notificationsender.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.gurulabs.notificationsender.MainActivity

import com.gurulabs.notificationsender.R
import com.gurulabs.notificationsender.adapter.NoteAdapter
import com.gurulabs.notificationsender.databinding.FragmentUpdateNoteBinding
import com.gurulabs.notificationsender.model.Note
import com.gurulabs.notificationsender.viewmodel.NoteViewModel
import com.google.android.material.appbar.MaterialToolbar

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note), MenuProvider {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var notesAdapter: NoteAdapter


    private lateinit var currentNote: Note
    private val args: UpdateNoteFragmentArgs by navArgs()

    private lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        toolbar.title = ""
        // Set the Toolbar as the action bar
        (activity as MainActivity).setSupportActionBar(toolbar)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)


        notesViewModel = (activity as MainActivity).noteViewModel
        this.view = view
        currentNote = args.note!!

        binding.apply {
            noteTitleEditText.setText(currentNote.noteTitle)
            noteDescEditText.setText(currentNote.noteBody)

            binding.fabDone.setOnClickListener(View.OnClickListener {

                updateNote(view, currentNote)

            })


        }


    }

    private fun updateNote(view: View, currentNote: Note) {
        val enteredTitle = binding.noteTitleEditText.text.toString().trim()
        val enteredDesc = binding.noteDescEditText.text.toString().trim()

        if (enteredTitle.isNotEmpty()) {

            val note = Note(currentNote.id, enteredTitle, enteredDesc)
            notesViewModel.updateNote(note)

            Toast.makeText(context, "Note Updated", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment, false)

        } else {
            Toast.makeText(context, "Enter Title", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.update_note_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.delete_note_menu -> {
                deleteNote(view)
                true
            }


            else -> {
                false
            }
        }

    }

    private fun deleteNote(view: View) {

        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("You are sure?")
            setPositiveButton("Delete") { _, _ ->
                notesViewModel.deleteNote(currentNote)
                view.findNavController().popBackStack(R.id.homeFragment, false)
                Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show()

            }
            setNegativeButton("Cancel",null)

        }.create().show()

    }

}