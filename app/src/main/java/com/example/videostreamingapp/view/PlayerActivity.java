package com.example.videostreamingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.videostreamingapp.R;
import com.example.videostreamingapp.model.Video;

public class PlayerActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private VideoView videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        title = findViewById(R.id.video_title_player);
        description = findViewById(R.id.videoDesc_player_activity);
        videoPlayer = findViewById(R.id.videoView);

        Intent intent = getIntent();

        Bundle data = intent.getExtras();

        //create an object that was passed from MainActivity using Bundle
        Video video = (Video) data.getSerializable("videoData");

        assert video != null;
        title.setText(video.getTitle());
        description.setText(video.getDescription());


    }

}