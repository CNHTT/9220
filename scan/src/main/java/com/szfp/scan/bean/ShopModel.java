package com.szfp.scan.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.simpleframework.xml.Serializer;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 戴尔 on 2017/12/21.
 */

@Entity
public class ShopModel implements Serializable{
    static  final  long serialVersionUID=42L;
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String itemNo;
    private int num;
    private double price;

    public ShopModel(String name, String itemNo, int num, double price) {
        this.name = name;
        this.itemNo = itemNo;
        this.num = num;
        this.price = price;
    }

    @Generated(hash = 1945938891)
    public ShopModel(Long id, String name, String itemNo, int num, double price) {
        this.id = id;
        this.name = name;
        this.itemNo = itemNo;
        this.num = num;
        this.price = price;
    }

    @Generated(hash = 1257168190)
    public ShopModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
