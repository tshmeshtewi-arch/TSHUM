package com.nuxflix.app

import android.net.Uri
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class PlayerActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private lateinit var loadingSpinner: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        playerView = findViewById(R.id.player_view)
        loadingSpinner = findViewById(R.id.loading_spinner)

        val videoUrl = intent.getStringExtra("videoUrl") ?: return

        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            playerView.player = exoPlayer

            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            exoPlayer.setMediaItem(mediaItem)

            exoPlayer.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    when (playbackState) {
                        Player.STATE_BUFFERING -> {
                            loadingSpinner.visibility = android.view.View.VISIBLE
                        }
                        Player.STATE_READY -> {
                            loadingSpinner.visibility = android.view.View.GONE
                        }
                        Player.STATE_ENDED -> {
                            finish()
                        }
                    }
                }
            })

            exoPlayer.prepare()
            exoPlayer.play()
        }
    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
}