package com.mashup.sampleharrypotter.network.service

import com.mashup.sampleharrypotter.data.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {

    @GET("photos")
    suspend fun getPhotos(@Query("page") page: Int = 1): List<UnsplashImage>?
}