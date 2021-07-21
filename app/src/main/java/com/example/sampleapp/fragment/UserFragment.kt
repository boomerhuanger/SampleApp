package com.example.helloworld

import android.content.Context
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.R
import com.example.sampleapp.UserViewModel
import com.example.sampleapp.databinding.UserListBinding
import androidx.lifecycle.Observer
import java.sql.DriverManager.println
import java.util.*

class UserFragment(private val mContext: Context) : Fragment() {
    private var view1: View? = null
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private val albumFragment: String = "Album Fragment"
    private var fragment: Fragment? = null
    private lateinit var userViewModel: UserViewModel

    //private var userViewModel : UserViewModel
    private var userAlbumAdapter: UserAdapter? = null;
    private lateinit var binding: UserListBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.init()

        binding = DataBindingUtil.inflate(inflater, R.layout.user_list, container, false)
        userViewModel.getUsersLiveData().observe(viewLifecycleOwner, Observer { data ->
            userAlbumAdapter = UserAdapter(context, R.layout.user_info, data)
            binding.userList.adapter = userAlbumAdapter
          println("debug -- > ${data.toString()}")
        })

        Log.d("userAlbumAdapter", userAlbumAdapter.toString())
        val userInfo = binding.userList
        userInfo.adapter = userAlbumAdapter

        // Inflate the layout for this fragment
        //view1 = inflater.inflate(R.layout.user_list, container, false)
        //getUsers()

        //Log.d("Users list", "The length of the list of Users is" + userViewModel!!.getUsersLiveData().)
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
        return binding.root;
    }
}