package com.example.sampleapp

import android.util.Log
import com.example.helloworld.User
import com.example.helloworld.UserAdapter
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.sql.DriverManager.println

class UserRepository {
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
    private var service: UserService = retrofit.create(UserService::class.java)
    private var users = MutableLiveData<List<User>>()

    fun getUsers() {

        //val userAdapter = UserAdapter(context, R.layout.user_info, users)

        // Calling '/api/users/2'
        service.users().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("getting data", "getting data")
                if (!response.isSuccessful) {
                    return
                }
                Log.e("debug", response.body()!!.toString())
//                Log.i("Response", call.request().toString())
//                val apiResponse = response.body()!!
//
//                //users = apiResponse;
//                //users.postValue(apiResponse)
//                users.value = apiResponse
//
//                //Log.i("Users size", "List of users is" + userViewModel?.users?.size)
//                Log.i("All users", users.value.toString())
//
//                //Log.i("response", apiResponse.toString());
//                Log.i("my tag", "testing message")

                //val userInfo = binding.userList
                //userInfo.adapter = userAdapter

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable){
                println("debug --> $t")
                Log.d("Network Error", "Network Error :: " + t.localizedMessage)
            }
        })
    }

    fun getUsersLiveData() : LiveData<List<User>> {
        return users;
    }
}