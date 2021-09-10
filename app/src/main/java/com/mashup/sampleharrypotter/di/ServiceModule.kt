package com.mashup.sampleharrypotter.di

import com.mashup.sampleharrypotter.network.SampleRetrofit
import com.mashup.sampleharrypotter.network.service.UnsplashService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideServiceModule(okHttpClient: OkHttpClient): UnsplashService =
        SampleRetrofit.create(okHttpClient)

}