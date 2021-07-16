package com.example.sampleapp

import androidx.lifecycle.ViewModel
import com.example.helloworld.User
import java.util.*

class UserViewModel : ViewModel() {
    val users = ArrayList<User?>()
}