package com.odd.sportal;

import android.app.Application;

import com.extra.utils.SPUtils;
import com.extra.utils.Utils;

/**
 * Created by 戴尔 on 2017/12/11.
 */

public class App extends Application {
    public static  boolean isLoadData = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        isLoadData = SPUtils.getBoolean(this,ContextValue.ISLOADDATA);
    }
}
