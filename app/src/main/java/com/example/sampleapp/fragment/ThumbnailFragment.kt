package com.example.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.sampleapp.databinding.ThumbnailInfoBinding
import com.example.sampleapp.databinding.UserListBinding
import com.example.sampleapp.models.Album
import com.squareup.picasso.Picasso

class ThumbnailFragment (private val album : Album?) : Fragment(){
    private var view1: View? = null
    private lateinit var binding:ThumbnailInfoBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.thumbnail_info, container, false)
        Picasso.get().load(album?.url).into(binding.thumbnail)
        binding.text = album?.title
        binding.albumID = "Album ID: " + album?.albumId
        binding.photoID = "Photo ID: " + album?.id
        return binding.root
    }
}