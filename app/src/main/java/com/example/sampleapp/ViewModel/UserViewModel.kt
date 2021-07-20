package com.example.sampleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloworld.User
import java.util.*

class UserViewModel : ViewModel() {
    private lateinit var userRepository : UserRepository
    private lateinit var usersLiveData : LiveData<List<User>>

    fun init() {
        userRepository = UserRepository()
       // userRepository.getUsersLiveData()
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