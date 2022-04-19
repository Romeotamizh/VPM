package com.optisol.vpm.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.optisol.vpm.data.model.Video
import com.optisol.vpm.databinding.ItemVideoBinding

class VideoAdapter(private val videos: List<Video>) :
    RecyclerView.Adapter<VideoAdapter.VideoHolder>() {

    class VideoHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(video: Video) = with(binding) {
            Glide.with(root.context).load(video.avatar).into(avatar)
            name.text = "${video.first_name} ${video.last_name}"
            email.text = video.email
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VideoHolder(
        ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: VideoHolder, position: Int) =
        holder.bind(videos[position])


    override fun getItemCount() = videos.size

}