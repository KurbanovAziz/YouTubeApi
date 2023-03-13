package com.example.youtubeapi.ui.playlist.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.ItemPlaylistsBinding
import com.example.youtubeapi.model.Item
import com.example.youtubeapi.utils.loadImage

class DetailAdapter :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private val items = arrayListOf<Item>()

    inner class DetailViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.ivItem.loadImage(item.snippet.thumbnails.medium.url)
            binding.tvTitle.text = item.snippet.title
            binding.blackBar.isVisible = false
            binding.tvVideo.text = item.snippet.publishedAt
        }
    }

    fun setItems(list: List<Item>) {
        items.clear()
        items.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(items[position])
    }
}