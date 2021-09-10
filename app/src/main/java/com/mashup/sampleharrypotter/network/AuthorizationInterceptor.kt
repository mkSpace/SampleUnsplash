package com.mashup.sampleharrypotter.network

import com.mashup.sampleharrypotter.Constant
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request()
            .newBuilder()
            .apply { header("Authorization", "Client-ID ${Constant.ACCESS_KEY}") }
            .build()
    )
}