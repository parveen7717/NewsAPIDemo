package com.example.newsapidemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapidemo.R;
import com.example.newsapidemo.model.Article;
import com.example.newsapidemo.util.ImageLoad;
import com.example.newsapidemo.util.LOGS;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.TopNewsHolder> {

    public interface OnItemClickListener {
        void onItemClick(Article list);
    }

    private Context context;
    private List<Article> list;
    OnItemClickListener listener;

    public TopNewsAdapter(Context context, List<Article> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        TopNewsHolder topNewsHolder = new TopNewsHolder(view);
        return topNewsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TopNewsHolder holder, int position) {
        if (position>0){
            holder.newsheading.setText(list.get(position).getTitle());
            holder.channelName.setText(list.get(position).getSource().getName());
            if (list.get(position).getUrlToImage()!=null && !list.get(position).getUrlToImage().equals("")){
                ImageLoad.imageLoad(list.get(position).getUrlToImage(), new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        holder.newsImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
            holder.bind(list.get(position), listener);
        }


    }

    @Override
    public int getItemCount() {
        LOGS.system("-------Sizeeeeeeeeeeeeeee-----------",String.valueOf(list.size()));
        return list.size();
    }

    public class TopNewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.newsImg)
        ImageView newsImg;
        @BindView(R.id.newsheading)
        TextView newsheading;
        @BindView(R.id.channelName)
        TextView channelName;
        public TopNewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Article item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
