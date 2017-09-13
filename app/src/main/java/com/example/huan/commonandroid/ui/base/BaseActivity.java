package com.example.huan.commonandroid.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huan.commonandroid.EBApplication;
import com.example.huan.commonandroid.R;
import com.example.huan.commonandroid.biz.BasePresenter;
import com.example.huan.commonandroid.biz.IMvpView;
import com.example.huan.commonandroid.bridge.BridgeFactory;
import com.example.huan.commonandroid.bridge.Bridges;
import com.example.huan.commonandroid.bridge.http.OkHttpManager;
import com.example.huan.commonandroid.constant.Event;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;


/**
 * <基础activity>
 *
 * @author caoyinfei
 * @version [版本号, 2014-3-24]
 * @see [相关类/方法]
 * @since [V1]
 */
public abstract class BaseActivity extends Activity implements CreateInit, PublishActivityCallBack, PresentationLayerFunc, IMvpView, OnClickListener {

    private PresentationLayerFuncHelper presentationLayerFuncHelper;

    /**
     * 返回按钮
     */
    private ImageView back;

    /**
     * 标题，右边字符
     */
    protected TextView title, right;

    public BasePresenter presenter;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

            boolean useThemestatusBarColor = false;//是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.green));
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        presentationLayerFuncHelper = new PresentationLayerFuncHelper(this);

        initViews();
        initListeners();
        initData();
        setHeader();
        EBApplication.ebApplication.addActivity(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void setHeader() {
        back = (ImageView) findViewById(R.id.common_title_back);
        title = (TextView) findViewById(R.id.common_title);
        right = (TextView) findViewById(R.id.common_title_right);
        back.setOnClickListener(this);
    }
    /**
     * 显示返回按钮
     * @param activity
     */
    protected void showBackButton(final Activity activity) {
        ImageView backimg = (ImageView) findViewById(R.id.common_title_back);
        backimg.setVisibility(View.VISIBLE);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_title_back:
                finish();
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEventMainThread(Event event) {

    }

    @Override
    protected void onResume() {
        EBApplication.ebApplication.currentActivityName = this.getClass().getName();
        super.onResume();
    }

    @Override
    public void startActivity(Class<?> openClass, Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void openActivityForResult(Class<?> openClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void setResultOk(Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) ;
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showToast(String msg) {
        presentationLayerFuncHelper.showToast(msg);
    }

    @Override
    public void showProgressDialog() {
        presentationLayerFuncHelper.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        presentationLayerFuncHelper.hideProgressDialog();
    }

    @Override
    public void showSoftKeyboard(View focusView) {
        presentationLayerFuncHelper.showSoftKeyboard(focusView);
    }

    @Override
    public void hideSoftKeyboard() {
        presentationLayerFuncHelper.hideSoftKeyboard();
    }

    @Override
    protected void onDestroy() {
        EBApplication.ebApplication.deleteActivity(this);
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView(this);
        }
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.cancelActivityRequest(TAG);
        super.onDestroy();
    }

}
