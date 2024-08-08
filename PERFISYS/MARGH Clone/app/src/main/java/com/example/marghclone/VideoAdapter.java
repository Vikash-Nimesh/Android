package com.example.marghclone;


import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<VideoAct> videoActList;
    Context context;

    // creating consturctor
    public VideoAdapter(Context context,List<VideoAct> videoActList)
    {
        this.context = context;
        this.videoActList = videoActList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        // the layout that
                        // we created above
                        R.layout.video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position)
    {
        holder.setDataVideo(videoActList.get(position),context);
    }

    @Override
    public int getItemCount()
    {
        return videoActList.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        ImageView videoView;



        public VideoViewHolder(@NonNull View itemView)
        {
            super(itemView);

            // getting all this from the
            // java file we created in above steps
            videoView = itemView.findViewById(R.id.videoView);


        }

        void setDataVideo(VideoAct videoAct,Context context)
        {

            Glide.with(context).load(videoAct.videoURL).into(videoView);
        }
    }
}
