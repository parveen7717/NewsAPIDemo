package com.example.newsapidemo.view.top_news;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.newsapidemo.Interface.Response;
import com.example.newsapidemo.R;
import com.example.newsapidemo.adapter.TopNewsAdapter;
import com.example.newsapidemo.model.Article;
import com.example.newsapidemo.util.DataConstant;
import com.example.newsapidemo.util.LOGS;
import com.example.newsapidemo.view.HomeActivity;
import com.example.newsapidemo.view.news_detail.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TopNews extends Fragment implements Response {
    @BindView(R.id.topNewsRecycler)
    RecyclerView topNewsRecycler;
    Unbinder unbinder;
    List<Article> topNewsList = new ArrayList<>();
    TopNewsAdapter topNewsAdapter;
    @BindView(R.id.mainScroll)
    NestedScrollView mainScroll;
    Article data;
    @BindView(R.id.seeMore)
    TextView seeMore;

    public TopNews() {
    }

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((HomeActivity) getActivity()).apiController.regResponseInterface(this);

        topNewsAdapter = new TopNewsAdapter(getActivity(), topNewsList, new TopNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Article list) {
                Bundle bundle = new Bundle();
                bundle.putString("newsImage", list.getUrlToImage());
                bundle.putString("webUrl", list.getUrl());
                ((HomeActivity) getActivity()).navigation.fWB(NewsDetailActivity.class, bundle);
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        topNewsRecycler.setLayoutManager(mLayoutManager);
        topNewsRecycler.setAdapter(topNewsAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((HomeActivity) getActivity()).apiController.getTopTrendingData("IN", "52fc4e2ba1b648418ddffed8e22ffbe8");

            }
        }, 200);
        seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void addTopNews() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                topNewsList.clear();
                topNewsList.addAll(DataConstant.articles);
            }
        }, "findData").start();

        /*for (Article articles: DataConstant.getArticles()) {
            LOGS.system("----------title--------------", data.getTitle());
            this.data =articles;
            break;
        }*/
        // topNewsList.add(data);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainScroll.fullScroll(View.FOCUS_UP);
        mainScroll.smoothScrollTo(0, 0);
        addTopNews();
        topNewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void result(String key, String message) {
        if (key.equals("articles")) {
            if (message.equals("success")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addTopNews();
                        topNewsAdapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        }
    }

}
