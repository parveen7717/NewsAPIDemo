package com.example.newsapidemo.view.news_detail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.example.newsapidemo.R;
import com.example.newsapidemo.controller.AppNavigationController;
import com.example.newsapidemo.util.ImageLoad;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.newsWebView)
    WebView newsWebView;
    private AppNavigationController navigation;
    Toolbar toolbar;

    @Override
    public void onBackPressed() {
        navigation.backPressFrag();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_item_detail);
        ButterKnife.bind(this);
        navigation = new AppNavigationController(this);
        Bundle extra = getIntent().getExtras();
        String webUrl = extra.getString("webUrl");
        String newsImageUrl = extra.getString("newsImage");
        toolbar = findViewById(R.id.toolbar);
        toolbarLayout.findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        newsWebView.loadUrl(webUrl);
        if (newsImageUrl != null && !newsImageUrl.equals("")) {
            ImageLoad.imageLoad(newsImageUrl, new Target() {

                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    toolbarLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });

        }
    }

}
