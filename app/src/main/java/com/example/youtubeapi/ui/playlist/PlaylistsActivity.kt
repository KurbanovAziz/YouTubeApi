package com.example.youtubeapi.ui.playlist

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.App.Companion.KEY
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.PlaylistsMainBinding
import com.example.youtubeapi.ui.playlist.adapter.PlaylistsAdapter
import com.example.youtubeapi.ui.playlist.detail.DetailActivity
import com.example.youtubeapi.utils.ConnectionManager
import com.example.youtubeapi.utils.isNetworkConnected
import com.example.youtubeapi.utils.showToast

class PlaylistsActivity : BaseActivity<PlaylistsMainBinding, PlaylistsViewModel>() {

    private lateinit var adapter : PlaylistsAdapter

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }
    
    override fun inflateViewBinding(): PlaylistsMainBinding {
        return PlaylistsMainBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        super.initListener()
        adapter = PlaylistsAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(KEY, it.id)
            startActivity(intent)
        }
    }
    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlists().observe(this) {
            binding.recyclerView.adapter = adapter
            adapter.setPlaylists(it.items)
        }
    }

    override fun isConnection() {
        super.isConnection()
        val cld = ConnectionManager(application)
        cld.observe(this) {
            if (!it) {
                binding.noInternet.isVisible = true
                binding.include.btnTryAgain.setOnClickListener {
                    if (!isNetworkConnected()) {
                        showToast(getString(R.string.no_internet))
                    } else {
                        binding.noInternet.isVisible = false
                    }
                }
            } else {
                initViewModel()
            }
        }
    }
}