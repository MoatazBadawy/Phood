package com.phood.identity.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.phood.identity.ui.view.databinding.ActivityLoginBinding
import com.phood.identity.ui.view.utils.Constants.INTRO_VIDEO_URL
import com.phood.identity.ui.view.utils.CustomPlayerManager
import com.phood.identity.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var playerManager: CustomPlayerManager
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initStateBar()
        initMediaPlayer()
        observeEvents()
    }

    private fun initStateBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.statusBarColor = resources.getColor(R.color.main_color)
        }
    }

    private fun initMediaPlayer() {
        playerManager = CustomPlayerManager(this, binding.playerView)
        playerManager.setMediaItem(INTRO_VIDEO_URL)
        playerManager.displayCustomViewBeforePlayVideo(binding.loginImageBackground)
        playerManager.displayVideoPlayerAfterVideoPlay(binding.loginImageBackground)
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isUserLoggedIn.collect { isUserLoggedIn ->
                    if (isUserLoggedIn) {
                        val uri = Uri.parse("com.moataz.phood://main")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoginFailed.collect { isLoginFailed ->
                    if (isLoginFailed) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login Failed",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerManager.release()
    }
}
