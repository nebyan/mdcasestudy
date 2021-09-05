package com.casestudy.player

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer

class AppPlayer(var context: Context, var appPlayerListener: AppPlayerListener) : Player.Listener {

    private val exoplayer by lazy {
        SimpleExoPlayer.Builder(context)
            .build()
    }

    init {

        exoplayer.addListener(this)

    }

    fun play(streamUrl: String) {

        when (exoplayer.playbackState) {
            Player.STATE_ENDED,
            Player.STATE_IDLE -> {
                playUrl(streamUrl)
            }
            Player.STATE_BUFFERING -> {
                exoplayer.stop()
            }
            Player.STATE_READY -> {
                if (exoplayer.isPlaying) {
                    exoplayer.pause()
                } else {
                    exoplayer.play()
                }
            }
        }
    }

    private fun playUrl(streamUrl: String) {
        exoplayer.setMediaItem(MediaItem.fromUri(Uri.parse(streamUrl)))
        exoplayer.prepare()
        exoplayer.play()
    }

    fun destroy() {
        exoplayer.release()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {

        when (playbackState) {
            Player.STATE_ENDED,
            Player.STATE_IDLE -> {
                appPlayerListener.paused()
            }
            Player.STATE_BUFFERING,
            Player.STATE_READY -> {
                appPlayerListener.started()
            }
        }
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        if (playWhenReady) {
            appPlayerListener.started()
        } else {
            appPlayerListener.paused()
        }
    }


}

interface AppPlayerListener {

    fun started()

    fun paused()
}