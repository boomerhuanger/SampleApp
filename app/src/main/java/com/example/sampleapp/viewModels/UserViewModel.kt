package com.example.sampleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.models.User
import com.example.sampleapp.repository.UserRepository

class UserViewModel : ViewModel() {
    private lateinit var userRepository : UserRepository
    private lateinit var usersLiveData : LiveData<List<User>>

    fun init() {
        userRepository = UserRepository()
        getUsers()
        usersLiveData = userRepository.getUsersLiveData()
    }

    fun getUsers() {
        userRepository.getUsers()
    }

    fun getUsersLiveData() : LiveData<List<User>> {
        return usersLiveData
    }
}