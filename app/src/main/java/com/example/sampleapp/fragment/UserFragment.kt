package com.example.helloworld

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.R
import com.example.sampleapp.UserViewModel
import com.example.sampleapp.databinding.UserListBinding
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.sampleapp.fragment.BaseFragment
import java.sql.DriverManager.println
import java.util.*

class UserFragment() : BaseFragment() {
    private var view1: View? = null
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private val albumFragment: String = "Album Fragment"
    private val title : String = "User Info"
    private var fragment: Fragment? = null
    private lateinit var userViewModel: UserViewModel

    //private var userViewModel : UserViewModel
    private var userAlbumAdapter: UserAdapter? = null;
    private lateinit var binding: UserListBinding;

    override fun getLayoutId(): Int {
        return R.layout.user_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.init()

        binding = baseBinding as UserListBinding
        binding.titleName = getTitle(getLayoutId())

        userViewModel.getUsersLiveData().observe(viewLifecycleOwner, Observer { data ->
            userAlbumAdapter = UserAdapter(context, R.layout.user_info, data)
            binding.userList.adapter = userAlbumAdapter
          println("debug -- > ${data.toString()}")
        })

        Log.d("userAlbumAdapter", userAlbumAdapter.toString())
        val userInfo = binding.userList
        userInfo.adapter = userAlbumAdapter

        //Log.d("Users list", "The length of the list of Users is" + userViewModel!!.getUsersLiveData().)
        userInfo.setOnItemClickListener { adapterView: AdapterView<*>, view: View, i: Int, l: Long ->
            Log.e("position id", i.toString())
            val action = UserFragmentDirections.actionUserFragmentToAlbumFragment()
            //Navigation.findNavController(binding.root).navigate(action)
            view.findNavController().navigate(action)

        }



            /*OnItemClickListener { parent, view1, position, id ->
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
        }*/
        Log.d("User adapter set", "User adapter set")
        //return binding.root;
        //return super.onCreateView(inflater, container, savedInstanceState)
    }
}