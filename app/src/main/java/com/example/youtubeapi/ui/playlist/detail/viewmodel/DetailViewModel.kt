package com.example.youtubeapi.ui.playlist.detail.viewmodel

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.model.Item
import com.example.youtubeapi.model.Playlists

class DetailViewModel : BaseViewModel() {
    fun getItemLists(id: String): LiveData<Playlists> {
        return App().repository.getItemList(id)
    }
}