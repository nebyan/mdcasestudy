package com.casestudy.model


import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("category")
    val category: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("image")
    val image: ImageX,
    @SerializedName("name")
    val name: String,
    @SerializedName("text")
    val text: String
)