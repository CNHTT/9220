package com.extra.loyalty.model.entities;

/**
 * Created by 戴尔 on 2017/12/29.
 */

public class PaginationBean {

    /**
     * total : 8
     * offset : 0
     * length : 10
     */

    private String total;
    private String offset;
    private String length;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
