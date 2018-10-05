package com.example.newsapidemo.view.politics;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapidemo.Interface.Response;
import com.example.newsapidemo.R;
import com.example.newsapidemo.adapter.PoliticsAdapter;
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

public class Politics extends Fragment implements Response{
    @BindView(R.id.politicsRecycler)
    RecyclerView politicsRecycler;
    Unbinder unbinder;
    List<Article> politicsNewsList = new ArrayList<>();
    PoliticsAdapter politicsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.politics_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((HomeActivity) getActivity()).apiController.regResponseInterface(this);
        politicsAdapter = new PoliticsAdapter(getActivity(), politicsNewsList, new PoliticsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Article list) {
                Bundle bundle = new Bundle();
                bundle.putString("newsImage", list.getUrlToImage());
                bundle.putString("webUrl", list.getUrl());
                ((HomeActivity) getActivity()).navigation.fWB(NewsDetailActivity.class, bundle);
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        politicsRecycler.setLayoutManager(mLayoutManager);
        politicsRecycler.setAdapter(politicsAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((HomeActivity) getActivity()).apiController.getPoliticalNews("Indian Politics", "52fc4e2ba1b648418ddffed8e22ffbe8");
            }
        }, 200);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        addPoliticsNews();
        politicsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void addPoliticsNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                politicsNewsList.clear();
                politicsNewsList.addAll(DataConstant.politicalNewsList);
            }
        }, "findPoliticalData").start();
    }

    @Override
    public void result(String key, String message) {
        if (key.equals("articles")) {
            if (message.equals("success")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addPoliticsNews();
                        politicsAdapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        }
    }
}
