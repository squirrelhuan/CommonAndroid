package com.example.huan.commonandroid.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.example.huan.commonandroid.bean.greendao.Sample;
import com.example.huan.commonandroid.bean.greendao.User;
import com.example.huan.commonandroid.greendao.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/** 报告
 * Created by huan on 2017/9/12.
 */
public class SampleDao extends AbstractDao<Sample, Long> {

    public static final String TABLENAME = "gp_users_samples";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", false, "sample_id");
        public final static Property Samplename = new Property(1, String.class, "samplename", false, "sample_name");
    };


    public SampleDao(DaoConfig config) {
        super(config);
    }

    public SampleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + TABLENAME +" (" + //
                "\"sample_id\" INTEGER ," + // 0: id
                "\"sample_name\" TEXT);"); // 1: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + TABLENAME;
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Sample entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String name = entity.getSamplename();
        if (name != null) {
            stmt.bindString(2, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Sample entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String name = entity.getSamplename();
        if (name != null) {
            stmt.bindString(2, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public Sample readEntity(Cursor cursor, int offset) {
        Sample entity = new Sample(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0),
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1)
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, Sample entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSamplename(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
    }

    @Override
    protected final Long updateKeyAfterInsert(Sample entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(Sample entity) {
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
