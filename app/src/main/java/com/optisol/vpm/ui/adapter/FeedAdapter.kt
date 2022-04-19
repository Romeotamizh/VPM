package com.optisol.vpm.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optisol.vpm.common.formatToLocale
import com.optisol.vpm.data.model.Feed
import com.optisol.vpm.databinding.ItemFeedBinding

class FeedAdapter(
    private val feeds: List<Feed>,
    private val onFeedClickListener: OnFeedClickListener
) :
    RecyclerView.Adapter<FeedAdapter.FeedHolder>() {

    class FeedHolder(
        private val binding: ItemFeedBinding,
        private val onFeedClickListener: OnFeedClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(feed: Feed) = with(binding) {
            name.text = feed.name
            isLive = feed.isLive
            createdOn.text = "Created on : ${feed.createdDateTime.formatToLocale()}"
            root.setOnClickListener {
                onFeedClickListener.onClick(feed)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FeedHolder(
        ItemFeedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onFeedClickListener
    )

    override fun onBindViewHolder(holder: FeedHolder, position: Int) =
        holder.bind(feeds[position])


    override fun getItemCount() = feeds.size

    fun interface OnFeedClickListener {
        fun onClick(feed: Feed)

    }


}