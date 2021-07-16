package com.example.helloworld

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

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
    private var albumAdapter : AlbumAdapter? = null
    private var albumViewModel : AlbumViewModel? = null;
    private val thumbnailFragment : String = "Thumbnail Fragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        //ListView mListView = (ListView) findViewById(R.id.listView1);
        if (albumViewModel == null) {
            albumViewModel= ViewModelProvider(this).get(AlbumViewModel::class.java)
        }

        view1 = inflater.inflate(R.layout.album_list, container, false)
        getPhotos()

        //retro fit call should not be called on main thread
        //big system calls to a database etc. shoould be implemented with a viewModel
        //viewModel is like hiding the business logic
        Log.d("Albums list", "The length of the list of Albums is" + albumViewModel?.albums?.size)
        val albumView = view1!!.findViewById<ListView>(R.id.albumList)
        if (albumView != null) {
            var title = view1!!.findViewById<View>(R.id.album_id) as TextView
            title.setText("Album ID: " + albumId.toString())
            albumView.onItemClickListener = OnItemClickListener { parent, view1, position, id ->
                Log.d("Album fragment", "In album fragment")
                    Log.i("Touch", "Touched the screen");
                    Log.i("Position in list", Integer.toString(position));
                    var fm = activity?.supportFragmentManager;
                    fragment = fm!!.findFragmentByTag(thumbnailFragment);
                    if (fragment == null) {
                        val ft = fm?.beginTransaction();
                        fragment = ThumbnailFragment(albumViewModel!!.albums[position]);
                        ft.add(R.id.container, fragment as ThumbnailFragment, thumbnailFragment);
                        ft.addToBackStack(thumbnailFragment)
                        ft.commit();
                        Log.i("Position is ", Integer.toString(position));
                    }
                Log.i("Touch", "Touched the screen");
                Log.i("Position in list", Integer.toString(position));
                Log.i("Album info", albumViewModel!!.albums[position].toString())
            }
        }
        Log.d("Album adapter set", "Album adapter set")
        return view1
    }

    // Calling '/api/users/2'
    private fun getPhotos() {
        val httpClient = OkHttpClient.Builder()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        val service = retrofit.create<UserService>(UserService::class.java)

        albumAdapter = AlbumAdapter(context, R.layout.album_info, albumViewModel!!.albums)

        Log.i("Album id", albumId.toString())

        // Calling '/api/users/2'
        service.photos().enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (!response.isSuccessful) {
                    return
                }
                Log.i("Response", call.request().toString())

                val apiResponse = response.body()!!

                //users = apiResponse;
                for (album in apiResponse) {
                    if (album.albumId == albumId)
                        albumViewModel?.albums?.add(album)
                }
                Log.i("Users size", "List of users is" + albumViewModel?.albums?.size)
                Log.i("All users", albumViewModel?.albums.toString())

                //Log.i("response", apiResponse.toString());
                Log.i("my tag", "testing message")

                val albumInfo = view1!!.findViewById<ListView>(R.id.albumList)
                albumInfo.adapter = albumAdapter
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.d("Network Error", "Network Error :: " + t.localizedMessage)
            }
        })
    }
}