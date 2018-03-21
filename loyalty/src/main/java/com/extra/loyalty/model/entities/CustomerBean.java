package com.extra.loyalty.model.entities;

import java.io.Serializable;

/**
 * Created by 戴尔 on 2018/1/9.
 */

public class CustomerBean implements Serializable {

    /**
     * code : 6000000497583623
     * card_number : 18889655
     * first_name : Xtra
     * last_name : T
     * phone : +13347226715
     * email : cnhttt@163.com
     * street1 : nan shang
     * street2 : xili
     * city : shenzhen
     * state :
     * postal_code :
     * country : b
     * custom_date : 2018-01-05
     * custom_field :
     * customer_username : xtra22
     * registered : Y
     * record_timestamp : 2018-01-05 01:46:43
     * custom_field_3 : {"label":"Sentinel ALT","data":""}
     */

    private String code;
    private String card_number;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private String custom_date;
    private String custom_field;
    private String customer_username;
    private String registered;
    private String record_timestamp;
    private CustomField3Bean custom_field_3;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustom_date() {
        return custom_date;
    }

    public void setCustom_date(String custom_date) {
        this.custom_date = custom_date;
    }

    public String getCustom_field() {
        return custom_field;
    }

    public void setCustom_field(String custom_field) {
        this.custom_field = custom_field;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getRecord_timestamp() {
        return record_timestamp;
    }

    public void setRecord_timestamp(String record_timestamp) {
        this.record_timestamp = record_timestamp;
    }

    public CustomField3Bean getCustom_field_3() {
        return custom_field_3;
    }

    public void setCustom_field_3(CustomField3Bean custom_field_3) {
        this.custom_field_3 = custom_field_3;
    }

    public static class CustomField3Bean implements Serializable {
        /**
         * label : Sentinel ALT
         * data :
         */

        private String label;
        private String data;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
