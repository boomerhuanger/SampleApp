package com.example.sampleapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloworld.AlbumAdapter
import com.example.sampleapp.AlbumViewModel
import com.example.sampleapp.R
import com.example.sampleapp.ThumbnailFragment
import com.example.sampleapp.UserViewModel
import com.example.sampleapp.models.Album
import java.sql.DriverManager

abstract class BaseFragment() : Fragment() {

    //private lateinit var viewModel : ViewModel
    lateinit var baseBinding: ViewDataBinding
    private val userTitle : String = "User Info"
    private val photoTitle = 1;
    private val albumTitle = 2;
    lateinit var viewModel: ViewModel

    @LayoutRes
    abstract fun getLayoutId() : Int

    @SuppressLint("ResourceType")
    open fun getTitle(layoutId : Int, idNumber : Int = 0) : String {
        lateinit var title : String;
        if (layoutId == R.layout.user_list) {
            title = userTitle
        } else if (layoutId == photoTitle) {
            title = "Photo ID: $idNumber"
        } else if (layoutId == albumTitle){
            title = "Album ID: $idNumber"
        }
        return title
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Layout id", getLayoutId().toString())
        baseBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return baseBinding.root
    }
}