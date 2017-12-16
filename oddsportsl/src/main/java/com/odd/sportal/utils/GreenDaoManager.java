package com.odd.sportal.utils;

import com.extra.utils.Utils;
import com.odd.sportal.greendao.DaoMaster;
import com.odd.sportal.greendao.DaoSession;

/**
 * Created by 戴尔 on 2017/12/11.
 */

public class GreenDaoManager {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private static  volatile GreenDaoManager mInstance;

    private GreenDaoManager(){
        if (mInstance==null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(Utils.getContext(),"ODD.db");
            daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
            daoSession = daoMaster.newSession();
        }
    }


    public static GreenDaoManager getInstance(){
        if (mInstance==null){
            synchronized (GreenDaoManager.class){
                if (mInstance ==null)
                    mInstance = new GreenDaoManager();
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return daoMaster;
    }


    public DaoSession getSession() {
        daoSession = daoMaster.newSession();
        return daoSession;
    }
}
