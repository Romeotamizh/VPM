package com.optisol.vpm.ui.viewmodel.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.optisol.vpm.data.repository.VideoRepository
import com.optisol.vpm.data.retrofit.Result
import kotlinx.coroutines.Dispatchers

class VideoViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    fun getVideos(pageNumber: Int)  =liveData(Dispatchers.IO) {
        emit(Result.loading(null))
        try {
            emit(Result.success(videoRepository.getVideos(pageNumber)))
        } catch (exception: Exception) {
            emit(Result.failure(null, exception.message ?: "Unknown Error!"))
        }

    }


}