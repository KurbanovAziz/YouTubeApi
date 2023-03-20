package com.example.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.App.Companion.repository
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.core.base.BaseViewModel
import com.example.youtubeapi.data.remote.model.Playlists
import com.example.youtubeapi.data.remote.ApiService
import com.example.youtubeapi.core.network.RetrofitClient
import com.example.youtubeapi.core.network.result.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : BaseViewModel() {

    fun getPlaylists() : LiveData<Resource<Playlists>> {
        return repository.getPlaylists()
    }


}