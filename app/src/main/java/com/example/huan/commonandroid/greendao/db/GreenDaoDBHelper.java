package com.example.huan.commonandroid.greendao.db;

import android.content.Context;

import com.example.huan.commonandroid.greendao.DaoMaster;
import com.example.huan.commonandroid.greendao.DaoSession;


/**
 * Created by huan on 2017/9/12.
 */
public class GreenDaoDBHelper {
    private static GreenDaoDBHelper instance;
    private static Context mContext;

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,
                    "notes.db", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    private GreenDaoDBHelper() {
    }

    public static void init(Context context) {
        mContext = context;
        instance = new GreenDaoDBHelper();
        // 数据库对象
        DaoSession daoSession = getDaoSession(mContext);

    }

    public static GreenDaoDBHelper getInstance() {
        return instance;
    }

}
