package com.mashup.sampleharrypotter.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SampleRetrofit {

    const val API_END_POINT = "https://api.unsplash.com/"

    fun <T> create(
        service: Class<T>,
        client: OkHttpClient,
        httpUrl: String = API_END_POINT
    ): T = Retrofit.Builder()
        .baseUrl(httpUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(service)

    inline fun <reified T : Any> create(
        client: OkHttpClient,
        httpUrl: String = API_END_POINT
    ): T {
        require(httpUrl.isNotBlank()) { "Parameter httpUrl cannot be blank." }
        return create(service = T::class.java, httpUrl = httpUrl, client = client)
    }
}