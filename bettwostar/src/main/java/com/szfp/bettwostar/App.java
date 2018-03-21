package com.szfp.bettwostar;

import android.app.Application;
import android.util.Log;

import com.extra.utils.Utils;
import com.pos.device.SDKManager;
import com.pos.device.SDKManagerCallback;
import com.szfp.retrofit.HttpUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by 戴尔 on 2018/1/22.
 */

public class App extends Application{




    public static List<String> types;
    public static List<String> sorts;
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        new HttpUtil.SingletonBuilder(this,AppUrl.localhost).build();

        types   = Arrays.asList(getResources().getStringArray(R.array.types));
        sorts   = Arrays.asList(getResources().getStringArray(R.array.sorts));
//        SDKManager.init(this, () -> Log.d("SDKMANAGER", "SUCCESS"));
    }
}
