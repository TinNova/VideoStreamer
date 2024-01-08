package com.tinnovakovic.videostreamer.data.remote_api

import com.tinnovakovic.videostreamer.data.models.CatFact
import retrofit2.http.GET
import retrofit2.http.Query

//TODO: Update API Template As Required
interface TemplateApi {

    @GET("facts")
    suspend fun getCatFacts(
    ): List<CatFact>

}