package com.example.contactmanagerapp.viewmodel


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactmanagerapp.room.User
import com.example.contactmanagerapp.room.UserRepository

class UserViewModel (val repository: UserRepository) : ViewModel(), Observable {

    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete:User

    @Bindable
    val inputName = MutableLiveData<String>()


}