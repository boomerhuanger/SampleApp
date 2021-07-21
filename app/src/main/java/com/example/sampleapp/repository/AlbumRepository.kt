package com.example.sampleapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.models.Album
import com.example.sampleapp.models.User
import com.example.sampleapp.network.UserService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumRepository {
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
    private var service: UserService = retrofit.create(UserService::class.java)
    private var albumsList = MutableLiveData<List<Album>>()
    fun getAlbums() {
        service.photos().enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                albumsList.value = response.body()
                Log.i("Album Data", albumsList.value.toString())
            }
            override fun onFailure(call: Call<List<Album>>, t: Throwable){
                albumsList.value = null
            }
        })
    }
    fun getAlbumsLiveData()  = albumsList
}
