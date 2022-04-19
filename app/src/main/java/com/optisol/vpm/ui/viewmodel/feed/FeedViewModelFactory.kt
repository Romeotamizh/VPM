package com.optisol.vpm.ui.viewmodel.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.optisol.vpm.data.repository.FeedRepository

class FeedViewModelFactory(private val repository: FeedRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FeedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}