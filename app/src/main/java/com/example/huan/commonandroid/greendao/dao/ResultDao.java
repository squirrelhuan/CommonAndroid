package com.example.huan.commonandroid.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.example.huan.commonandroid.bean.greendao.Result;
import com.example.huan.commonandroid.greendao.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * 用户
 * Created by huan on 2017/9/12.
 */
public class ResultDao extends AbstractDao<Result, String> {

    public static final String TABLENAME = "gp_result";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "item_no");
        public final static Property Name = new Property(1, String.class, "name", false, "name_cn");
        public final static Property Result = new Property(2, String.class, "result", true, "result");
        public final static Property Rank = new Property(3, String.class, "rank", false, "rank");
        public final static Property Sampleid = new Property(4, String.class, "sampleid", true, "sample_id");
        public final static Property Avg_type = new Property(5, String.class, "avg_type", false, "avg_type");
    };


    public ResultDao(DaoConfig config) {
        super(config);
    }

    public ResultDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + TABLENAME +" (" + //
                "\"item_no\" INTEGER PRIMARY KEY ," + // 0: id
                "\"name\" TEXT);"); // 1: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + TABLENAME;
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Result entity) {
        stmt.clearBindings();

        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Result entity) {
        stmt.clearBindings();

        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        String result = entity.getResult();
        if (result != null) {
            stmt.bindString(3, id);
        }
        String rank = entity.getResult();
        if (rank != null) {
            stmt.bindString(4, id);
        }
        String sampleid = entity.getSampleid();
        if (sampleid != null) {
            stmt.bindString(5, id);
        }
        String avg_type = entity.getAvg_type();
        if (avg_type != null) {
            stmt.bindString(6, id);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }

    @Override
    public Result readEntity(Cursor cursor, int offset) {
        Result entity = new Result(
                cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0),
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1),
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2),
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3),
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4),
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5)
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, Result entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
    }


    @Override
    protected final String updateKeyAfterInsert(Result entity, long rowId) {
        entity.setId(rowId+"");
        return rowId+"";
    }

    @Override
    public String getKey(Result entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

}
