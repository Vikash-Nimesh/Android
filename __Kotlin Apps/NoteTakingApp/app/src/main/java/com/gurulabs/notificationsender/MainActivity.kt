package com.gurulabs.notificationsender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gurulabs.notificationsender.database.NoteDatabase
import com.gurulabs.notificationsender.databinding.ActivityMainBinding
import com.gurulabs.notificationsender.respository.NoteRepository
import com.gurulabs.notificationsender.viewmodel.NoteViewModel
import com.gurulabs.notificationsender.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()

    }

    private fun setUpViewModel(){
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application,noteRepository)
        noteViewModel = ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)


    }
}