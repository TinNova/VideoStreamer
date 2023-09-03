package com.tinnovakovic.videostreamer.di

import com.tinnovakovic.videostreamer.TemplateApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    //TODO: Update API Template As Required
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient.Builder): TemplateApi {
        return Retrofit.Builder()
            .baseUrl("templateApi")
            .client(
                okHttpClient
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TemplateApi::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }
}