package com.example.huan.commonandroid.retrofit;

import android.content.Context;
import android.util.Log;

import com.example.huan.commonandroid.constant.URLUtil;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class RetrofitHelper {

    private Context mCntext;

    private static OkHttpClient okHttpClient = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }
    private RetrofitHelper(Context mContext){
        mCntext = mContext;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {

        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("OkHttpClient","OkHttpMessage:"+message);
            }
        });
        loggingInterceptor.setLevel(level);

        okHttpClient = new OkHttpClient.Builder().addInterceptor
                (loggingInterceptor).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URLUtil.SERVER)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }
}
