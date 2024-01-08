package com.tinnovakovic.videostreamer.data.models

import com.google.gson.annotations.SerializedName

data class CatFact(
    @SerializedName("text")
    val fact: String,
    val isSelected: Boolean
)