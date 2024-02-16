package com.example.notetakingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notetakingapp.MainActivity

import com.example.notetakingapp.R
import com.example.notetakingapp.adapter.NoteAdapter
import com.example.notetakingapp.databinding.FragmentHomeBinding
import com.example.notetakingapp.model.Note
import com.example.notetakingapp.viewmodel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener,
    MenuProvider {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var notesAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //homeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost:MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)



        notesViewModel = (activity as MainActivity).noteViewModel


        setUpRecyclerView()
        binding.fabAdd.setOnClickListener(View.OnClickListener {

            it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)

        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNote(newText)

        }
        return true
    }


    private fun setUpRecyclerView() {
        notesAdapter = NoteAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = notesAdapter

        }

        activity.let {

            notesViewModel.getAllNotes().observe(viewLifecycleOwner) {

                    note ->
                notesAdapter.differ.submitList(note)
               // updateIU(note)


            }
        }


    }

    /*private fun updateIU(note: List<Note>?): List<Note>? {

        return true
    }*/

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch = menu.findItem(R.id.search_menu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

    fun searchNote(searchText: String?) {
        val search_text = "%$searchText"
        notesViewModel.getSearchedNote(search_text).observe(this) {

                note ->
            notesAdapter.differ.submitList(note)

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}