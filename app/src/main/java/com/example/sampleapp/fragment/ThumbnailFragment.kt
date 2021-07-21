package com.example.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sampleapp.models.Album
import com.squareup.picasso.Picasso

class ThumbnailFragment (private val album : Album?) : Fragment(){
    private var view1: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        view1 = inflater.inflate(R.layout.thumbnail_info, container, false)

        val thumbnail : ImageView = view1?.findViewById<View>(R.id.thumbnail) as ImageView
        Picasso.get().load(album?.url).into(thumbnail)
        val text : TextView = view1?.findViewById<View>(R.id.thumbnailText) as TextView
        text.text = album?.title
        val album_id : TextView = view1?.findViewById<View>(R.id.thumbnail_album_id) as TextView
        album_id.text = "Album ID: " + album?.albumId
        val photo_id : TextView = view1?.findViewById<View>(R.id.thumbnail_photo_id) as TextView
        photo_id.text = "Photo ID: " + album?.id

        return view1;
    }



}