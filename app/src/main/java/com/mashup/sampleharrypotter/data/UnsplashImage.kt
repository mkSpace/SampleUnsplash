package com.mashup.sampleharrypotter.data

data class UnsplashImage(val id: String, val width: Int, val height: Int, val urls: ImageUrls) {
    data class ImageUrls(
        val small: String?
    )

    companion object {
        const val SMALL_IMAGE_WIDTH = 400
    }
}