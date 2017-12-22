package com.odd.sportal.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.odd.sportal.model.NextLiveEventsModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEXT_LIVE_EVENTS_MODEL".
*/
public class NextLiveEventsModelDao extends AbstractDao<NextLiveEventsModel, Long> {

    public static final String TABLENAME = "NEXT_LIVE_EVENTS_MODEL";

    /**
     * Properties of entity NextLiveEventsModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property EVENT_ID = new Property(1, long.class, "EVENT_ID", false, "EVENT__ID");
    }


    public NextLiveEventsModelDao(DaoConfig config) {
        super(config);
    }
    
    public NextLiveEventsModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEXT_LIVE_EVENTS_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"EVENT__ID\" INTEGER NOT NULL );"); // 1: EVENT_ID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEXT_LIVE_EVENTS_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NextLiveEventsModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getEVENT_ID());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NextLiveEventsModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getEVENT_ID());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NextLiveEventsModel readEntity(Cursor cursor, int offset) {
        NextLiveEventsModel entity = new NextLiveEventsModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1) // EVENT_ID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NextLiveEventsModel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEVENT_ID(cursor.getLong(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NextLiveEventsModel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NextLiveEventsModel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NextLiveEventsModel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
