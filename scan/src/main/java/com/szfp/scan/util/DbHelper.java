package com.szfp.scan.util;

import com.extra.utils.ToastUtils;
import com.szfp.scan.bean.ShopModel;
import com.szfp.scan.greendao.ShopModelDao;

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
                GreenDaoManager.getInstance().getSession().getShopModelDao().insert(shopModel1);
            }

            ToastUtils.showToast("Add inventory success");

            return true;

        }catch (Exception e){

            ToastUtils.showToast("Add inventory error");
            return false;
        }

    }
}
