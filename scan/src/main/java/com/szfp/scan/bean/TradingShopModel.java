package com.szfp.scan.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by 戴尔 on 2017/12/25.
 */
@Entity
public class TradingShopModel implements Serializable {

    static  final  long serialVersionUID=42L;
    @Id(autoincrement = true)
    private Long tid;

    private String sn;
    private int sellNum;
    private Long id;
    private String name;
    private String itemNo;
    private int num;
    private double price;
    @Generated(hash = 922520944)
    public TradingShopModel(Long tid, String sn, int sellNum, Long id, String name,
            String itemNo, int num, double price) {
        this.tid = tid;
        this.sn = sn;
        this.sellNum = sellNum;
        this.id = id;
        this.name = name;
        this.itemNo = itemNo;
        this.num = num;
        this.price = price;
    }
    @Generated(hash = 49848450)
    public TradingShopModel() {
    }
    public Long getTid() {
        return this.tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }
    public String getSn() {
        return this.sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public int getSellNum() {
        return this.sellNum;
    }
    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getItemNo() {
        return this.itemNo;
    }
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
