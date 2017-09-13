package com.example.huan.commonandroid.retrofit;

import com.example.huan.commonandroid.constant.URLUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huan on 2017/9/12.
 */

public interface RetrofitService {

    @POST(URLUtil.USER_LOGIN)
    Observable<Ret> getLogin(@Query("username") String name,
                                    @Query("passwd") String tag);

}

