package com.szfp.scan.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 戴尔 on 2017/12/21.
 */

public class ShopCardModel implements Serializable {

    private int shoppingAccount;//shop all num
    private double shoppingTotalPrice;  //shop  all  price
    private Map<ShopModel,Integer> shoppingSingle;  //only  one shop  price

    public ShopCardModel(){
        this.shoppingAccount = 0;
        this.shoppingTotalPrice = 0;
        this.shoppingSingle =  new HashMap<>();
    }


    public int getShoppingAccount() {
        return shoppingAccount;
    }

    public double getShoppingTotalPrice() {
        return shoppingTotalPrice;
    }

    public Map<ShopModel, Integer> getShoppingSingle() {
        return shoppingSingle;
    }

    public void clear(){
        this.shoppingSingle.clear();
        this.shoppingTotalPrice = 0;
        this.shoppingAccount=0;
    }

    public int getDishAccount() {
        return shoppingSingle.size();
    }


    public boolean addShoppingSingle(ShopModel shopModel){
        int remain = shopModel.getNum();
        if (remain<=0)return false;
        shopModel.setNum(--remain);
        int num =0;
        if (shoppingSingle.containsKey(shopModel))
            num = shoppingSingle.get(shopModel);
        num+=1;
        shoppingSingle.put(shopModel,num);
        shoppingTotalPrice +=shopModel.getPrice();
        shoppingAccount++;
        return true;
    }
    public boolean subShoppingSingle(ShopModel modelDish){
        int num = 0;
        if(shoppingSingle.containsKey(modelDish)){
            num = shoppingSingle.get(modelDish);
        }
        if(num<=0) return false;
        num--;
        int remain = modelDish.getNum();
        modelDish.setNum(++remain);
        shoppingSingle.put(modelDish,num);
        if (num ==0) shoppingSingle.remove(modelDish);

        shoppingTotalPrice -= modelDish.getPrice();
        shoppingAccount--;
        return true;
    }

}
