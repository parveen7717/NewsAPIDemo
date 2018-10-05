package com.example.newsapidemo.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.newsapidemo.R;
import com.example.newsapidemo.adapter.ViewPagerAdapter;
import com.example.newsapidemo.controller.APIController;
import com.example.newsapidemo.controller.AppNavigationController;
import com.example.newsapidemo.view.business.Business;
import com.example.newsapidemo.view.country.Country;
import com.example.newsapidemo.view.entertainment.Entertainment;
import com.example.newsapidemo.view.health.Health;
import com.example.newsapidemo.view.my_city.MyCity;
import com.example.newsapidemo.view.photos.Photos;
import com.example.newsapidemo.view.politics.Politics;
import com.example.newsapidemo.view.sports.Sports;
import com.example.newsapidemo.view.top_news.TopNews;
import com.example.newsapidemo.view.videos.Videos;

import retrofit2.Call;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public AppNavigationController navigation;
    public APIController apiController;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigation = new AppNavigationController(this);
        apiController = new APIController(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TopNews(), "Top");
        adapter.addFrag(new MyCity(), "My City");
        adapter.addFrag(new Politics(), "Politics");
        adapter.addFrag(new Videos(), "Video");
        adapter.addFrag(new Photos(), "Photos");
        adapter.addFrag(new Entertainment(), "Entertainment");
        adapter.addFrag(new Country(), "India");
        adapter.addFrag(new Business(), "Business");
        adapter.addFrag(new Sports(), "Sports");
        adapter.addFrag(new Health(), "Health");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tabLayout.setupWithViewPager(viewPager);
    }
}
