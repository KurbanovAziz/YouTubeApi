package com.example.youtubeapi

import android.app.Application
import com.example.youtubeapi.remote.Repository

class App : Application() {
    val repository: Repository by lazy {
        Repository()
    }
    companion object{
        const val CHANNEL_ID = "UCt7sv-NKh44rHAEb-qCCxvA"
        const val PART_PLAYLISTS = "snippet,contentDetails"
        const val KEY: String = "key"
    }
}
