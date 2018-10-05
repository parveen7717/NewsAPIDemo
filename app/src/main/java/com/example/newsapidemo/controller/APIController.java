package com.example.newsapidemo.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.newsapidemo.alert.CustomAlertDialog;
import com.example.newsapidemo.apiservice.ApiService;
import com.example.newsapidemo.apiservice.NewsApi;
import com.example.newsapidemo.model.Article;
import com.example.newsapidemo.model.Example;
import com.example.newsapidemo.util.DataConstant;
import com.example.newsapidemo.util.LOGS;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.example.newsapidemo.util.DataConstant.politicalNewsList;

public class APIController {
    private Activity activity;
    private CustomAlertDialog loadingDialog;
    private com.example.newsapidemo.Interface.Response controllerResponse;

    public APIController(Activity activity) {
        this.activity = activity;
        loadingDialog = new CustomAlertDialog(activity);
    }


    public void getTopTrendingData(String country, String apiKey) {
        DataConstant.clearArticlesData();
       // loadingDialog.alertShow();
        final NewsApi apiService = ApiService.getAPI().create(NewsApi.class);
        Call<Example> call = apiService.getTopTrendingArticles(country, apiKey);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(@NonNull Call<Example> call, @NonNull retrofit2.Response<Example> response) {
                LOGS.system("----response---", String.valueOf(response));
                if (response.isSuccessful()) {
                  //  loadingDialog.alertHide();
                    List<Article> article = new ArrayList<>(response.body().getArticles());
                    DataConstant.setArticles(article);
                    controllerResponse.result("articles", "success");
                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Example> call, @NonNull Throwable t) {
                loadingDialog.alertHide();
                LOGS.system("-------------Articles--------retrofit", String.valueOf(t.getMessage()));
                Toast.makeText(activity, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPoliticalNews(String query,String apiKey){
        DataConstant.clearArticlesData();
       // loadingDialog.alertShow();
        final NewsApi apiService = ApiService.getAPI().create(NewsApi.class);
        Call<Example> call = apiService.getPoliticalNews(query, apiKey);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(@NonNull Call<Example> call, @NonNull retrofit2.Response<Example> response) {
                LOGS.system("----response---", String.valueOf(response));
                if (response.isSuccessful()) {
                 //   loadingDialog.alertHide();
                    List<Article> article = new ArrayList<>(response.body().getArticles());
                    politicalNewsList.addAll(article);
                    controllerResponse.result("articles", "success");
                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Example> call, @NonNull Throwable t) {
                loadingDialog.alertHide();
                LOGS.system("-------------Articles--------retrofit", String.valueOf(t.getMessage()));
                Toast.makeText(activity, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void regResponseInterface(com.example.newsapidemo.Interface.Response controllerResponse) {
        this.controllerResponse = controllerResponse;

    }

}
