package com.optisol.vpm.data.retrofit


class ApiHelper(private val apiService: ApiService) {
    suspend fun getVideos(pageNumber: Int) = apiService.getVideos(pageNumber)
}