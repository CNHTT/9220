package com.szfp.bettwostar.model.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 戴尔 on 2018/1/23.
 */

public class PostMakeBet implements Serializable {
    private String sn;
    private String token;

    private List<MakeBet> data;


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

    public List<MakeBet> getData() {
        return data;
    }

    public void setData(List<MakeBet> data) {
        this.data = data;
    }
}
