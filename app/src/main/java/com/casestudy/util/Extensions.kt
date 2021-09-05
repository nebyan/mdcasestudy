package com.casestudy.util

import android.view.View
import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.casestudy.model.MEDIA_TYPE
import com.casestudy.model.MediaDetailHolder
import com.casestudy.model.Meditation
import com.casestudy.model.Story

fun ImageView.loadCoil(url: String, cornerRadius: Float){
    this.load(url){
        transformations(RoundedCornersTransformation(cornerRadius,cornerRadius,cornerRadius,cornerRadius))
    }
}

fun Meditation.toMediaDetailHolder(): MediaDetailHolder{
    return MediaDetailHolder(
        type = MEDIA_TYPE.MEDITATION,
        title = this.title,
        desc = this.content,
        imageUrl = this.image.large,
        date = this.releaseDate
    )
}

fun Story.toMediaDetailHolder(): MediaDetailHolder{
    return MediaDetailHolder(
        type = MEDIA_TYPE.STORY,
        title = this.name,
        desc = this.text,
        imageUrl = this.image.large,
        date = this.date
    )
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}
