package com.example.videostreamingapp.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.videostreamingapp.R;
import com.example.videostreamingapp.model.Video;

import retrofit2.http.Url;

public class PlayerActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private VideoView videoPlayer;
    private ProgressBar progressBar;
    private ImageView fullScreen;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        title = findViewById(R.id.video_title_player);
        description = findViewById(R.id.videoDesc_player_activity);
        videoPlayer = findViewById(R.id.videoView);
        progressBar = findViewById(R.id.progressBar);
        fullScreen = findViewById(R.id.fullScreenOp);
        frameLayout = findViewById(R.id.frameLayout);

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

        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullScreen.setVisibility(View.GONE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                getSupportActionBar().hide();
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                frameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)));
                videoPlayer.setLayoutParams(new FrameLayout.LayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)));
            }
        });


    }

    @Override
    public void onBackPressed() {
        fullScreen.setVisibility(View.VISIBLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().show();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int heightValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,220,getResources().getDisplayMetrics());
        frameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,heightValue)));
        videoPlayer.setLayoutParams(new FrameLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,heightValue)));
        int orientation = getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            super.onBackPressed();
        }

    }
}