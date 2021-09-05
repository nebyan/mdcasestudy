package com.casestudy.model


import com.google.gson.annotations.SerializedName

data class Meditation(
    @SerializedName("content")
    val content: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
)