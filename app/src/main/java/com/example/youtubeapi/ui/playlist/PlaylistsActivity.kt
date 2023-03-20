package com.example.youtubeapi.ui.playlist

import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.App.Companion.KEY
import com.example.youtubeapi.R
import com.example.youtubeapi.core.base.BaseActivity
import com.example.youtubeapi.core.ext.isNetworkConnected
import com.example.youtubeapi.core.ext.showToast
import com.example.youtubeapi.databinding.PlaylistsMainBinding
import com.example.youtubeapi.ui.detail.DetailActivity
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.utils.ConnectionManager
import com.example.youtubeapi.ui.playlist.adapter.PlaylistsAdapter

class PlayListsActivity : BaseActivity<PlaylistsMainBinding, PlaylistsViewModel>() {

    private lateinit var adapter: PlaylistsAdapter
    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun inflateViewBinding(): PlaylistsMainBinding {
        return PlaylistsMainBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        adapter = PlaylistsAdapter {
            val intent = Intent(this@PlayListsActivity, DetailActivity::class.java)
            intent.putExtra(KEY, it.id)
            intent.putExtra(KEY_TITLE, it.snippet.title)
            intent.putExtra(KEY_DESC, it.snippet.description)
            intent.putExtra(KEY_VIDEO_COUNT, it.contentDetails.itemCount)
            startActivity(intent)
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

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlaylists().observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressCircular.isVisible = true
                }
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    it.data?.items?.let { it1 -> adapter.setPlaylists(it1) }
                    binding.progressCircular.isVisible = false
                }
                Status.ERROR -> {
                    showToast(it.message.toString())
                    binding.progressCircular.isVisible = false

                }
            }
        }
    }

    companion object{
        const val KEY_DESC = "desc"
        const val KEY_TITLE = "title"
        const val KEY_VIDEO_COUNT = "video_count"
    }

}