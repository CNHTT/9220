package com.szfp.scan.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    private boolean isAdd = true;
    public boolean addShoppingSingle(ShopModel shopModel,int type){
        if (type==1){
            int remain = shopModel.getNum();
            if (remain<=0)return false;
            int num =0;
            isAdd=true;
            List<ShopModel> list =new ArrayList<>();
            list.addAll( shoppingSingle.keySet());
            for (int i = 0; i <list.size() ; i++) {
                if (list.get(i).getItemNo().equals(shopModel.getItemNo())){
                    num = shoppingSingle.get(list.get(i));
                    num+=1;

                    list.get(i).setNum(shopModel.getNum()-num);
                    shoppingSingle.put(list.get(i),num);
                    isAdd=false;
                }
            }

            if (isAdd){
                num+=1;
                shopModel.setNum(shopModel
                        .getNum()-num);
                shoppingSingle.put(shopModel,num);
            }
        }else {
            int remain = shopModel.getNum();
            if(remain<=0)
                return false;
            shopModel.setNum(--remain);
            int num = 0;
            if(shoppingSingle.containsKey(shopModel)){
                num = shoppingSingle.get(shopModel);
            }
            num+=1;
            shoppingSingle.put(shopModel,num);
        }

//        if (shoppingSingle.containsKey(shopModel))
//            num = shoppingSingle.get(shopModel);
//
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


    public void setShoppingAccount(int shoppingAccount) {
        this.shoppingAccount = shoppingAccount;
    }

    public void setShoppingTotalPrice(double shoppingTotalPrice) {
        this.shoppingTotalPrice = shoppingTotalPrice;
    }

    public void setShoppingSingle(Map<ShopModel, Integer> shoppingSingle) {
        this.shoppingSingle = shoppingSingle;
    }
}
