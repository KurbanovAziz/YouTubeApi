package com.example.youtubeapi.data.remote

import com.example.youtubeapi.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("key") apiKey : String,
        @Query("part") part : String,
        @Query("channelId") channelId : String,
        @Query("maxResults") maxResults: Int = 20
    ): Call<Playlists>

    @GET("playlistItems")
    fun getItemlists(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") id: String,
        @Query("maxResults") maxResults: Int = 20
    ): Call<Playlists>

    @GET("videos")
    fun getVideo(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("id") id: String,
    ): Call<Playlists>
}