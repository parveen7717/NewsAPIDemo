package com.example.newsapidemo.view.my_city;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.newsapidemo.R;
import com.example.newsapidemo.adapter.TopNewsAdapter;
import com.example.newsapidemo.model.Article;
import com.example.newsapidemo.util.DataConstant;
import com.example.newsapidemo.view.HomeActivity;
import com.example.newsapidemo.view.news_detail.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyCity extends Fragment {
    @BindView(R.id.topNewsRecycler)
    RecyclerView topNewsRecycler;
    Unbinder unbinder;
    List<Article> topNewsList = new ArrayList<>();
    TopNewsAdapter topNewsAdapter;
    @BindView(R.id.mainScroll)
    NestedScrollView mainScroll;
    Article data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        addTopNews();
        topNewsAdapter = new TopNewsAdapter(getActivity(), topNewsList, new TopNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Article list) {
                ((HomeActivity)getActivity()).navigation.Wf(NewsDetailActivity.class);
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        topNewsRecycler.setLayoutManager(mLayoutManager);
        topNewsRecycler.setAdapter(topNewsAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void addTopNews() {
        topNewsList.clear();
        for (Article data: DataConstant.getArticles()) {
            this.data =data;
            break;
        }
        topNewsList.add(data);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainScroll.fullScroll(View.FOCUS_UP);
        mainScroll.smoothScrollTo(0,0);
    }
}
