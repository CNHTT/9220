package com.odd.sportal.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 戴尔 on 2017/12/11.
 */

@Entity
public class EventModel implements Serializable {
    static  final  long serialVersionUID=42L;
    @Id(autoincrement = true)
    private Long id;
    private Date LIVE_DT;

    private long ISPLAYED;

    private String FILE_NAME;

    private String TEAM2_NAME;

    private String TEAM1_NAME;

    private String TNAME;

    private String ET_DESCR_EN;

    private String  ET_NAME;

    private double HANDICAP2;

    private double HANDICAP1;

    private double ODDS_2;

    private double ODDS_X;

    private double ODDS_1;

    private long USE_CLM_LEN;

    private String EVT_COMMENT_UNC;

    private long LOTOS_CODE;

    private long EVENT_TYPE;

    private long TOURN_ID;

    private long DRAW_ID;

    private long DISPLAY_ORDER;

    //20131414
    private Date DISPLAY_DATE;

    private long TEAM2_ID;

    private long TEAM1_ID;

    private long STATUS;

    private Date ACTUAL_DATE;

    private Date SCD_DATE;

    //20131010
    private Date BET_START_DATE;

    private long EVENT_CODE;

    private long EVENT_ID;

    @Generated(hash = 840276890)
    public EventModel(Long id, Date LIVE_DT, long ISPLAYED, String FILE_NAME,
            String TEAM2_NAME, String TEAM1_NAME, String TNAME, String ET_DESCR_EN,
            String ET_NAME, double HANDICAP2, double HANDICAP1, double ODDS_2,
            double ODDS_X, double ODDS_1, long USE_CLM_LEN, String EVT_COMMENT_UNC,
            long LOTOS_CODE, long EVENT_TYPE, long TOURN_ID, long DRAW_ID,
            long DISPLAY_ORDER, Date DISPLAY_DATE, long TEAM2_ID, long TEAM1_ID,
            long STATUS, Date ACTUAL_DATE, Date SCD_DATE, Date BET_START_DATE,
            long EVENT_CODE, long EVENT_ID) {
        this.id = id;
        this.LIVE_DT = LIVE_DT;
        this.ISPLAYED = ISPLAYED;
        this.FILE_NAME = FILE_NAME;
        this.TEAM2_NAME = TEAM2_NAME;
        this.TEAM1_NAME = TEAM1_NAME;
        this.TNAME = TNAME;
        this.ET_DESCR_EN = ET_DESCR_EN;
        this.ET_NAME = ET_NAME;
        this.HANDICAP2 = HANDICAP2;
        this.HANDICAP1 = HANDICAP1;
        this.ODDS_2 = ODDS_2;
        this.ODDS_X = ODDS_X;
        this.ODDS_1 = ODDS_1;
        this.USE_CLM_LEN = USE_CLM_LEN;
        this.EVT_COMMENT_UNC = EVT_COMMENT_UNC;
        this.LOTOS_CODE = LOTOS_CODE;
        this.EVENT_TYPE = EVENT_TYPE;
        this.TOURN_ID = TOURN_ID;
        this.DRAW_ID = DRAW_ID;
        this.DISPLAY_ORDER = DISPLAY_ORDER;
        this.DISPLAY_DATE = DISPLAY_DATE;
        this.TEAM2_ID = TEAM2_ID;
        this.TEAM1_ID = TEAM1_ID;
        this.STATUS = STATUS;
        this.ACTUAL_DATE = ACTUAL_DATE;
        this.SCD_DATE = SCD_DATE;
        this.BET_START_DATE = BET_START_DATE;
        this.EVENT_CODE = EVENT_CODE;
        this.EVENT_ID = EVENT_ID;
    }

    @Generated(hash = 1307476520)
    public EventModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLIVE_DT() {
        return this.LIVE_DT;
    }

    public void setLIVE_DT(Date LIVE_DT) {
        this.LIVE_DT = LIVE_DT;
    }

    public long getISPLAYED() {
        return this.ISPLAYED;
    }

    public void setISPLAYED(long ISPLAYED) {
        this.ISPLAYED = ISPLAYED;
    }

    public String getFILE_NAME() {
        return this.FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getTEAM2_NAME() {
        return this.TEAM2_NAME;
    }

    public void setTEAM2_NAME(String TEAM2_NAME) {
        this.TEAM2_NAME = TEAM2_NAME;
    }

    public String getTEAM1_NAME() {
        return this.TEAM1_NAME;
    }

    public void setTEAM1_NAME(String TEAM1_NAME) {
        this.TEAM1_NAME = TEAM1_NAME;
    }

    public String getTNAME() {
        return this.TNAME;
    }

    public void setTNAME(String TNAME) {
        this.TNAME = TNAME;
    }

    public String getET_DESCR_EN() {
        return this.ET_DESCR_EN;
    }

    public void setET_DESCR_EN(String ET_DESCR_EN) {
        this.ET_DESCR_EN = ET_DESCR_EN;
    }

    public String getET_NAME() {
        return this.ET_NAME;
    }

    public void setET_NAME(String ET_NAME) {
        this.ET_NAME = ET_NAME;
    }

    public double getHANDICAP2() {
        return this.HANDICAP2;
    }

    public void setHANDICAP2(double HANDICAP2) {
        this.HANDICAP2 = HANDICAP2;
    }

    public double getHANDICAP1() {
        return this.HANDICAP1;
    }

    public void setHANDICAP1(double HANDICAP1) {
        this.HANDICAP1 = HANDICAP1;
    }

    public double getODDS_2() {
        return this.ODDS_2;
    }

    public void setODDS_2(double ODDS_2) {
        this.ODDS_2 = ODDS_2;
    }

    public double getODDS_X() {
        return this.ODDS_X;
    }

    public void setODDS_X(double ODDS_X) {
        this.ODDS_X = ODDS_X;
    }

    public double getODDS_1() {
        return this.ODDS_1;
    }

    public void setODDS_1(double ODDS_1) {
        this.ODDS_1 = ODDS_1;
    }

    public long getUSE_CLM_LEN() {
        return this.USE_CLM_LEN;
    }

    public void setUSE_CLM_LEN(long USE_CLM_LEN) {
        this.USE_CLM_LEN = USE_CLM_LEN;
    }

    public String getEVT_COMMENT_UNC() {
        return this.EVT_COMMENT_UNC;
    }

    public void setEVT_COMMENT_UNC(String EVT_COMMENT_UNC) {
        this.EVT_COMMENT_UNC = EVT_COMMENT_UNC;
    }

    public long getLOTOS_CODE() {
        return this.LOTOS_CODE;
    }

    public void setLOTOS_CODE(long LOTOS_CODE) {
        this.LOTOS_CODE = LOTOS_CODE;
    }

    public long getEVENT_TYPE() {
        return this.EVENT_TYPE;
    }

    public void setEVENT_TYPE(long EVENT_TYPE) {
        this.EVENT_TYPE = EVENT_TYPE;
    }

    public long getTOURN_ID() {
        return this.TOURN_ID;
    }

    public void setTOURN_ID(long TOURN_ID) {
        this.TOURN_ID = TOURN_ID;
    }

    public long getDRAW_ID() {
        return this.DRAW_ID;
    }

    public void setDRAW_ID(long DRAW_ID) {
        this.DRAW_ID = DRAW_ID;
    }

    public long getDISPLAY_ORDER() {
        return this.DISPLAY_ORDER;
    }

    public void setDISPLAY_ORDER(long DISPLAY_ORDER) {
        this.DISPLAY_ORDER = DISPLAY_ORDER;
    }

    public Date getDISPLAY_DATE() {
        return this.DISPLAY_DATE;
    }

    public void setDISPLAY_DATE(Date DISPLAY_DATE) {
        this.DISPLAY_DATE = DISPLAY_DATE;
    }

    public long getTEAM2_ID() {
        return this.TEAM2_ID;
    }

    public void setTEAM2_ID(long TEAM2_ID) {
        this.TEAM2_ID = TEAM2_ID;
    }

    public long getTEAM1_ID() {
        return this.TEAM1_ID;
    }

    public void setTEAM1_ID(long TEAM1_ID) {
        this.TEAM1_ID = TEAM1_ID;
    }

    public long getSTATUS() {
        return this.STATUS;
    }

    public void setSTATUS(long STATUS) {
        this.STATUS = STATUS;
    }

    public Date getACTUAL_DATE() {
        return this.ACTUAL_DATE;
    }

    public void setACTUAL_DATE(Date ACTUAL_DATE) {
        this.ACTUAL_DATE = ACTUAL_DATE;
    }

    public Date getSCD_DATE() {
        return this.SCD_DATE;
    }

    public void setSCD_DATE(Date SCD_DATE) {
        this.SCD_DATE = SCD_DATE;
    }

    public Date getBET_START_DATE() {
        return this.BET_START_DATE;
    }

    public void setBET_START_DATE(Date BET_START_DATE) {
        this.BET_START_DATE = BET_START_DATE;
    }

    public long getEVENT_CODE() {
        return this.EVENT_CODE;
    }

    public void setEVENT_CODE(long EVENT_CODE) {
        this.EVENT_CODE = EVENT_CODE;
    }

    public long getEVENT_ID() {
        return this.EVENT_ID;
    }

    public void setEVENT_ID(long EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }
}
