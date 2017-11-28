package com.extra.loyalty.model.entities;

import java.io.Serializable;

/**
 * Created by 戴尔 on 2017/11/24.
 */

public class AccountInfo implements Serializable{


    /**
     * account_id : coaldemolg
     * biz_name : RAYONS SPORT FC
     * biz_address1 : Land Trade House
     * biz_address2 : Remera-Kismenti
     * biz_city : Kigali
     * biz_state :
     * biz_zip :
     * biz_country : Rwanda
     * contact_first_name : Kabandana
     * contact_last_name : Augustin
     * contact_phone : 0787300219
     * contact_email : smartsaverw@gmail.com
     * language : EN
     * symbol : RWF
     * glyph : Fr
     * timezone : 35
     * coalition : Yes
     * created : 2017-05-08 10:01:39
     */

    private String account_id;
    private String biz_name;
    private String biz_address1;
    private String biz_address2;
    private String biz_city;
    private String biz_state;
    private String biz_zip;
    private String biz_country;
    private String contact_first_name;
    private String contact_last_name;
    private String contact_phone;
    private String contact_email;
    private String language;
    private String symbol;
    private String glyph;
    private String timezone;
    private String coalition;
    private String created;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getBiz_name() {
        return biz_name;
    }

    public void setBiz_name(String biz_name) {
        this.biz_name = biz_name;
    }

    public String getBiz_address1() {
        return biz_address1;
    }

    public void setBiz_address1(String biz_address1) {
        this.biz_address1 = biz_address1;
    }

    public String getBiz_address2() {
        return biz_address2;
    }

    public void setBiz_address2(String biz_address2) {
        this.biz_address2 = biz_address2;
    }

    public String getBiz_city() {
        return biz_city;
    }

    public void setBiz_city(String biz_city) {
        this.biz_city = biz_city;
    }

    public String getBiz_state() {
        return biz_state;
    }

    public void setBiz_state(String biz_state) {
        this.biz_state = biz_state;
    }

    public String getBiz_zip() {
        return biz_zip;
    }

    public void setBiz_zip(String biz_zip) {
        this.biz_zip = biz_zip;
    }

    public String getBiz_country() {
        return biz_country;
    }

    public void setBiz_country(String biz_country) {
        this.biz_country = biz_country;
    }

    public String getContact_first_name() {
        return contact_first_name;
    }

    public void setContact_first_name(String contact_first_name) {
        this.contact_first_name = contact_first_name;
    }

    public String getContact_last_name() {
        return contact_last_name;
    }

    public void setContact_last_name(String contact_last_name) {
        this.contact_last_name = contact_last_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getGlyph() {
        return glyph;
    }

    public void setGlyph(String glyph) {
        this.glyph = glyph;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCoalition() {
        return coalition;
    }

    public void setCoalition(String coalition) {
        this.coalition = coalition;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
