package com.extra.loyalty.presenter;

import com.extra.loyalty.inter.OnLoginFinishedListener;
import com.extra.loyalty.model.ILoginModelImpl;
import com.extra.loyalty.model.entities.AccountInfo;
import com.extra.loyalty.model.impl.ILoginModel;
import com.extra.loyalty.view.impl.ILoginView;
import com.extra.presenter.BasePresenter;

/**
 * Created by 戴尔 on 2017/11/23.
 */

public class LoginPresenter extends BasePresenter<ILoginView>  implements OnLoginFinishedListener{
    private ILoginModel mLoginModel;

    public LoginPresenter() {
        this.mLoginModel =new ILoginModelImpl();
    }

    public void loginNetWork(){
        mLoginModel.loginFromNet(mView.getActivity(),mView.getUID(),mView.getPID(),this);
    }

    @Override
    public void netWorkError(String error) {
        mView.setLoginError(error);
    }

    @Override
    public void netLoginSuccess(AccountInfo info) {
        mView.setLoginInfo(info);
    }
}
