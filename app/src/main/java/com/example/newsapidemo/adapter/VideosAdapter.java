package com.example.newsapidemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapidemo.R;

import java.util.List;

import butterknife.ButterKnife;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoNewsHolder> {

    public interface OnItemClickListener {
        void onItemClick(String list);
    }

    private Context context;
    List<String> list;
    OnItemClickListener listener;

    public VideosAdapter(Context context, List<String> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideoNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_video_items, parent, false);
        VideoNewsHolder videoNewsHolder = new VideoNewsHolder(view);
        return videoNewsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoNewsHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VideoNewsHolder extends RecyclerView.ViewHolder {
        public VideoNewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final String item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
