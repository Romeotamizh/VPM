package com.optisol.vpm.ui.viewmodel.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.optisol.vpm.data.repository.VideoRepository

class VideoViewModelFactory(private val videoRepository: VideoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(VideoViewModel::class.java))
            @Suppress("UNCHECKED_CAST")
            VideoViewModel(videoRepository) as T
        else
            throw IllegalArgumentException("Class not found")
    }
}