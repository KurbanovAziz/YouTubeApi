package com.example.youtubeapi.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.ItemPlaylistsBinding
import com.example.youtubeapi.data.remote.model.Item
import com.example.youtubeapi.core.ext.loadImage

class DetailAdapter(val onClick: (Item) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private val items = arrayListOf<Item>()

    inner class DetailViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.ivItem.loadImage(item.snippet.thumbnails.medium.url)
            binding.tvTitle.text = item.snippet.title
            binding.blackBar.isVisible = false
            binding.tvVideo.text = item.snippet.publishedAt

            itemView.setOnClickListener {
                onClick(item)
            }
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