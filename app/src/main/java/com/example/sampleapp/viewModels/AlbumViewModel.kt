package com.example.sampleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.models.Album
import com.example.sampleapp.models.User
import com.example.sampleapp.repository.AlbumRepository
import com.example.sampleapp.repository.UserRepository
import java.util.*

class AlbumViewModel : ViewModel() {
    private lateinit var albumRepository : AlbumRepository
    private lateinit var albumsLiveData : LiveData<List<Album>>

    fun init() {
        albumRepository = AlbumRepository()
        getAlbums()
        albumsLiveData = albumRepository.getAlbumsLiveData()
    }

    fun getAlbums() {
        albumRepository.getAlbums()
    }

    fun getAlbumsLiveData() : LiveData<List<Album>> {
        return albumsLiveData
    }
}