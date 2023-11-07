package com.tinnovakovic.videostreamer.di

import com.tinnovakovic.videostreamer.data.StreamerRepo
import com.tinnovakovic.videostreamer.data.StreamerRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindStreamerRepo(impl: StreamerRepoImpl): StreamerRepo
}