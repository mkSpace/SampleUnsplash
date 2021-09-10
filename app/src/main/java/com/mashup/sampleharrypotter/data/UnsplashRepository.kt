package com.mashup.sampleharrypotter.data

import com.mashup.sampleharrypotter.network.service.UnsplashService
import javax.inject.Inject

class UnsplashRepository @Inject constructor(private val service: UnsplashService) {

    suspend fun getPhotos(page: Int): List<UnsplashImage>? = service.getPhotos(page)
}