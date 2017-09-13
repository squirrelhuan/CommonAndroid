package com.example.huan.commonandroid.biz.personcenter;

import android.content.Context;

import com.example.huan.commonandroid.bean.home.LoginResp;
import com.example.huan.commonandroid.biz.BasePresenter;
import com.example.huan.commonandroid.bridge.BridgeFactory;
import com.example.huan.commonandroid.bridge.Bridges;
import com.example.huan.commonandroid.bridge.cache.sharePref.EBSharedPrefManager;
import com.example.huan.commonandroid.bridge.cache.sharePref.EBSharedPrefUser;
import com.example.huan.commonandroid.bridge.http.DataManager;
import com.example.huan.commonandroid.bridge.http.OkHttpManager;
import com.example.huan.commonandroid.capabilities.http.ITRequestResult;
import com.example.huan.commonandroid.capabilities.http.Param;
import com.example.huan.commonandroid.constant.URLUtil;
import com.example.huan.commonandroid.bridge.security.SecurityManager;
import com.example.huan.commonandroid.retrofit.Book;
import com.example.huan.commonandroid.retrofit.Ret;
import com.example.huan.commonandroid.retrofit.RetrofitService;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * <功能详细描述>
 *
 * @author caoyinfei
 * @version [版本号, 2016/5/4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginPresenter extends BasePresenter<IUserLoginView> {

    private CompositeSubscription mCompositeSubscription;
    private DataManager manager;
    private Context mContext;

    public LoginPresenter(Context mContext) {
        this.mContext = mContext;
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }
    Ret mRet = null;
    public void login(String useName, String password) {
        //网络层
        mvpView.showLoading();
       SecurityManager securityManager = BridgeFactory.getBridge(Bridges.SECURITY);
        /* OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);


        httpManager.requestAsyncPostByTag(URLUtil.USER_LOGIN, getName(), new ITRequestResult<LoginResp>() {
                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccessful(LoginResp entity) {
                        mvpView.onSuccess();
                        EBSharedPrefManager manager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
                        manager.getKDPreferenceUserInfo().saveString(EBSharedPrefUser.USER_NAME, "abc");
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        mvpView.onError(errorMsg, "");
                    }

                }, LoginResp.class, new Param("username", useName),
                new Param("passwd", securityManager.encryptSHA(password.getBytes())));
*/

        mCompositeSubscription.add(manager.getLogin(useName, securityManager.encryptSHA(password.getBytes()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Ret>() {
                    @Override
                    public void onCompleted() {
                        if (mRet != null&&mRet.getRet().equals("1")) {
                            mvpView.onSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                       // mBookView.onError("请求失败！！");
                        mvpView.onError(e.getMessage(),"");
                    }

                    @Override
                    public void onNext(Ret book) {
                        mRet = book;
                    }
                })
        );


    }


}
