package com.example.newsapidemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapidemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosNewsHolder> {

    public interface OnItemClickListener {
        void onItemClick(String list);
    }

    private Context context;
    List<String> list;
    OnItemClickListener listener;

    public PhotosAdapter(Context context, List<String> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PhotosNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_photos_items, parent, false);
        PhotosNewsHolder photosNewsHolder = new PhotosNewsHolder(view);
        return photosNewsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosNewsHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.bind(list.get(position),listener);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PhotosNewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.videoThumb1)
        ImageView videoThumb1;
        @BindView(R.id.videoThumb2)
        ImageView videoThumb2;
        @BindView(R.id.description1)
        TextView description1;
        @BindView(R.id.description2)
        TextView description2;

        private PhotosNewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void bind(final String item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
