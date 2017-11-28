package com.extra.loyalty.model.impl;

import android.app.Activity;
import android.content.Context;

import com.extra.loyalty.inter.OnLoginFinishedListener;
import com.extra.model.impl.BaseModel;

/**
 * Created by 戴尔 on 2017/11/23.
 */

public interface ILoginModel extends BaseModel {
    void loginFromNet(Activity activity, String uid, String pin, OnLoginFinishedListener listener);
}
