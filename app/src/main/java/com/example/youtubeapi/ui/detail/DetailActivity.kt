package com.example.youtubeapi.ui.detail


import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.App.Companion.KEY
import com.example.youtubeapi.R
import com.example.youtubeapi.core.base.BaseActivity
import com.example.youtubeapi.core.ext.isNetworkConnected
import com.example.youtubeapi.core.ext.showToast
import com.example.youtubeapi.core.utils.ConnectionManager
import com.example.youtubeapi.databinding.ActivityDetailBinding
import com.example.youtubeapi.ui.detail.adapter.DetailAdapter
import com.example.youtubeapi.ui.detail.viewmodel.DetailViewModel
import com.example.youtubeapi.ui.playlist.PlayListsActivity.Companion.KEY_DESC
import com.example.youtubeapi.ui.playlist.PlayListsActivity.Companion.KEY_TITLE
import com.example.youtubeapi.ui.playlist.PlayListsActivity.Companion.KEY_VIDEO_COUNT
import com.example.youtubeapi.ui.video.VideoActivity

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    private lateinit var adapter: DetailAdapter

    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        adapter = DetailAdapter {
            val intent = Intent(this, VideoActivity::class.java)
            intent.putExtra(KEY, it.id)
            intent.putExtra(KEY_TITLE_DETAIL, it.snippet.title)
            intent.putExtra(KEY_DESC_DETAIL, it.snippet.description)
            startActivity(intent)
        }
        binding.toolbar.tvBack.setOnClickListener {
            finish()
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

    override fun initViewModel() {
        super.initViewModel()
        val title = intent.getStringExtra(KEY_TITLE)
        val desc = intent.getStringExtra(KEY_DESC)
        val id = intent.getStringExtra(KEY)
        val videoCount = intent.getStringExtra(KEY_VIDEO_COUNT)
        id?.let { _ ->
            viewModel.getItemLists(id).observe(this) {
                binding.rvItems.adapter = adapter
                adapter.setItems(it.items)
                binding.tvTitleDetail.text = title
                binding.tvDescription.text = desc
                binding.tvVideoCount.text = videoCount
            }
        }
    }

    companion object {
        const val KEY_DESC_DETAIL = "desc"
        const val KEY_TITLE_DETAIL = "title"
    }
}