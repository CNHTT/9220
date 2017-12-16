package com.odd.sportal.model;

import java.io.Serializable;

/**
 * Created by 戴尔 on 2017/12/4.
 */

public class SelectCouponBean implements Serializable {
    private String from;
    private String to;
    private boolean current;
    private String coupon;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }
}
