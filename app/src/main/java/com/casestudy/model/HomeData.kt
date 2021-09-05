package com.casestudy.model


import com.google.gson.annotations.SerializedName

data class HomeData(
    @SerializedName("isBannerEnabled")
    val isBannerEnabled: Boolean,
    @SerializedName("meditations")
    val meditations: List<Meditation>,
    @SerializedName("stories")
    val stories: List<Story>
)