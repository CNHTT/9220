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
public class GameModel implements Serializable {
    static  final  long serialVersionUID=42L;
    @Id(autoincrement = true)
    private Long id;
    private long ID;
    private long EVENT_ID;
    private long GAME_ID;
    private long STATUS;

    private Date START_DATE;
    private Date END_DATE;
    private long EE;
    private String GAME_DESCR;

    private long GAME_ORDER;
    private double HANDICAP1;
    private String CHART_NAME;
    private  long SPECIAL;

    private long ISLIVE;
    private String  CHART_DESCR;
    private long SC1;
    private long SC2;

    private long DISPLAY_FLAG;
    private String GAME_NAME;
    private String FC_TYPE;
    @Generated(hash = 1614544507)
    public GameModel(Long id, long ID, long EVENT_ID, long GAME_ID, long STATUS,
            Date START_DATE, Date END_DATE, long EE, String GAME_DESCR,
            long GAME_ORDER, double HANDICAP1, String CHART_NAME, long SPECIAL,
            long ISLIVE, String CHART_DESCR, long SC1, long SC2, long DISPLAY_FLAG,
            String GAME_NAME, String FC_TYPE) {
        this.id = id;
        this.ID = ID;
        this.EVENT_ID = EVENT_ID;
        this.GAME_ID = GAME_ID;
        this.STATUS = STATUS;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.EE = EE;
        this.GAME_DESCR = GAME_DESCR;
        this.GAME_ORDER = GAME_ORDER;
        this.HANDICAP1 = HANDICAP1;
        this.CHART_NAME = CHART_NAME;
        this.SPECIAL = SPECIAL;
        this.ISLIVE = ISLIVE;
        this.CHART_DESCR = CHART_DESCR;
        this.SC1 = SC1;
        this.SC2 = SC2;
        this.DISPLAY_FLAG = DISPLAY_FLAG;
        this.GAME_NAME = GAME_NAME;
        this.FC_TYPE = FC_TYPE;
    }
    @Generated(hash = 1977967076)
    public GameModel() {
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
    public long getSTATUS() {
        return this.STATUS;
    }
    public void setSTATUS(long STATUS) {
        this.STATUS = STATUS;
    }
    public Date getSTART_DATE() {
        return this.START_DATE;
    }
    public void setSTART_DATE(Date START_DATE) {
        this.START_DATE = START_DATE;
    }
    public Date getEND_DATE() {
        return this.END_DATE;
    }
    public void setEND_DATE(Date END_DATE) {
        this.END_DATE = END_DATE;
    }
    public long getEE() {
        return this.EE;
    }
    public void setEE(long EE) {
        this.EE = EE;
    }
    public String getGAME_DESCR() {
        return this.GAME_DESCR;
    }
    public void setGAME_DESCR(String GAME_DESCR) {
        this.GAME_DESCR = GAME_DESCR;
    }
    public long getGAME_ORDER() {
        return this.GAME_ORDER;
    }
    public void setGAME_ORDER(long GAME_ORDER) {
        this.GAME_ORDER = GAME_ORDER;
    }
    public double getHANDICAP1() {
        return this.HANDICAP1;
    }
    public void setHANDICAP1(double HANDICAP1) {
        this.HANDICAP1 = HANDICAP1;
    }
    public String getCHART_NAME() {
        return this.CHART_NAME;
    }
    public void setCHART_NAME(String CHART_NAME) {
        this.CHART_NAME = CHART_NAME;
    }
    public long getSPECIAL() {
        return this.SPECIAL;
    }
    public void setSPECIAL(long SPECIAL) {
        this.SPECIAL = SPECIAL;
    }
    public long getISLIVE() {
        return this.ISLIVE;
    }
    public void setISLIVE(long ISLIVE) {
        this.ISLIVE = ISLIVE;
    }
    public String getCHART_DESCR() {
        return this.CHART_DESCR;
    }
    public void setCHART_DESCR(String CHART_DESCR) {
        this.CHART_DESCR = CHART_DESCR;
    }
    public long getSC1() {
        return this.SC1;
    }
    public void setSC1(long SC1) {
        this.SC1 = SC1;
    }
    public long getSC2() {
        return this.SC2;
    }
    public void setSC2(long SC2) {
        this.SC2 = SC2;
    }
    public long getDISPLAY_FLAG() {
        return this.DISPLAY_FLAG;
    }
    public void setDISPLAY_FLAG(long DISPLAY_FLAG) {
        this.DISPLAY_FLAG = DISPLAY_FLAG;
    }
    public String getGAME_NAME() {
        return this.GAME_NAME;
    }
    public void setGAME_NAME(String GAME_NAME) {
        this.GAME_NAME = GAME_NAME;
    }
    public String getFC_TYPE() {
        return this.FC_TYPE;
    }
    public void setFC_TYPE(String FC_TYPE) {
        this.FC_TYPE = FC_TYPE;
    }

}
