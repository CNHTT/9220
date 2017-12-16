package com.odd.sportal.model;

/**
 * Created by 戴尔 on 2017/12/14.
 */

public class EventTypeTranslation {

    /**
     * ID : 23
     * ETT_ID : 29
     * ETT_LANG : CY
     * ET_ID : 101
     * ET_NAME : Money
     * ET_NAME_ABB :
     * ET_NAME_LC :
     * WEB_ID : 0
     */

    private long ID;
    private long ETT_ID;
    private String ETT_LANG;
    private long ET_ID;
    private String ET_NAME;
    private String ET_NAME_ABB;
    private String ET_NAME_LC;
    private long WEB_ID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getETT_ID() {
        return ETT_ID;
    }

    public void setETT_ID(long ETT_ID) {
        this.ETT_ID = ETT_ID;
    }

    public String getETT_LANG() {
        return ETT_LANG;
    }

    public void setETT_LANG(String ETT_LANG) {
        this.ETT_LANG = ETT_LANG;
    }

    public long getET_ID() {
        return ET_ID;
    }

    public void setET_ID(long ET_ID) {
        this.ET_ID = ET_ID;
    }

    public String getET_NAME() {
        return ET_NAME;
    }

    public void setET_NAME(String ET_NAME) {
        this.ET_NAME = ET_NAME;
    }

    public String getET_NAME_ABB() {
        return ET_NAME_ABB;
    }

    public void setET_NAME_ABB(String ET_NAME_ABB) {
        this.ET_NAME_ABB = ET_NAME_ABB;
    }

    public String getET_NAME_LC() {
        return ET_NAME_LC;
    }

    public void setET_NAME_LC(String ET_NAME_LC) {
        this.ET_NAME_LC = ET_NAME_LC;
    }

    public long getWEB_ID() {
        return WEB_ID;
    }

    public void setWEB_ID(long WEB_ID) {
        this.WEB_ID = WEB_ID;
    }
}
