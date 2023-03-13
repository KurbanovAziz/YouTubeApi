package com.example.youtubeapi.remote

import com.example.youtubeapi.model.Item
import com.example.youtubeapi.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlayLists(
        @Query("key") apiKey : String,
        @Query("channelId") channelId : String,
        @Query("part") part : String
    ): Call<Playlists>

    @GET("playlistItems")
    fun getItemLists(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") id: String,
        @Query("maxResults") maxResults: Int = 20
    ): Call<Playlists>
}