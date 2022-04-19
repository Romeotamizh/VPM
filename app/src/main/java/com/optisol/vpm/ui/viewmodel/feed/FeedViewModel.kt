package com.optisol.vpm.ui.viewmodel.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.optisol.vpm.data.repository.FeedRepository
import com.optisol.vpm.data.model.Feed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FeedViewModel(private val feedRepository: FeedRepository) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    fun getFeeds() = liveData(Dispatchers.IO) {
        emit(feedRepository.getAllFeeds())
    }

    fun insertFeed(feed: Feed) = coroutineScope.launch {
        feedRepository.insertFeed(feed)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()

    }
}