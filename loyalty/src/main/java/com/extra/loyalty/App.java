package com.extra.loyalty;

import android.app.Application;

import com.extra.retrofit.HttpUtil;
import com.extra.utils.Utils;


/**
 * Created by 戴尔 on 2017/11/23.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        new HttpUtil.SingletonBuilder(this,ConstantValue.localhost)
                .paramsInterceptor(
                        map ->{
                            map.put(ConstantValue.OUTPUT,"JSON");
                            return map;
                        }
                )
                .build();
    }
}
