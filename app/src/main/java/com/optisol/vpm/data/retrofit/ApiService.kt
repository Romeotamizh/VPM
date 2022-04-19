package com.optisol.vpm.data.retrofit

import com.optisol.vpm.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getVideos(@Query("page") pageNumber : Int) : VideoResponse
}