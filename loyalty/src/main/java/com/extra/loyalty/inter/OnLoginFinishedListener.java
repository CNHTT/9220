package com.extra.loyalty.inter;

import com.extra.loyalty.model.entities.AccountInfo;

/**
 * Created by 戴尔 on 2017/11/23.
 */

public interface OnLoginFinishedListener {
    void netWorkError(String error);
    void netLoginSuccess(AccountInfo info);
}
