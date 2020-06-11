package com.example.videostreamingapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.videostreamingapp.R;
import com.example.videostreamingapp.adapter.VideoAdapter;
import com.example.videostreamingapp.model.ApiResponse;
import com.example.videostreamingapp.model.Video;
import com.example.videostreamingapp.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.video_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getJsonData();
    }

    private void getJsonData() {
        Call<ApiResponse> mService;

        mService = ApiClient.getInstance().getApi().getDataFromServer();

        mService.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                ApiResponse apiResponse = response.body();

                if (response.isSuccessful()){
                    final List<Video> videos = apiResponse.getCategories().get(0).getVideos();

                    videoAdapter = new VideoAdapter(videos);

                    videoAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {

                            Bundle b = new Bundle();
                            b.putSerializable("videoData", videos.get(position));

                            Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                            Toast.makeText(MainActivity.this, videos.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                            //send data to other activity using Bundle

                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    });

                    recyclerView.setAdapter(videoAdapter);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                call.cancel();
                Toast.makeText(MainActivity.this, "Failed: "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}