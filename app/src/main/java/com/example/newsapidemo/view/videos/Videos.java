package com.example.newsapidemo.view.videos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapidemo.R;
import com.example.newsapidemo.adapter.VideosAdapter;
import com.example.newsapidemo.view.HomeActivity;
import com.example.newsapidemo.view.news_detail.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Videos extends Fragment {
    @BindView(R.id.videoRecycler)
    RecyclerView videoRecycler;
    Unbinder unbinder;
    List<String> videoNewsList = new ArrayList<>();
    VideosAdapter videosAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        addVideosNews();
        videosAdapter = new VideosAdapter(getActivity(), videoNewsList, new VideosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String list) {
                ((HomeActivity) getActivity()).navigation.Wf(NewsDetailActivity.class);
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        videoRecycler.setLayoutManager(mLayoutManager);
        videoRecycler.setAdapter(videosAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void addVideosNews() {
        videoNewsList.clear();
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
        videoNewsList.add("Heading 1");
    }
}
