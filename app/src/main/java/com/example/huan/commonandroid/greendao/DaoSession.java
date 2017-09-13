package com.example.huan.commonandroid.greendao;

import com.example.huan.commonandroid.bean.greendao.Result;
import com.example.huan.commonandroid.bean.greendao.Sample;
import com.example.huan.commonandroid.bean.greendao.User;
import com.example.huan.commonandroid.greendao.dao.ResultDao;
import com.example.huan.commonandroid.greendao.dao.SampleDao;
import com.example.huan.commonandroid.greendao.dao.UserDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

/**
 * Created by huan on 2017/9/12.
 */

public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig sampleDaoConfig;
    private final DaoConfig resultDaoConfig;

    private final UserDao userDao;
    private final SampleDao sampleDao;
    private final ResultDao resultDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);
        userDao = new UserDao(userDaoConfig, this);

        sampleDaoConfig = daoConfigMap.get(SampleDao.class).clone();
        sampleDaoConfig.initIdentityScope(type);
        sampleDao = new SampleDao(sampleDaoConfig,this);

        resultDaoConfig = daoConfigMap.get(ResultDao.class).clone();
        resultDaoConfig.initIdentityScope(type);
        resultDao = new ResultDao(resultDaoConfig,this);

        registerDao(User.class, userDao);
        registerDao(Sample.class, sampleDao);
        registerDao(Result.class, resultDao);
    }

    public void clear() {
        userDaoConfig.getIdentityScope().clear();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public SampleDao getSampleDao() {
        return sampleDao;
    }

   public ResultDao getResultDao() {
        return resultDao;
    }
}