package com.odd.sportal.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import io.reactivex.Scheduler;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 戴尔 on 2017/12/14.
 */

@Entity
public class EventLiveModel  implements Serializable{
    static  final  long serialVersionUID=42L;
    @Id(autoincrement = true)
    private Long id;

    private long EVENT_ID;
    private long EVENT_CODE;
    private Date BET_START_DATE;
    private Date SCD_DATE;

    private Date ACTUAL_DATE;
    private long STATUS;
    private long TEAM1_ID;
    private long TEAM2_ID;

    private Date DISPLAY_DATE;
    private long DRAW_ID;
    private long TOURN_ID;
    private long EVENT_TYPE;


    private long LOTOS_CODE;
    private String EVT_COMMENT_UNC;
    private long USE_CLM_LEN;
    private double ODDS_1;


    private double ODDS_X;
    private double HANDICAP2;
    private double ODDS_2;
    private double HANDICAP1;

    private String ET_NAME;
    private String ET_CODE;
    private String ET_DESCR_EN;
    private String TNAME;

    private long TORDER;
    private long TWEB_ID;
    private String TEAM1_NAME;
    private String TEAM2_NAME;


    private String FILE_NAME;
    private long ISPLAYED;
    private Date LIVE_DT;
    private long DISCIPLINE_ID;

    private String SPORTS_IMAGE;
    private long SPORT_ORDER;
    private long ANTEPOST;
    private long TOURN_ORDER;
    @Generated(hash = 1227588243)
    public EventLiveModel(Long id, long EVENT_ID, long EVENT_CODE,
            Date BET_START_DATE, Date SCD_DATE, Date ACTUAL_DATE, long STATUS,
            long TEAM1_ID, long TEAM2_ID, Date DISPLAY_DATE, long DRAW_ID,
            long TOURN_ID, long EVENT_TYPE, long LOTOS_CODE, String EVT_COMMENT_UNC,
            long USE_CLM_LEN, double ODDS_1, double ODDS_X, double HANDICAP2,
            double ODDS_2, double HANDICAP1, String ET_NAME, String ET_CODE,
            String ET_DESCR_EN, String TNAME, long TORDER, long TWEB_ID,
            String TEAM1_NAME, String TEAM2_NAME, String FILE_NAME, long ISPLAYED,
            Date LIVE_DT, long DISCIPLINE_ID, String SPORTS_IMAGE, long SPORT_ORDER,
            long ANTEPOST, long TOURN_ORDER) {
        this.id = id;
        this.EVENT_ID = EVENT_ID;
        this.EVENT_CODE = EVENT_CODE;
        this.BET_START_DATE = BET_START_DATE;
        this.SCD_DATE = SCD_DATE;
        this.ACTUAL_DATE = ACTUAL_DATE;
        this.STATUS = STATUS;
        this.TEAM1_ID = TEAM1_ID;
        this.TEAM2_ID = TEAM2_ID;
        this.DISPLAY_DATE = DISPLAY_DATE;
        this.DRAW_ID = DRAW_ID;
        this.TOURN_ID = TOURN_ID;
        this.EVENT_TYPE = EVENT_TYPE;
        this.LOTOS_CODE = LOTOS_CODE;
        this.EVT_COMMENT_UNC = EVT_COMMENT_UNC;
        this.USE_CLM_LEN = USE_CLM_LEN;
        this.ODDS_1 = ODDS_1;
        this.ODDS_X = ODDS_X;
        this.HANDICAP2 = HANDICAP2;
        this.ODDS_2 = ODDS_2;
        this.HANDICAP1 = HANDICAP1;
        this.ET_NAME = ET_NAME;
        this.ET_CODE = ET_CODE;
        this.ET_DESCR_EN = ET_DESCR_EN;
        this.TNAME = TNAME;
        this.TORDER = TORDER;
        this.TWEB_ID = TWEB_ID;
        this.TEAM1_NAME = TEAM1_NAME;
        this.TEAM2_NAME = TEAM2_NAME;
        this.FILE_NAME = FILE_NAME;
        this.ISPLAYED = ISPLAYED;
        this.LIVE_DT = LIVE_DT;
        this.DISCIPLINE_ID = DISCIPLINE_ID;
        this.SPORTS_IMAGE = SPORTS_IMAGE;
        this.SPORT_ORDER = SPORT_ORDER;
        this.ANTEPOST = ANTEPOST;
        this.TOURN_ORDER = TOURN_ORDER;
    }
    @Generated(hash = 1196203828)
    public EventLiveModel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getEVENT_ID() {
        return this.EVENT_ID;
    }
    public void setEVENT_ID(long EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }
    public long getEVENT_CODE() {
        return this.EVENT_CODE;
    }
    public void setEVENT_CODE(long EVENT_CODE) {
        this.EVENT_CODE = EVENT_CODE;
    }
    public Date getBET_START_DATE() {
        return this.BET_START_DATE;
    }
    public void setBET_START_DATE(Date BET_START_DATE) {
        this.BET_START_DATE = BET_START_DATE;
    }
    public Date getSCD_DATE() {
        return this.SCD_DATE;
    }
    public void setSCD_DATE(Date SCD_DATE) {
        this.SCD_DATE = SCD_DATE;
    }
    public Date getACTUAL_DATE() {
        return this.ACTUAL_DATE;
    }
    public void setACTUAL_DATE(Date ACTUAL_DATE) {
        this.ACTUAL_DATE = ACTUAL_DATE;
    }
    public long getSTATUS() {
        return this.STATUS;
    }
    public void setSTATUS(long STATUS) {
        this.STATUS = STATUS;
    }
    public long getTEAM1_ID() {
        return this.TEAM1_ID;
    }
    public void setTEAM1_ID(long TEAM1_ID) {
        this.TEAM1_ID = TEAM1_ID;
    }
    public long getTEAM2_ID() {
        return this.TEAM2_ID;
    }
    public void setTEAM2_ID(long TEAM2_ID) {
        this.TEAM2_ID = TEAM2_ID;
    }
    public Date getDISPLAY_DATE() {
        return this.DISPLAY_DATE;
    }
    public void setDISPLAY_DATE(Date DISPLAY_DATE) {
        this.DISPLAY_DATE = DISPLAY_DATE;
    }
    public long getDRAW_ID() {
        return this.DRAW_ID;
    }
    public void setDRAW_ID(long DRAW_ID) {
        this.DRAW_ID = DRAW_ID;
    }
    public long getTOURN_ID() {
        return this.TOURN_ID;
    }
    public void setTOURN_ID(long TOURN_ID) {
        this.TOURN_ID = TOURN_ID;
    }
    public long getEVENT_TYPE() {
        return this.EVENT_TYPE;
    }
    public void setEVENT_TYPE(long EVENT_TYPE) {
        this.EVENT_TYPE = EVENT_TYPE;
    }
    public long getLOTOS_CODE() {
        return this.LOTOS_CODE;
    }
    public void setLOTOS_CODE(long LOTOS_CODE) {
        this.LOTOS_CODE = LOTOS_CODE;
    }
    public String getEVT_COMMENT_UNC() {
        return this.EVT_COMMENT_UNC;
    }
    public void setEVT_COMMENT_UNC(String EVT_COMMENT_UNC) {
        this.EVT_COMMENT_UNC = EVT_COMMENT_UNC;
    }
    public long getUSE_CLM_LEN() {
        return this.USE_CLM_LEN;
    }
    public void setUSE_CLM_LEN(long USE_CLM_LEN) {
        this.USE_CLM_LEN = USE_CLM_LEN;
    }
    public double getODDS_1() {
        return this.ODDS_1;
    }
    public void setODDS_1(double ODDS_1) {
        this.ODDS_1 = ODDS_1;
    }
    public double getODDS_X() {
        return this.ODDS_X;
    }
    public void setODDS_X(double ODDS_X) {
        this.ODDS_X = ODDS_X;
    }
    public double getHANDICAP2() {
        return this.HANDICAP2;
    }
    public void setHANDICAP2(double HANDICAP2) {
        this.HANDICAP2 = HANDICAP2;
    }
    public double getODDS_2() {
        return this.ODDS_2;
    }
    public void setODDS_2(double ODDS_2) {
        this.ODDS_2 = ODDS_2;
    }
    public double getHANDICAP1() {
        return this.HANDICAP1;
    }
    public void setHANDICAP1(double HANDICAP1) {
        this.HANDICAP1 = HANDICAP1;
    }
    public String getET_NAME() {
        return this.ET_NAME;
    }
    public void setET_NAME(String ET_NAME) {
        this.ET_NAME = ET_NAME;
    }
    public String getET_CODE() {
        return this.ET_CODE;
    }
    public void setET_CODE(String ET_CODE) {
        this.ET_CODE = ET_CODE;
    }
    public String getET_DESCR_EN() {
        return this.ET_DESCR_EN;
    }
    public void setET_DESCR_EN(String ET_DESCR_EN) {
        this.ET_DESCR_EN = ET_DESCR_EN;
    }
    public String getTNAME() {
        return this.TNAME;
    }
    public void setTNAME(String TNAME) {
        this.TNAME = TNAME;
    }
    public long getTORDER() {
        return this.TORDER;
    }
    public void setTORDER(long TORDER) {
        this.TORDER = TORDER;
    }
    public long getTWEB_ID() {
        return this.TWEB_ID;
    }
    public void setTWEB_ID(long TWEB_ID) {
        this.TWEB_ID = TWEB_ID;
    }
    public String getTEAM1_NAME() {
        return this.TEAM1_NAME;
    }
    public void setTEAM1_NAME(String TEAM1_NAME) {
        this.TEAM1_NAME = TEAM1_NAME;
    }
    public String getTEAM2_NAME() {
        return this.TEAM2_NAME;
    }
    public void setTEAM2_NAME(String TEAM2_NAME) {
        this.TEAM2_NAME = TEAM2_NAME;
    }
    public String getFILE_NAME() {
        return this.FILE_NAME;
    }
    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }
    public long getISPLAYED() {
        return this.ISPLAYED;
    }
    public void setISPLAYED(long ISPLAYED) {
        this.ISPLAYED = ISPLAYED;
    }
    public Date getLIVE_DT() {
        return this.LIVE_DT;
    }
    public void setLIVE_DT(Date LIVE_DT) {
        this.LIVE_DT = LIVE_DT;
    }
    public long getDISCIPLINE_ID() {
        return this.DISCIPLINE_ID;
    }
    public void setDISCIPLINE_ID(long DISCIPLINE_ID) {
        this.DISCIPLINE_ID = DISCIPLINE_ID;
    }
    public String getSPORTS_IMAGE() {
        return this.SPORTS_IMAGE;
    }
    public void setSPORTS_IMAGE(String SPORTS_IMAGE) {
        this.SPORTS_IMAGE = SPORTS_IMAGE;
    }
    public long getSPORT_ORDER() {
        return this.SPORT_ORDER;
    }
    public void setSPORT_ORDER(long SPORT_ORDER) {
        this.SPORT_ORDER = SPORT_ORDER;
    }
    public long getANTEPOST() {
        return this.ANTEPOST;
    }
    public void setANTEPOST(long ANTEPOST) {
        this.ANTEPOST = ANTEPOST;
    }
    public long getTOURN_ORDER() {
        return this.TOURN_ORDER;
    }
    public void setTOURN_ORDER(long TOURN_ORDER) {
        this.TOURN_ORDER = TOURN_ORDER;
    }


}
