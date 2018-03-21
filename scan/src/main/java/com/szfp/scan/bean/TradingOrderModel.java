package com.szfp.scan.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 戴尔 on 2017/12/25.
 */

@Entity
public class TradingOrderModel  implements Serializable {
    static  final  long serialVersionUID=42L;
    @Id(autoincrement = true)
    private Long id;
    private String sn;
    private double totalAmoumt;
    private int totalNum;
    private String opterator;
    private Date createTime;
    @Generated(hash = 1939184490)
    public TradingOrderModel(Long id, String sn, double totalAmoumt, int totalNum,
            String opterator, Date createTime) {
        this.id = id;
        this.sn = sn;
        this.totalAmoumt = totalAmoumt;
        this.totalNum = totalNum;
        this.opterator = opterator;
        this.createTime = createTime;
    }
    @Generated(hash = 1209039454)
    public TradingOrderModel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSn() {
        return this.sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public double getTotalAmoumt() {
        return this.totalAmoumt;
    }
    public void setTotalAmoumt(double totalAmoumt) {
        this.totalAmoumt = totalAmoumt;
    }
    public int getTotalNum() {
        return this.totalNum;
    }
    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
    public String getOpterator() {
        return this.opterator;
    }
    public void setOpterator(String opterator) {
        this.opterator = opterator;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
