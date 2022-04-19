package com.optisol.vpm

import android.app.Application
import android.content.Context
import com.optisol.vpm.data.model.Feed
import com.optisol.vpm.data.repository.FeedRepository
import com.optisol.vpm.data.repository.VideoRepository
import com.optisol.vpm.data.retrofit.ApiHelper
import com.optisol.vpm.data.retrofit.RetrofitBuilder
import com.optisol.vpm.data.room.FeedDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VPMApplication : Application() {

    companion object {

        private var videoRepository: VideoRepository? = null
        fun getVideoRepository() = videoRepository ?: VideoRepository(
            ApiHelper(RetrofitBuilder.apiService)
        )

        private var feedRepository: FeedRepository? = null
        fun getFeedRepository(applicationContext: Context) = feedRepository ?: FeedRepository(
            FeedDatabase.getDatabase(applicationContext).feedDao()
        )
    }


}