package com.example.newsapidemo.util;

import android.util.Log;

public class LOGS {
    public static void system(String message,String result){
        Log.d("--"+message,result);
        //System.out.println("----------------------------------------------------------"+message+":"+result);
    }
}