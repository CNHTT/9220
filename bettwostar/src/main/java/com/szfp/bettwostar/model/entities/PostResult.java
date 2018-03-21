package com.szfp.bettwostar.model.entities;

/**
 * Created by 戴尔 on 2018/1/29.
 */

public class PostResult {

    private String sn;
    private String token;
    private String week;

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    private String old_password;


    private String new_password;


    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
