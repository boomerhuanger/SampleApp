package com.example.sampleapp

import com.example.helloworld.Album
import com.example.helloworld.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/users/")
    fun users() : Call<List<User>>

    @GET("/photos/")
    fun photos() : Call<List<Album>>
}