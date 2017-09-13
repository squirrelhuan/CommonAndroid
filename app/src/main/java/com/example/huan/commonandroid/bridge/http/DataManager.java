package com.example.huan.commonandroid.bridge.http;

import android.content.Context;


import com.example.huan.commonandroid.retrofit.Book;
import com.example.huan.commonandroid.retrofit.Ret;
import com.example.huan.commonandroid.retrofit.RetrofitHelper;
import com.example.huan.commonandroid.retrofit.RetrofitService;

import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public  Observable<Ret> getLogin(String name, String tag){
        return mRetrofitService.getLogin(name,tag);
    }
}
