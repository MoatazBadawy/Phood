package com.phood.identity.ui.view.utils

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class CustomPlayerManager(
    private val context: Context,
    private val playerView: PlayerView,
) {
    private lateinit var videoPlayer: ExoPlayer

    init {
        initPlayer()
    }

    private fun initPlayer() {
        videoPlayer = ExoPlayer.Builder(context).build()
        playerView.player = videoPlayer
        videoPlayer.prepare()
        videoPlayer.playWhenReady = true
        videoPlayer.repeatMode = Player.REPEAT_MODE_ALL
    }

    fun setMediaItem(uri: String) {
        val mediaItem = MediaItem.Builder().setUri(uri).build()
        videoPlayer.setMediaItem(mediaItem)
        videoPlayer.prepare()
    }

    fun displayCustomViewBeforePlayVideo(view: View) {
        playerView.visibility = GONE
        view.visibility = VISIBLE
    }

    fun displayVideoPlayerAfterVideoPlay(view: View) {
        videoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == ExoPlayer.STATE_READY) {
                    playerView.visibility = VISIBLE
                    view.visibility = INVISIBLE
                }
            }
        })
    }

    fun release() {
        videoPlayer.release()
    }
}
