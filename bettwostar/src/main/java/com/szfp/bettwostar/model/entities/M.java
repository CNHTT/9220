package com.szfp.bettwostar.model.entities;

import java.io.Serializable;

/**
 * Created by 戴尔 on 2018/1/23.
 */

public class M implements Serializable {
    private String type;
    private String sort;
    private String under;

    private String string1;
    private String string2;
    private String string3;


    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUnder() {
        return under;
    }

    public void setUnder(String under) {
        this.under = under;
    }
}
