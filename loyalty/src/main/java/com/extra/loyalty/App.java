package com.extra.loyalty;

import android.app.Application;
import android.os.Bundle;

import com.extra.retrofit.HttpUtil;
import com.extra.utils.Utils;
import com.player.util.L;
import com.pos.device.SDKManager;
import com.pos.device.SDKManagerCallback;

import java.util.Arrays;
import java.util.List;

import retrofit2.http.HEAD;


/**
 * Created by 戴尔 on 2017/11/23.
 */

public class App extends Application {

    public static final String HEAD ="EXPERT IN WIRELESS POS TERMINAL FIELD" ;
    public static final String TITlE ="SZFP TECHNOLOGY LIMITED" ;
    public static final String  FOOT="Telephone : 86-755-86276295 \n"+
            "Email : sales@szfptech.com \n"+
            "Address: 506-507, HuaTong Business Building, Xingao Road, Xili Town, Nanshan District, Shenzhen, China" ;
    public static List<String> roles;
    public static List<String> rolesStr;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        roles = Arrays.asList(getResources().getStringArray(R.array.user_permission_levels));
        rolesStr = Arrays.asList(getResources().getStringArray(R.array.user_permission_levels_str));
        new HttpUtil.SingletonBuilder(this,ConstantValue.localhost)
                .paramsInterceptor(
                        map ->{
                            map.put(ConstantValue.OUTPUT,"JSON");
                            return map;
                        }
                )
                .build();

        Bundle bundle =new Bundle();
        bundle .putBoolean(SDKManager.MagCard,false);
        SDKManager.init(this, bundle, () -> {
            L.d("SDK_MANAGER SUCCESS");
        });
    }
}
