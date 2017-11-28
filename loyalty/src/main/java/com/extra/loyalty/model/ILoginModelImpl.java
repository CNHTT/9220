package com.extra.loyalty.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.extra.loyalty.Businesses;
import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.inter.OnLoginFinishedListener;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.model.impl.ILoginModel;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.retrofit.HttpBuilder;
import com.player.util.L;

/**
 * Created by 戴尔 on 2017/11/23.
 */

public class ILoginModelImpl implements ILoginModel {


    @Override
    public void loginFromNet(Activity context, String uid, String pin, OnLoginFinishedListener listener) {
        new  HttpBuilder(ConstantValue.localhost)
                .tag(context)
                .params(ConstantValue.USER_ID,uid)
                .params(ConstantValue.USER_PASSWORD,pin)
                .params(ConstantValue.TYPE,ConstantValue.ACCOUNT_INFO)
                .params(ConstantValue.ACCOUNT_ID,ConstantValue.ACCOUNT_ID_VALUE)
                .tag(context)
                .error( e ->{
                    L.d(e.toString());
                    listener.netWorkError(e[0].toString());
                })
                .success( s ->{
                    L.d(s);
                        Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                        if (result.checkResult()){
                            ConstantValue.ACCOUNT_ID_VALUE = result.getAccount().getAccount_id();
                            ConstantValue.USER_ID_VALUE = uid;
                            ConstantValue.USER_API_KEY_VALUE = pin;
                            listener.netLoginSuccess(result.getAccount());
                        }else {
                            listener.netWorkError(result.getError());
                        }
                })
                .post();
    }
}
