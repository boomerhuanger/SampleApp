package com.example.helloworld

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.R
import com.example.sampleapp.UserService
import com.example.sampleapp.UserViewModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class UserFragment(private val mContext: Context) : Fragment() {
    private var view1: View? = null
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private val albumFragment : String = "Album Fragment"
    private var fragment: Fragment? = null
    private var userViewModel : UserViewModel? = null;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

        if (userViewModel == null) {
            userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        }

        // Inflate the layout for this fragment
        view1 = inflater.inflate(R.layout.user_list, container, false)
        getUsers()

        Log.d("Users list", "The length of the list of Users is" + userViewModel!!.users.size)
        val userInfo = view1!!.findViewById<ListView>(R.id.userList)
        userInfo.onItemClickListener = OnItemClickListener { parent, view1, position, id ->
            Log.i("Touch", "Touched the screen")
            Log.i("Position in list", position.toString())
            val fm = activity?.supportFragmentManager
            fragment = fm!!.findFragmentByTag(albumFragment)
            if (fragment == null) {
                val ft = fm.beginTransaction()
                fragment = AlbumFragment(position + 1)
                ft.add(R.id.container, fragment as AlbumFragment, albumFragment)
                ft.addToBackStack(albumFragment)
                ft.commit()
                Log.i("Position is ", position.toString())
            }
        }
        Log.d("User adapter set", "User adapter set")
        return view1;
    }

    private fun getUsers() {
        val httpClient = OkHttpClient.Builder()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        val service = retrofit.create<UserService>(UserService::class.java)
        val userAdapter = UserAdapter(context, R.layout.user_info, userViewModel?.users)

        // Calling '/api/users/2'
        service.users().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (!response.isSuccessful) {
                    return
                }
                Log.i("Response", call.request().toString())
                val apiResponse = response.body()!!

                //users = apiResponse;
                for (user in apiResponse) {
                    userViewModel?.users?.add(user)
                }
                Log.i("Users size", "List of users is" + userViewModel?.users?.size)
                Log.i("All users", userViewModel?.users.toString())

                //Log.i("response", apiResponse.toString());
                Log.i("my tag", "testing message")

                val userInfo = view1!!.findViewById<ListView>(R.id.userList)
                userInfo.adapter = userAdapter
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Network Error", "Network Error :: " + t.localizedMessage)
            }
        })
    }
}