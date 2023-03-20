package com.example.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.App.Companion.repository
import com.example.youtubeapi.core.base.BaseViewModel
import com.example.youtubeapi.data.remote.model.Playlists

class VideoViewModel : BaseViewModel() {

    fun getVideo(id: String): LiveData<Playlists> {
        return repository.getVideo(id)
    }
}