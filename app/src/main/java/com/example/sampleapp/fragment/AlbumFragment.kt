package com.example.helloworld

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.*
import com.example.sampleapp.databinding.AlbumListBinding
import com.example.sampleapp.models.Album
import com.example.sampleapp.network.UserService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.DriverManager
import java.util.*
import androidx.lifecycle.Observer

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumFragment(albumId: Int) : Fragment() {
    private var albumId : Int = albumId
    private var view1: View? = null
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private var fragment: Fragment? = null
    private val thumbnailFragment : String = "Thumbnail Fragment"
    private var albums = mutableListOf<Album>()

    private lateinit var albumViewModel: AlbumViewModel

    //private var userViewModel : UserViewModel
    private var albumAdapter: AlbumAdapter? = null;
    private lateinit var binding: AlbumListBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        albumViewModel.init()

        binding = DataBindingUtil.inflate(inflater, R.layout.album_list, container, false)
        albumViewModel.getAlbumsLiveData().observe(viewLifecycleOwner, Observer { data ->
            for (item in data) {
                if (item.albumId == albumId) {
                    albums.add(item)
                }
            }
            albumAdapter = AlbumAdapter(context, R.layout.album_info, albums)
            binding.albumList.adapter = albumAdapter
            DriverManager.println("debug -- > ${data.toString()}")
        })

        Log.d("userAlbumAdapter", albumAdapter.toString())
        val albumInfo = binding.albumList
        albumInfo.adapter = albumAdapter

        //retro fit call should not be called on main thread
        //big system calls to a database etc. shoould be implemented with a viewModel
        //viewModel is like hiding the business logic

        binding.title = "Album ID: $albumId"
        albumInfo.onItemClickListener = OnItemClickListener { parent, view1, position, id ->
            Log.d("Album fragment", "In album fragment")
            Log.i("Touch", "Touched the screen");
            Log.i("Position in list", Integer.toString(position));
            var fm = activity?.supportFragmentManager;
            fragment = fm!!.findFragmentByTag(thumbnailFragment);
            if (fragment == null) {
                val ft = fm?.beginTransaction();
                fragment = ThumbnailFragment(albums.get(position));
                ft.add(R.id.container, fragment as ThumbnailFragment, thumbnailFragment);
                ft.addToBackStack(thumbnailFragment)
                ft.commit();
                Log.i("Position is ", Integer.toString(position));
            }
            Log.i("Touch", "Touched the screen");
            Log.i("Position in list", Integer.toString(position));
            //Log.i("Album info", albumViewModel!!.albums[position].toString())
        }
        Log.d("Album adapter set", "Album adapter set")
        return binding.root
    }
}