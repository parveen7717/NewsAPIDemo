package com.example.newsapidemo.util;

import android.net.Uri;

import com.example.newsapidemo.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ImageLoad {
    public static void imageLoad(String url, Target imageView){
        Uri quizImgUri = Uri.parse(url);
        if(quizImgUri!=null) {
            Picasso.get().load(quizImgUri).placeholder(R.drawable.ic_dummy_image).error(R.drawable.ic_dummy_image).into(imageView);
        }
    }
}
