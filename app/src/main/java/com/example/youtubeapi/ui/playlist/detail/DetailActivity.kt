package com.example.youtubeapi.ui.playlist.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.App.Companion.KEY
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityDetailBinding
import com.example.youtubeapi.ui.playlist.detail.adapter.DetailAdapter
import com.example.youtubeapi.ui.playlist.detail.viewmodel.DetailViewModel
import com.example.youtubeapi.utils.ConnectionManager
import com.example.youtubeapi.utils.isNetworkConnected
import com.example.youtubeapi.utils.showToast

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    private lateinit var adapter: DetailAdapter

    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        binding.toolbar.tvBack.setOnClickListener {
            finish()
        }
    }

    override fun isConnection() {
        super.isConnection()
        val connectionManager = ConnectionManager(application)
        connectionManager.observe(this) { isConnected ->
            binding.noInternet.root.isVisible = !isConnected
            binding.noInternet.btnTryAgain.setOnClickListener {
                if (!isNetworkConnected()) {
                    showToast(getString(R.string.no_internet))
                    binding.itemsLayout.isVisible = false
                } else {
                    binding.noInternet.root.isVisible = false
                    binding.itemsLayout.isVisible = true
                }
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        adapter = DetailAdapter()
        val id = intent.getStringExtra(KEY)
        id?.let { _ ->
            viewModel.getItemLists(id).observe(this) {
                binding.rvItems.adapter = adapter
                adapter.setItems(it.items)
            }
        }

    }
}