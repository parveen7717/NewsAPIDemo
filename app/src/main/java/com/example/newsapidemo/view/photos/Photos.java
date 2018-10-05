package com.example.newsapidemo.view.photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapidemo.R;
import com.example.newsapidemo.adapter.PhotosAdapter;
import com.example.newsapidemo.view.HomeActivity;
import com.example.newsapidemo.view.news_detail.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Photos extends Fragment {
    @BindView(R.id.photoRecycler)
    RecyclerView photoRecycler;
    Unbinder unbinder;
    List<String> photosNewsList = new ArrayList<>();
    PhotosAdapter photosAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photos_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        addPhotosNews();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        photoRecycler.setLayoutManager(mLayoutManager);
        photoRecycler.setAdapter(photosAdapter = new PhotosAdapter(getActivity(), photosNewsList, new PhotosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String list) {
                ((HomeActivity) getActivity()).navigation.Wf(NewsDetailActivity.class);

            }
        }));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void addPhotosNews() {
        photosNewsList.clear();
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
        photosNewsList.add("Heading 1");
    }
}
