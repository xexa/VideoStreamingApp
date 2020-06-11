package com.example.videostreamingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videostreamingapp.model.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private List<Video> videoList;

    public VideoAdapter(List<Video> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String imageUri = videoList.get(position).getThumb();
        Picasso.get().
                load(imageUri)
                .placeholder(R.drawable.loading)
                .into(holder.imageThumbnail);

        holder.videoTitle.setText(videoList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        if (videoList.size() > 0)
            return videoList.size();
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageThumbnail;
        private TextView videoTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageThumbnail = itemView.findViewById(R.id.videoThumbnail);
            videoTitle = itemView.findViewById(R.id.videoTitle);
        }
    }
}
