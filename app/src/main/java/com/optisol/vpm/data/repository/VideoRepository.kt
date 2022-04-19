package com.optisol.vpm.data.repository

import com.optisol.vpm.data.retrofit.ApiHelper

class VideoRepository(private val apiHelper: ApiHelper) {

    suspend fun getVideos(pageNumber: Int) = apiHelper.getVideos(pageNumber)
}