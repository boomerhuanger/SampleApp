package com.example.sampleapp

import androidx.lifecycle.ViewModel
import com.example.helloworld.Album
import java.util.*

class AlbumViewModel : ViewModel() {
    val albums = ArrayList<Album?>()
}