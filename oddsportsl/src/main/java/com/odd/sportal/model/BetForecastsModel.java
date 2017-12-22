package com.odd.sportal.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 戴尔 on 2017/12/12.
 */
@Entity
public class BetForecastsModel implements Serializable {
    static  final  long serialVersionUID=42L;
    @Id(autoincrement = true)
    private Long id;
    private long ID;
    private long EVENT_ID;
    private long GAME_ID;
    private long FORECAST_CD;

    private String DESCR;
    private double ODDS;
    private long FC_STATUS;
    private long FC_WIN;


    private long REV_ID;
    private long ODDS_CHANGED;
    private long ISCURRENT;
    private Date  FC_FROM;

    private Date FC_TO;
    private long TEAM_ID;
    private long FC_PLAYER_ID;
    private String DESCR_EN;

    private String FC_NAME;
    private String DESCR_LOCAL;
    private  long FORDER;
    private long FC_UID;

    private double ODDS_NORMAL;
    private double FC_PARAM;
    @Generated(hash = 1903951645)
    public BetForecastsModel(Long id, long ID, long EVENT_ID, long GAME_ID,
            long FORECAST_CD, String DESCR, double ODDS, long FC_STATUS,
            long FC_WIN, long REV_ID, long ODDS_CHANGED, long ISCURRENT,
            Date FC_FROM, Date FC_TO, long TEAM_ID, long FC_PLAYER_ID,
            String DESCR_EN, String FC_NAME, String DESCR_LOCAL, long FORDER,
            long FC_UID, double ODDS_NORMAL, double FC_PARAM) {
        this.id = id;
        this.ID = ID;
        this.EVENT_ID = EVENT_ID;
        this.GAME_ID = GAME_ID;
        this.FORECAST_CD = FORECAST_CD;
        this.DESCR = DESCR;
        this.ODDS = ODDS;
        this.FC_STATUS = FC_STATUS;
        this.FC_WIN = FC_WIN;
        this.REV_ID = REV_ID;
        this.ODDS_CHANGED = ODDS_CHANGED;
        this.ISCURRENT = ISCURRENT;
        this.FC_FROM = FC_FROM;
        this.FC_TO = FC_TO;
        this.TEAM_ID = TEAM_ID;
        this.FC_PLAYER_ID = FC_PLAYER_ID;
        this.DESCR_EN = DESCR_EN;
        this.FC_NAME = FC_NAME;
        this.DESCR_LOCAL = DESCR_LOCAL;
        this.FORDER = FORDER;
        this.FC_UID = FC_UID;
        this.ODDS_NORMAL = ODDS_NORMAL;
        this.FC_PARAM = FC_PARAM;
    }
    @Generated(hash = 633495807)
    public BetForecastsModel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getID() {
        return this.ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }
    public long getEVENT_ID() {
        return this.EVENT_ID;
    }
    public void setEVENT_ID(long EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }
    public long getGAME_ID() {
        return this.GAME_ID;
    }
    public void setGAME_ID(long GAME_ID) {
        this.GAME_ID = GAME_ID;
    }
    public long getFORECAST_CD() {
        return this.FORECAST_CD;
    }
    public void setFORECAST_CD(long FORECAST_CD) {
        this.FORECAST_CD = FORECAST_CD;
    }
    public String getDESCR() {
        return this.DESCR;
    }
    public void setDESCR(String DESCR) {
        this.DESCR = DESCR;
    }
    public double getODDS() {
        return this.ODDS;
    }
    public void setODDS(double ODDS) {
        this.ODDS = ODDS;
    }
    public long getFC_STATUS() {
        return this.FC_STATUS;
    }
    public void setFC_STATUS(long FC_STATUS) {
        this.FC_STATUS = FC_STATUS;
    }
    public long getFC_WIN() {
        return this.FC_WIN;
    }
    public void setFC_WIN(long FC_WIN) {
        this.FC_WIN = FC_WIN;
    }
    public long getREV_ID() {
        return this.REV_ID;
    }
    public void setREV_ID(long REV_ID) {
        this.REV_ID = REV_ID;
    }
    public long getODDS_CHANGED() {
        return this.ODDS_CHANGED;
    }
    public void setODDS_CHANGED(long ODDS_CHANGED) {
        this.ODDS_CHANGED = ODDS_CHANGED;
    }
    public long getISCURRENT() {
        return this.ISCURRENT;
    }
    public void setISCURRENT(long ISCURRENT) {
        this.ISCURRENT = ISCURRENT;
    }
    public Date getFC_FROM() {
        return this.FC_FROM;
    }
    public void setFC_FROM(Date FC_FROM) {
        this.FC_FROM = FC_FROM;
    }
    public Date getFC_TO() {
        return this.FC_TO;
    }
    public void setFC_TO(Date FC_TO) {
        this.FC_TO = FC_TO;
    }
    public long getTEAM_ID() {
        return this.TEAM_ID;
    }
    public void setTEAM_ID(long TEAM_ID) {
        this.TEAM_ID = TEAM_ID;
    }
    public long getFC_PLAYER_ID() {
        return this.FC_PLAYER_ID;
    }
    public void setFC_PLAYER_ID(long FC_PLAYER_ID) {
        this.FC_PLAYER_ID = FC_PLAYER_ID;
    }
    public String getDESCR_EN() {
        return this.DESCR_EN;
    }
    public void setDESCR_EN(String DESCR_EN) {
        this.DESCR_EN = DESCR_EN;
    }
    public String getFC_NAME() {
        return this.FC_NAME;
    }
    public void setFC_NAME(String FC_NAME) {
        this.FC_NAME = FC_NAME;
    }
    public String getDESCR_LOCAL() {
        return this.DESCR_LOCAL;
    }
    public void setDESCR_LOCAL(String DESCR_LOCAL) {
        this.DESCR_LOCAL = DESCR_LOCAL;
    }
    public long getFORDER() {
        return this.FORDER;
    }
    public void setFORDER(long FORDER) {
        this.FORDER = FORDER;
    }
    public long getFC_UID() {
        return this.FC_UID;
    }
    public void setFC_UID(long FC_UID) {
        this.FC_UID = FC_UID;
    }
    public double getODDS_NORMAL() {
        return this.ODDS_NORMAL;
    }
    public void setODDS_NORMAL(double ODDS_NORMAL) {
        this.ODDS_NORMAL = ODDS_NORMAL;
    }
    public double getFC_PARAM() {
        return this.FC_PARAM;
    }
    public void setFC_PARAM(double FC_PARAM) {
        this.FC_PARAM = FC_PARAM;
    }

   

}
