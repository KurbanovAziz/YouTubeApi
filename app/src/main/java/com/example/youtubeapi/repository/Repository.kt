package com.example.youtubeapi.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.core.network.RetrofitClient
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.data.remote.ApiService
import com.example.youtubeapi.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylists(): LiveData<Resource<Playlists>> {
        val data = MutableLiveData<Resource<Playlists>>()

        data.value = Resource.loading()

        apiService.getPlaylists(
            BuildConfig.API_KEY, App.PART_PLAYLISTS, App.CHANNEL_ID
        ).enqueue(object : Callback<Playlists> {
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<Playlists>, t: Throwable) {
                data.value = Resource.error(t.message, null, null)
                Log.e("ololo", "${t.message}")
            }
        })
        return data
    }

    fun getItemList(id: String): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()
        apiService.getItemlists(BuildConfig.API_KEY, App.PART_PLAYLISTS, id)
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(
                    call: Call<Playlists>, response: Response<Playlists>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }
                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    print(t.stackTrace)
                }
            })
        return data
    }

    fun getVideo(id: String): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()
        apiService.getVideo(BuildConfig.API_KEY, App.PART_PLAYLISTS, id)
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(
                    call: Call<Playlists>, response: Response<Playlists>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }
                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    print(t.stackTrace)
                }
            })
        return data
    }
}