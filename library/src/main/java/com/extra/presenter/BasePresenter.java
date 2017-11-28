package com.extra.presenter;

import com.extra.view.impl.IBaseView;

/**
 * Created by 戴尔 on 2017/11/22.
 */

public abstract class BasePresenter<T extends IBaseView> {

    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

}
