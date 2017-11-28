package com.extra.loyalty.view.impl;

import com.extra.loyalty.model.entities.AccountInfo;
import com.extra.view.impl.IBaseView;

/**
 * Created by 戴尔 on 2017/11/23.
 */

public interface ILoginView extends IBaseView{

    void setLoginInfo(AccountInfo accountInfo);
    void setLoginError(String error);
    String getUID();
    String getPID();
}
