package com.example.newsapidemo.util;

import com.example.newsapidemo.model.Article;

import java.util.ArrayList;
import java.util.List;

public class DataConstant {
    public static List<Article> articles = new ArrayList<>();
    public static List<Article> politicalNewsList = new ArrayList<>();


    public static void setArticles(List<Article> articles) {
        DataConstant.articles = articles;
    }

    public static List<Article> getArticles() {
        return articles;
    }
    public static void clearArticlesData(){
        articles.clear();
    }

}
