package com.szfp.scan.util;

import com.extra.utils.ToastUtils;
import com.szfp.scan.bean.ManagerModel;
import com.szfp.scan.bean.ShopModel;
import com.szfp.scan.bean.TradingOrderModel;
import com.szfp.scan.bean.TradingShopModel;
import com.szfp.scan.greendao.ManagerModelDao;
import com.szfp.scan.greendao.ShopModelDao;
import com.szfp.scan.greendao.TradingOrderModelDao;
import com.szfp.scan.greendao.TradingShopModelDao;
import com.szfp.scan.inter.OnLoadShopModelListener;
import com.szfp.scan.inter.OnLoadTradingOrderModels;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 戴尔 on 2017/12/21.
 */

public class DbHelper {
    public static boolean  insert(ShopModel shopModel) {
        try {
            ShopModel shopModel1 = GreenDaoManager.getInstance().getSession().getShopModelDao().queryBuilder().where(ShopModelDao.Properties.ItemNo.eq(shopModel.getItemNo())).build().unique();
            if (shopModel1==null)
                GreenDaoManager.getInstance().getSession().getShopModelDao().insert(shopModel);
            else {
                shopModel1.setNum(shopModel1.getNum()+shopModel.getNum());
                GreenDaoManager.getInstance().getSession().getShopModelDao().update(shopModel1);
            }

            ToastUtils.showToast("Add inventory success");

            return true;

        }catch (Exception e){

            ToastUtils.showToast("Add inventory error");
            return false;
        }

    }

    public static ShopModel getShopModel(String s) {
        try {
            ShopModel shopModel1 = GreenDaoManager.getInstance().getSession().getShopModelDao().queryBuilder().where(ShopModelDao.Properties.ItemNo.eq(s)).build().unique();
            if (shopModel1==null){
                ToastUtils.showToast("No inventory error"); return null;}
            else return shopModel1 ;
        }catch (Exception e){
            ToastUtils.showToast("No inventory error");
            return null ;
        }
    }

    public static void updateShopModel(List<ShopModel> shopModels) {

        try {
            GreenDaoManager.getInstance().getSession().getShopModelDao().updateInTx(shopModels);
        }catch (Exception e){

            ToastUtils.showToast("Update inventory error");
        }
    }

    public static void insertTrading(TradingOrderModel tradingOrderModel, List<TradingShopModel> tradingShopModels) {

        GreenDaoManager.getInstance().getSession().getTradingOrderModelDao().insert(tradingOrderModel);
        GreenDaoManager.getInstance().getSession().getTradingShopModelDao().insertInTx(tradingShopModels);


    }

    public static void loadTradingOrderModels(int offset, OnLoadTradingOrderModels listener) {
        try {
            List<TradingOrderModel> list =GreenDaoManager.getInstance().getSession().getTradingOrderModelDao().queryBuilder().offset(offset*10).limit(10).orderDesc(TradingOrderModelDao.Properties.Id).list();
            listener.loadData(list);
        }catch (Exception e){
            listener.loadData(new ArrayList<>()) ;
        }

    }
    public static void loadShopModels(int offset, OnLoadShopModelListener listener) {
        try {
            List<ShopModel> list =GreenDaoManager.getInstance().getSession().getShopModelDao().queryBuilder().offset(offset*10).limit(10).orderDesc(ShopModelDao.Properties.Id).list();
            listener.loadShopModels(list);
        }catch (Exception e){
            listener.loadShopModels(new ArrayList<>()) ;
        }

    }
    public static List<TradingShopModel> getTradingModel(String sn) {
        return GreenDaoManager.getInstance().getSession().getTradingShopModelDao().queryBuilder()
                .where(TradingShopModelDao.Properties.Sn.eq(sn)).build().list();
    }

    public static boolean upDateShopModel(ShopModel shopModel) {
        try {
            GreenDaoManager.getInstance().getSession().getShopModelDao().update(shopModel);
            return true;
        }catch (Exception e){

            ToastUtils.showToast("Update inventory error");
            return false;
        }
    }

    public static boolean insertManager(ManagerModel m) {
        try {
            GreenDaoManager.getInstance().getSession().getManagerModelDao().insert(m);
            return true;
        }catch (Exception e){

            ToastUtils.showToast("Update inventory error");
            return false;
        }
    }

    public static ManagerModel getManager(String name, String pass) {
        try {
            return GreenDaoManager.getInstance().getSession().getManagerModelDao().queryBuilder().where(ManagerModelDao.Properties.Name.eq(name),ManagerModelDao.Properties.Pass.eq(pass)).build().unique();
        }catch (Exception e){
            return null;
        }
    }
}
