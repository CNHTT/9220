package com.odd.sportal;

import android.app.Application;
import android.os.Environment;
import android.view.View;

import com.extra.retrofit.AppUrl;
import com.extra.retrofit.HttpUtil;
import com.extra.utils.SPUtils;
import com.extra.utils.Utils;

import java.io.File;

/**
 * Created by 戴尔 on 2017/12/11.
 */

public class App extends Application {
    public static  boolean isLoadData = false;

    public static  String filePath;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        isLoadData = SPUtils.getBoolean(this,ContextValue.ISLOADDATA);
        new HttpUtil.SingletonBuilder(this, AppUrl.localhost)
                .build();
        filePath = getSDCardPath()+"/bet/";
        File dirFirstFolder = new File(filePath);
        if (!dirFirstFolder.exists()){
            dirFirstFolder.mkdirs();
        }
    }



    private String getSDCardPath(){
        File sdcardDir = null;
        //判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(sdcardExist){
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }
}
