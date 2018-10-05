package com.example.newsapidemo.apiservice;

import com.example.newsapidemo.model.Example;

import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//country=us&apiKey=52fc4e2ba1b648418ddffed8e22ffbe8
public interface NewsApi {

    @GET("top-headlines")
    Call <Example> getTopTrendingArticles(@Query("country") String country,@Query("apiKey") String apiKey);

    @GET("everything")
    Call <Example> getPoliticalNews(@Query("q") String country,@Query("apiKey") String apiKey);
}

