package com.odd.sportal.model;

import java.util.Date;

/**
 * Created by 戴尔 on 2017/12/14.
 */

public class EvtPreGameModel {
    private long EVENT_ID;
    private long EVENT_CODE;
    private Date BET_START_DATE;
    private Date SCD_DATE;

    private Date ACTUAL_DATE;
    private long STATUS;
    private long TEAM1_ID;
    private long TEAM2_ID;

    private Date DISPLAY_DATE;
    private long DISPLAY_ORDER;
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
    private String et_code;
    private String ET_DESCR_EN;
    private String TNAME;

    private long TORDER;
    private long TWEB_ID;
    private String TEAM1_NAME;
    private String TEAM2_NAME;


    private String FILE_NAME;
    private long isPlayed;
    private Date LIVE_DT;
    private long DISCIPLINE_ID;

    private String SPORTS_IMAGE;
    private long SPORT_ORDER;
    private long ANTEPOST;
    private long TOURN_ORDER;


    public long getEVENT_ID() {
        return EVENT_ID;
    }

    public void setEVENT_ID(long EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }

    public long getEVENT_CODE() {
        return EVENT_CODE;
    }

    public void setEVENT_CODE(long EVENT_CODE) {
        this.EVENT_CODE = EVENT_CODE;
    }

    public Date getBET_START_DATE() {
        return BET_START_DATE;
    }

    public void setBET_START_DATE(Date BET_START_DATE) {
        this.BET_START_DATE = BET_START_DATE;
    }

    public Date getSCD_DATE() {
        return SCD_DATE;
    }

    public void setSCD_DATE(Date SCD_DATE) {
        this.SCD_DATE = SCD_DATE;
    }

    public Date getACTUAL_DATE() {
        return ACTUAL_DATE;
    }

    public void setACTUAL_DATE(Date ACTUAL_DATE) {
        this.ACTUAL_DATE = ACTUAL_DATE;
    }

    public long getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(long STATUS) {
        this.STATUS = STATUS;
    }

    public long getTEAM1_ID() {
        return TEAM1_ID;
    }

    public void setTEAM1_ID(long TEAM1_ID) {
        this.TEAM1_ID = TEAM1_ID;
    }

    public long getTEAM2_ID() {
        return TEAM2_ID;
    }

    public void setTEAM2_ID(long TEAM2_ID) {
        this.TEAM2_ID = TEAM2_ID;
    }

    public Date getDISPLAY_DATE() {
        return DISPLAY_DATE;
    }

    public void setDISPLAY_DATE(Date DISPLAY_DATE) {
        this.DISPLAY_DATE = DISPLAY_DATE;
    }

    public long getDISPLAY_ORDER() {
        return DISPLAY_ORDER;
    }

    public void setDISPLAY_ORDER(long DISPLAY_ORDER) {
        this.DISPLAY_ORDER = DISPLAY_ORDER;
    }

    public long getDRAW_ID() {
        return DRAW_ID;
    }

    public void setDRAW_ID(long DRAW_ID) {
        this.DRAW_ID = DRAW_ID;
    }

    public long getTOURN_ID() {
        return TOURN_ID;
    }

    public void setTOURN_ID(long TOURN_ID) {
        this.TOURN_ID = TOURN_ID;
    }

    public long getEVENT_TYPE() {
        return EVENT_TYPE;
    }

    public void setEVENT_TYPE(long EVENT_TYPE) {
        this.EVENT_TYPE = EVENT_TYPE;
    }

    public long getLOTOS_CODE() {
        return LOTOS_CODE;
    }

    public void setLOTOS_CODE(long LOTOS_CODE) {
        this.LOTOS_CODE = LOTOS_CODE;
    }

    public String getEVT_COMMENT_UNC() {
        return EVT_COMMENT_UNC;
    }

    public void setEVT_COMMENT_UNC(String EVT_COMMENT_UNC) {
        this.EVT_COMMENT_UNC = EVT_COMMENT_UNC;
    }

    public long getUSE_CLM_LEN() {
        return USE_CLM_LEN;
    }

    public void setUSE_CLM_LEN(long USE_CLM_LEN) {
        this.USE_CLM_LEN = USE_CLM_LEN;
    }

    public double getODDS_1() {
        return ODDS_1;
    }

    public void setODDS_1(double ODDS_1) {
        this.ODDS_1 = ODDS_1;
    }

    public double getODDS_X() {
        return ODDS_X;
    }

    public void setODDS_X(double ODDS_X) {
        this.ODDS_X = ODDS_X;
    }

    public double getHANDICAP2() {
        return HANDICAP2;
    }

    public void setHANDICAP2(double HANDICAP2) {
        this.HANDICAP2 = HANDICAP2;
    }

    public double getODDS_2() {
        return ODDS_2;
    }

    public void setODDS_2(double ODDS_2) {
        this.ODDS_2 = ODDS_2;
    }

    public double getHANDICAP1() {
        return HANDICAP1;
    }

    public void setHANDICAP1(double HANDICAP1) {
        this.HANDICAP1 = HANDICAP1;
    }

    public String getET_NAME() {
        return ET_NAME;
    }

    public void setET_NAME(String ET_NAME) {
        this.ET_NAME = ET_NAME;
    }

    public String getEt_code() {
        return et_code;
    }

    public void setEt_code(String et_code) {
        this.et_code = et_code;
    }

    public String getET_DESCR_EN() {
        return ET_DESCR_EN;
    }

    public void setET_DESCR_EN(String ET_DESCR_EN) {
        this.ET_DESCR_EN = ET_DESCR_EN;
    }

    public String getTNAME() {
        return TNAME;
    }

    public void setTNAME(String TNAME) {
        this.TNAME = TNAME;
    }

    public long getTORDER() {
        return TORDER;
    }

    public void setTORDER(long TORDER) {
        this.TORDER = TORDER;
    }

    public long getTWEB_ID() {
        return TWEB_ID;
    }

    public void setTWEB_ID(long TWEB_ID) {
        this.TWEB_ID = TWEB_ID;
    }

    public String getTEAM1_NAME() {
        return TEAM1_NAME;
    }

    public void setTEAM1_NAME(String TEAM1_NAME) {
        this.TEAM1_NAME = TEAM1_NAME;
    }

    public String getTEAM2_NAME() {
        return TEAM2_NAME;
    }

    public void setTEAM2_NAME(String TEAM2_NAME) {
        this.TEAM2_NAME = TEAM2_NAME;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public long getIsPlayed() {
        return isPlayed;
    }

    public void setIsPlayed(long isPlayed) {
        this.isPlayed = isPlayed;
    }

    public Date getLIVE_DT() {
        return LIVE_DT;
    }

    public void setLIVE_DT(Date LIVE_DT) {
        this.LIVE_DT = LIVE_DT;
    }

    public long getDISCIPLINE_ID() {
        return DISCIPLINE_ID;
    }

    public void setDISCIPLINE_ID(long DISCIPLINE_ID) {
        this.DISCIPLINE_ID = DISCIPLINE_ID;
    }

    public String getSPORTS_IMAGE() {
        return SPORTS_IMAGE;
    }

    public void setSPORTS_IMAGE(String SPORTS_IMAGE) {
        this.SPORTS_IMAGE = SPORTS_IMAGE;
    }

    public long getSPORT_ORDER() {
        return SPORT_ORDER;
    }

    public void setSPORT_ORDER(long SPORT_ORDER) {
        this.SPORT_ORDER = SPORT_ORDER;
    }

    public long getANTEPOST() {
        return ANTEPOST;
    }

    public void setANTEPOST(long ANTEPOST) {
        this.ANTEPOST = ANTEPOST;
    }

    public long getTOURN_ORDER() {
        return TOURN_ORDER;
    }

    public void setTOURN_ORDER(long TOURN_ORDER) {
        this.TOURN_ORDER = TOURN_ORDER;
    }
}
