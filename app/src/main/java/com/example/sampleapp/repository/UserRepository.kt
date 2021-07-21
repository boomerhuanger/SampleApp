package com.example.sampleapp.repository

import com.example.sampleapp.models.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.MutableLiveData;
import com.example.sampleapp.network.UserService


class UserRepository {
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
    private var service: UserService = retrofit.create(UserService::class.java)
    private var usersList = MutableLiveData<List<User>>()
    fun getUsers() {
        service.users().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                usersList.value = response.body()
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable){
                usersList.value = null
            }
        })
    }
    fun getUsersLiveData()  = usersList
}
