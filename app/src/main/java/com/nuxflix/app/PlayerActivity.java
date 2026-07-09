package com.nuxflix.app;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        String title = getIntent().getStringExtra("title");
        String videoUrl = getIntent().getStringExtra("videoUrl");

        VideoView videoView = findViewById(R.id.video_view);
        ProgressBar spinner = findViewById(R.id.loading_spinner);
        TextView playerTitle = findViewById(R.id.player_title);
        ImageButton btnBack = findViewById(R.id.btn_back);

        playerTitle.setText(title);
        btnBack.setOnClickListener(v -> finish());

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(videoUrl));

        videoView.setOnPreparedListener(mp -> {
            spinner.setVisibility(View.GONE);
            videoView.start();
        });

        videoView.setOnErrorListener((mp, what, extra) -> {
            spinner.setVisibility(View.GONE);
            return false;
        });
    }
}
