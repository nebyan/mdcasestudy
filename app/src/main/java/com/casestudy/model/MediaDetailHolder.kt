package com.casestudy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaDetailHolder(
    var type: MEDIA_TYPE,
    var title: String,
    var desc: String,
    var imageUrl: String,
    var date: String,
): Parcelable

enum class MEDIA_TYPE{
    MEDITATION, STORY
}
