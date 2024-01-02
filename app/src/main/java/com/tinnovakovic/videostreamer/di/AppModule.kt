package com.tinnovakovic.videostreamer.di

import com.tinnovakovic.videostreamer.data.local_api.StreamerLocalJson
import com.tinnovakovic.videostreamer.data.local_api.StreamerLocalJsonImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindStreamerApi(
        streamerLocalJsonImpl: StreamerLocalJsonImpl
    ): StreamerLocalJson


}