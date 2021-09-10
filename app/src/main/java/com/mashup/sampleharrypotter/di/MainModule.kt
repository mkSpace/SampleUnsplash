package com.mashup.sampleharrypotter.di

import com.mashup.sampleharrypotter.PhotoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun providePhotoAdapter(): PhotoAdapter = PhotoAdapter()
}