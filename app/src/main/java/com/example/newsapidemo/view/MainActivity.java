package com.example.newsapidemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.newsapidemo.apiservice.ApiService;
import com.example.newsapidemo.util.DataConstant;
import com.example.newsapidemo.R;
import com.example.newsapidemo.controller.APIController;
import com.example.newsapidemo.model.Article;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ApiService apiService;
    ListView articleListView;
    List<Article> list;
    APIController apiController;
    String[] articleList;
    ArrayAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        apiController = new APIController(this);
        articleListView = findViewById(R.id.articleID);
        apiController.getTopTrendingData("us","52fc4e2ba1b648418ddffed8e22ffbe8");
        getArticlesData();
    }

    public void getArticlesData() {
        list.clear();
        list =(DataConstant.getArticles());

        articleList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            articleList[i] = String.valueOf(list.get(i).getTitle());
            Log.i("article", articleList[i]);
        }


       newsAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, articleList);
       articleListView.setAdapter(newsAdapter);
       newsAdapter.notifyDataSetChanged();
    }

}
