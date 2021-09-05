package com.casestudy.model


import com.google.gson.annotations.SerializedName

data class ImageX(
    @SerializedName("large")
    val large: String,
    @SerializedName("small")
    val small: String
)