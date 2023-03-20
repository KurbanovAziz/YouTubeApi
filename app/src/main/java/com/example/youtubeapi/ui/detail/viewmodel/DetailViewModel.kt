package com.example.youtubeapi.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.App.Companion.repository
import com.example.youtubeapi.core.base.BaseViewModel
import com.example.youtubeapi.data.remote.model.Playlists

class DetailViewModel : BaseViewModel() {
    fun getItemLists(id: String): LiveData<Playlists> {
        return repository.getItemList(id)
    }
}