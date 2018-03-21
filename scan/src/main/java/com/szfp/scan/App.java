package com.szfp.scan;

import android.app.Application;
import android.util.Log;

import com.extra.utils.Utils;
import com.pos.device.SDKManager;
import com.pos.device.SDKManagerCallback;

/**
 * Created by 戴尔 on 2017/12/21.
 */

public class App extends Application{
    public static String LOGRECORD = "LOGRECORD";
    public static String isLogin = "isLogin";
    private boolean isAidl; //5800
    public static String userName = "123";
    public static String TID = "123";

    public static boolean isTurn;


    public static String companyName = "SZFP TECHNOLOGY LIMITED";
    public static String slogan = "EXPERT IN WIRELESS POS TERMINAL FIELD";

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        SDKManager.init(this, new SDKManagerCallback() {
            @Override
            public void onFinish() {
                Log.d("SDKMANAGER", "SUCCESS");
            }
        });
    }
}
