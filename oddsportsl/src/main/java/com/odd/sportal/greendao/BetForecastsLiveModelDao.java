package com.odd.sportal.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.odd.sportal.model.BetForecastsLiveModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BET_FORECASTS_LIVE_MODEL".
*/
public class BetForecastsLiveModelDao extends AbstractDao<BetForecastsLiveModel, Long> {

    public static final String TABLENAME = "BET_FORECASTS_LIVE_MODEL";

    /**
     * Properties of entity BetForecastsLiveModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ID = new Property(1, long.class, "ID", false, "ID");
        public final static Property EVENT_ID = new Property(2, long.class, "EVENT_ID", false, "EVENT__ID");
        public final static Property GAME_ID = new Property(3, long.class, "GAME_ID", false, "GAME__ID");
        public final static Property FORECAST_CD = new Property(4, long.class, "FORECAST_CD", false, "FORECAST__CD");
        public final static Property DESCR = new Property(5, String.class, "DESCR", false, "DESCR");
        public final static Property ODDS = new Property(6, double.class, "ODDS", false, "ODDS");
        public final static Property FC_STATUS = new Property(7, long.class, "FC_STATUS", false, "FC__STATUS");
        public final static Property FC_WIN = new Property(8, long.class, "FC_WIN", false, "FC__WIN");
        public final static Property REV_ID = new Property(9, long.class, "REV_ID", false, "REV__ID");
        public final static Property ODDS_CHANGED = new Property(10, long.class, "ODDS_CHANGED", false, "ODDS__CHANGED");
        public final static Property ISCURRENT = new Property(11, long.class, "ISCURRENT", false, "ISCURRENT");
        public final static Property FC_FROM = new Property(12, java.util.Date.class, "FC_FROM", false, "FC__FROM");
        public final static Property FC_TO = new Property(13, java.util.Date.class, "FC_TO", false, "FC__TO");
        public final static Property TEAM_ID = new Property(14, long.class, "TEAM_ID", false, "TEAM__ID");
        public final static Property FC_PLAYER_ID = new Property(15, long.class, "FC_PLAYER_ID", false, "FC__PLAYER__ID");
        public final static Property DESCR_EN = new Property(16, String.class, "DESCR_EN", false, "DESCR__EN");
        public final static Property FC_NAME = new Property(17, String.class, "FC_NAME", false, "FC__NAME");
        public final static Property DESCR_LOCAL = new Property(18, String.class, "DESCR_LOCAL", false, "DESCR__LOCAL");
        public final static Property FORDER = new Property(19, long.class, "FORDER", false, "FORDER");
        public final static Property FC_UID = new Property(20, long.class, "FC_UID", false, "FC__UID");
        public final static Property FC_PARAM = new Property(21, double.class, "FC_PARAM", false, "FC__PARAM");
        public final static Property FC_MAP_CODE = new Property(22, long.class, "FC_MAP_CODE", false, "FC__MAP__CODE");
        public final static Property FC_MAP_ID = new Property(23, long.class, "FC_MAP_ID", false, "FC__MAP__ID");
        public final static Property ODDS_NORMAL = new Property(24, double.class, "ODDS_NORMAL", false, "ODDS__NORMAL");
    }


    public BetForecastsLiveModelDao(DaoConfig config) {
        super(config);
    }
    
    public BetForecastsLiveModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BET_FORECASTS_LIVE_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ID\" INTEGER NOT NULL ," + // 1: ID
                "\"EVENT__ID\" INTEGER NOT NULL ," + // 2: EVENT_ID
                "\"GAME__ID\" INTEGER NOT NULL ," + // 3: GAME_ID
                "\"FORECAST__CD\" INTEGER NOT NULL ," + // 4: FORECAST_CD
                "\"DESCR\" TEXT," + // 5: DESCR
                "\"ODDS\" REAL NOT NULL ," + // 6: ODDS
                "\"FC__STATUS\" INTEGER NOT NULL ," + // 7: FC_STATUS
                "\"FC__WIN\" INTEGER NOT NULL ," + // 8: FC_WIN
                "\"REV__ID\" INTEGER NOT NULL ," + // 9: REV_ID
                "\"ODDS__CHANGED\" INTEGER NOT NULL ," + // 10: ODDS_CHANGED
                "\"ISCURRENT\" INTEGER NOT NULL ," + // 11: ISCURRENT
                "\"FC__FROM\" INTEGER," + // 12: FC_FROM
                "\"FC__TO\" INTEGER," + // 13: FC_TO
                "\"TEAM__ID\" INTEGER NOT NULL ," + // 14: TEAM_ID
                "\"FC__PLAYER__ID\" INTEGER NOT NULL ," + // 15: FC_PLAYER_ID
                "\"DESCR__EN\" TEXT," + // 16: DESCR_EN
                "\"FC__NAME\" TEXT," + // 17: FC_NAME
                "\"DESCR__LOCAL\" TEXT," + // 18: DESCR_LOCAL
                "\"FORDER\" INTEGER NOT NULL ," + // 19: FORDER
                "\"FC__UID\" INTEGER NOT NULL ," + // 20: FC_UID
                "\"FC__PARAM\" REAL NOT NULL ," + // 21: FC_PARAM
                "\"FC__MAP__CODE\" INTEGER NOT NULL ," + // 22: FC_MAP_CODE
                "\"FC__MAP__ID\" INTEGER NOT NULL ," + // 23: FC_MAP_ID
                "\"ODDS__NORMAL\" REAL NOT NULL );"); // 24: ODDS_NORMAL
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BET_FORECASTS_LIVE_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BetForecastsLiveModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getID());
        stmt.bindLong(3, entity.getEVENT_ID());
        stmt.bindLong(4, entity.getGAME_ID());
        stmt.bindLong(5, entity.getFORECAST_CD());
 
        String DESCR = entity.getDESCR();
        if (DESCR != null) {
            stmt.bindString(6, DESCR);
        }
        stmt.bindDouble(7, entity.getODDS());
        stmt.bindLong(8, entity.getFC_STATUS());
        stmt.bindLong(9, entity.getFC_WIN());
        stmt.bindLong(10, entity.getREV_ID());
        stmt.bindLong(11, entity.getODDS_CHANGED());
        stmt.bindLong(12, entity.getISCURRENT());
 
        java.util.Date FC_FROM = entity.getFC_FROM();
        if (FC_FROM != null) {
            stmt.bindLong(13, FC_FROM.getTime());
        }
 
        java.util.Date FC_TO = entity.getFC_TO();
        if (FC_TO != null) {
            stmt.bindLong(14, FC_TO.getTime());
        }
        stmt.bindLong(15, entity.getTEAM_ID());
        stmt.bindLong(16, entity.getFC_PLAYER_ID());
 
        String DESCR_EN = entity.getDESCR_EN();
        if (DESCR_EN != null) {
            stmt.bindString(17, DESCR_EN);
        }
 
        String FC_NAME = entity.getFC_NAME();
        if (FC_NAME != null) {
            stmt.bindString(18, FC_NAME);
        }
 
        String DESCR_LOCAL = entity.getDESCR_LOCAL();
        if (DESCR_LOCAL != null) {
            stmt.bindString(19, DESCR_LOCAL);
        }
        stmt.bindLong(20, entity.getFORDER());
        stmt.bindLong(21, entity.getFC_UID());
        stmt.bindDouble(22, entity.getFC_PARAM());
        stmt.bindLong(23, entity.getFC_MAP_CODE());
        stmt.bindLong(24, entity.getFC_MAP_ID());
        stmt.bindDouble(25, entity.getODDS_NORMAL());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BetForecastsLiveModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getID());
        stmt.bindLong(3, entity.getEVENT_ID());
        stmt.bindLong(4, entity.getGAME_ID());
        stmt.bindLong(5, entity.getFORECAST_CD());
 
        String DESCR = entity.getDESCR();
        if (DESCR != null) {
            stmt.bindString(6, DESCR);
        }
        stmt.bindDouble(7, entity.getODDS());
        stmt.bindLong(8, entity.getFC_STATUS());
        stmt.bindLong(9, entity.getFC_WIN());
        stmt.bindLong(10, entity.getREV_ID());
        stmt.bindLong(11, entity.getODDS_CHANGED());
        stmt.bindLong(12, entity.getISCURRENT());
 
        java.util.Date FC_FROM = entity.getFC_FROM();
        if (FC_FROM != null) {
            stmt.bindLong(13, FC_FROM.getTime());
        }
 
        java.util.Date FC_TO = entity.getFC_TO();
        if (FC_TO != null) {
            stmt.bindLong(14, FC_TO.getTime());
        }
        stmt.bindLong(15, entity.getTEAM_ID());
        stmt.bindLong(16, entity.getFC_PLAYER_ID());
 
        String DESCR_EN = entity.getDESCR_EN();
        if (DESCR_EN != null) {
            stmt.bindString(17, DESCR_EN);
        }
 
        String FC_NAME = entity.getFC_NAME();
        if (FC_NAME != null) {
            stmt.bindString(18, FC_NAME);
        }
 
        String DESCR_LOCAL = entity.getDESCR_LOCAL();
        if (DESCR_LOCAL != null) {
            stmt.bindString(19, DESCR_LOCAL);
        }
        stmt.bindLong(20, entity.getFORDER());
        stmt.bindLong(21, entity.getFC_UID());
        stmt.bindDouble(22, entity.getFC_PARAM());
        stmt.bindLong(23, entity.getFC_MAP_CODE());
        stmt.bindLong(24, entity.getFC_MAP_ID());
        stmt.bindDouble(25, entity.getODDS_NORMAL());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BetForecastsLiveModel readEntity(Cursor cursor, int offset) {
        BetForecastsLiveModel entity = new BetForecastsLiveModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // ID
            cursor.getLong(offset + 2), // EVENT_ID
            cursor.getLong(offset + 3), // GAME_ID
            cursor.getLong(offset + 4), // FORECAST_CD
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // DESCR
            cursor.getDouble(offset + 6), // ODDS
            cursor.getLong(offset + 7), // FC_STATUS
            cursor.getLong(offset + 8), // FC_WIN
            cursor.getLong(offset + 9), // REV_ID
            cursor.getLong(offset + 10), // ODDS_CHANGED
            cursor.getLong(offset + 11), // ISCURRENT
            cursor.isNull(offset + 12) ? null : new java.util.Date(cursor.getLong(offset + 12)), // FC_FROM
            cursor.isNull(offset + 13) ? null : new java.util.Date(cursor.getLong(offset + 13)), // FC_TO
            cursor.getLong(offset + 14), // TEAM_ID
            cursor.getLong(offset + 15), // FC_PLAYER_ID
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // DESCR_EN
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // FC_NAME
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // DESCR_LOCAL
            cursor.getLong(offset + 19), // FORDER
            cursor.getLong(offset + 20), // FC_UID
            cursor.getDouble(offset + 21), // FC_PARAM
            cursor.getLong(offset + 22), // FC_MAP_CODE
            cursor.getLong(offset + 23), // FC_MAP_ID
            cursor.getDouble(offset + 24) // ODDS_NORMAL
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BetForecastsLiveModel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setID(cursor.getLong(offset + 1));
        entity.setEVENT_ID(cursor.getLong(offset + 2));
        entity.setGAME_ID(cursor.getLong(offset + 3));
        entity.setFORECAST_CD(cursor.getLong(offset + 4));
        entity.setDESCR(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setODDS(cursor.getDouble(offset + 6));
        entity.setFC_STATUS(cursor.getLong(offset + 7));
        entity.setFC_WIN(cursor.getLong(offset + 8));
        entity.setREV_ID(cursor.getLong(offset + 9));
        entity.setODDS_CHANGED(cursor.getLong(offset + 10));
        entity.setISCURRENT(cursor.getLong(offset + 11));
        entity.setFC_FROM(cursor.isNull(offset + 12) ? null : new java.util.Date(cursor.getLong(offset + 12)));
        entity.setFC_TO(cursor.isNull(offset + 13) ? null : new java.util.Date(cursor.getLong(offset + 13)));
        entity.setTEAM_ID(cursor.getLong(offset + 14));
        entity.setFC_PLAYER_ID(cursor.getLong(offset + 15));
        entity.setDESCR_EN(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setFC_NAME(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setDESCR_LOCAL(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setFORDER(cursor.getLong(offset + 19));
        entity.setFC_UID(cursor.getLong(offset + 20));
        entity.setFC_PARAM(cursor.getDouble(offset + 21));
        entity.setFC_MAP_CODE(cursor.getLong(offset + 22));
        entity.setFC_MAP_ID(cursor.getLong(offset + 23));
        entity.setODDS_NORMAL(cursor.getDouble(offset + 24));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BetForecastsLiveModel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BetForecastsLiveModel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BetForecastsLiveModel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
