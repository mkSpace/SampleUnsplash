package com.mashup.sampleharrypotter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mashup.sampleharrypotter.data.UnsplashImage
import com.mashup.sampleharrypotter.databinding.ItemPhotoBinding

class PhotoAdapter : ListAdapter<UnsplashImage, PhotoAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UnsplashImage>() {
            override fun areItemsTheSame(oldItem: UnsplashImage, newItem: UnsplashImage): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UnsplashImage,
                newItem: UnsplashImage
            ): Boolean = oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position !in 0 until itemCount) return
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    private fun ViewHolder.bind(item: UnsplashImage) {
        val smallWidth = UnsplashImage.SMALL_IMAGE_WIDTH
        val widthRatio = item.width / smallWidth
        val height = item.height / widthRatio
        Glide.with(binding.root)
            .load(item.urls.small)
            .override(400, height)
            .into(binding.root)
    }
}