package com.tinnovakovic.videostreamer.di

import com.tinnovakovic.videostreamer.remote.StreamerApi
import com.tinnovakovic.videostreamer.remote.StreamerApiImpl
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
        streamerApiImpl: StreamerApiImpl
    ): StreamerApi


}