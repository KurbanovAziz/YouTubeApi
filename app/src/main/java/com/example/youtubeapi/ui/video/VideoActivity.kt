package com.example.youtubeapi.ui.video

import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.App
import com.example.youtubeapi.R
import com.example.youtubeapi.core.base.BaseActivity
import com.example.youtubeapi.core.ext.isNetworkConnected
import com.example.youtubeapi.core.ext.showToast
import com.example.youtubeapi.core.utils.ConnectionManager
import com.example.youtubeapi.databinding.ActivityVideoBinding
import com.example.youtubeapi.ui.playlist.PlayListsActivity

class VideoActivity : BaseActivity<ActivityVideoBinding, VideoViewModel>() {

    private var click = true
    override val viewModel: VideoViewModel by lazy {
        ViewModelProvider(this)[VideoViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        super.initListener()
        binding.btnPlay.setOnClickListener {
            click = if (click) {
                binding.btnPlay.setImageResource(R.drawable.ic_pause)
                false
            } else {
                binding.btnPlay.setImageResource(R.drawable.ic_play)
                true
            }
        }
        binding.btnDownload.setOnClickListener {
            binding.videoQualityLayout.isVisible = true
        }
        binding.videoQuality.btnDownload.setOnClickListener {
            binding.videoQualityLayout.isVisible = false
        }

    }

    override fun initViewModel() {
        super.initViewModel()
        val title = intent.getStringExtra(PlayListsActivity.KEY_TITLE)
        val desc = intent.getStringExtra(PlayListsActivity.KEY_DESC)
        val id = intent.getStringExtra(App.KEY)
        if (id != null) {
            viewModel.getVideo(id)
            binding.tvTitleVideo.text = title
            binding.tvDescriptionVideo.text = desc
        }
    }


    override fun isConnection() {
        val cld = ConnectionManager(application)
        cld.observe(this) { isConnected ->
            binding.checkInternet.root.isVisible = !isConnected
            binding.checkInternet.btnTryAgain.setOnClickListener {
                if (!isNetworkConnected()) {
                    showToast(getString(R.string.no_internet))
                } else {
                    binding.checkInternet.root.isVisible = false
                }
            }
        }
    }
}