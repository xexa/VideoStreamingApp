package com.example.videostreamingapp.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.videostreamingapp.R;
import com.example.videostreamingapp.model.Video;

import retrofit2.http.Url;

public class PlayerActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private VideoView videoPlayer;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        title = findViewById(R.id.video_title_player);
        description = findViewById(R.id.videoDesc_player_activity);
        videoPlayer = findViewById(R.id.videoView);
        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();

        Bundle data = intent.getExtras();

        //create an object that was passed from MainActivity using Bundle
        Video video = (Video) data.getSerializable("videoData");

        assert video != null;
        title.setText(video.getTitle());
        description.setText(video.getDescription());

        getSupportActionBar().setTitle(video.getTitle());

        Uri videoUri = Uri.parse(video.getSources().get(0));

        videoPlayer.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(this);

        videoPlayer.setMediaController(mediaController);

        videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoPlayer.start();
                progressBar.setVisibility(View.GONE);
            }
        });


    }

}