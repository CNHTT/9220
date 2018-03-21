package com.szfp.scan.bean;

import java.io.Serializable;

/**
 * Created by 戴尔 on 2017/12/25.
 */

public class ContinueTradingModel implements Serializable{

    private ShopModel shopModel;
    private int  num;

    public ShopModel getShopModel() {
        return shopModel;
    }

    public void setShopModel(ShopModel shopModel) {
        this.shopModel = shopModel;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
