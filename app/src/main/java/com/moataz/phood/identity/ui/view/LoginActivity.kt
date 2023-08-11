package com.moataz.phood.identity.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.moataz.phood.R
import com.moataz.phood.databinding.ActivityLoginBinding
import com.moataz.phood.identity.ui.view.utils.Constants.INTRO_VIDEO_URL
import com.moataz.phood.identity.ui.view.utils.CustomPlayerManager

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var playerManager: CustomPlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initMediaPlayer()
    }

    private fun initMediaPlayer() {
        playerManager = CustomPlayerManager(this, binding.playerView)
        playerManager.setMediaItem(INTRO_VIDEO_URL)
        playerManager.displayCustomViewBeforePlayVideo(binding.loginImageBackground)
        playerManager.displayVideoPlayerAfterVideoPlay(binding.loginImageBackground)
    }

    override fun onDestroy() {
        super.onDestroy()
        playerManager.release()
    }
}
