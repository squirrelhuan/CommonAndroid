package com.example.huan.commonandroid;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.huan.commonandroid.bean.greendao.Result;
import com.example.huan.commonandroid.bean.greendao.Sample;
import com.example.huan.commonandroid.bean.greendao.User;
import com.example.huan.commonandroid.bridge.BridgeFactory;
import com.example.huan.commonandroid.bridge.BridgeLifeCycleSetKeeper;
import com.example.huan.commonandroid.bridge.Bridges;
import com.example.huan.commonandroid.bridge.cache.localstorage.LocalFileStorageManager;
import com.example.huan.commonandroid.db.DBHelper;
import com.example.huan.commonandroid.greendao.DaoMaster;
import com.example.huan.commonandroid.greendao.DaoSession;
import com.example.huan.commonandroid.greendao.dao.ResultDao;
import com.example.huan.commonandroid.greendao.dao.SampleDao;
import com.example.huan.commonandroid.greendao.dao.UserDao;
import com.example.huan.commonandroid.util.ToastUtil;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <应用初始化> <功能详细描述>
 * 
 * @author cyf
 * @version [版本号, 2014-3-24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EBApplication extends Application
{
    /**
     * app实例
     */
    public static EBApplication ebApplication = null;
    
    /**
     * 本地activity栈
     */
    public static List<Activity> activitys = new ArrayList<Activity>();
    
    /**
     * 当前avtivity名称
     */
    public static String currentActivityName = "";

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        ebApplication = this;
        BridgeFactory.init(this);
        BridgeLifeCycleSetKeeper.getInstance().initOnApplicationCreate(this);
        LocalFileStorageManager manager = BridgeFactory.getBridge(Bridges.LOCAL_FILE_STORAGE);
      //  Picasso picasso = new Picasso.Builder(this).downloader(
       //         new OkHttpDownloader(new File(manager.getCacheImgFilePath(this)))).build();
      //  Picasso.setSingletonInstance(picasso);


        setDatabase();


        //mUserDao.getKey()
    }
    /**
     * 设置greenDao
     */
    private void setDatabase() {
        new DBHelper(getBaseContext());//初始化数据库文件
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "genes.db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

        UserDao mUserDao = getDaoSession().getUserDao();
        List<User> users = mUserDao.loadAll();
        Log.i("info","greendao");
        SampleDao sampleDao = getDaoSession().getSampleDao();
        List<Sample> samples = sampleDao.loadAll();
        Log.i("info","greendao");
        ResultDao resultDao = getDaoSession().getResultDao();
        List<Result> results = resultDao.loadAll();
        Log.i("info","greendao");
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        onDestory();
    }

    /**
     * 退出应用，清理内存
     */
    private void onDestory() {
        BridgeLifeCycleSetKeeper.getInstance().clearOnApplicationQuit();
        ToastUtil.destory();
    }


    /**
     * 
     * <添加> <功能详细描述>
     * 
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void addActivity(Activity activity)
    {
        activitys.add(activity);
    }
    
    /**
     * 
     * <删除>
     * <功能详细描述>
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void deleteActivity(Activity activity)
    {
        if (activity != null)
        {
            activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * greendao
     * @return
     */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    /**
     * greendao
     * @return
     */
    public SQLiteDatabase getDb() {
        return db;
    }

}
