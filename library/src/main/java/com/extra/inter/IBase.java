package com.extra.inter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.extra.presenter.BasePresenter;

/**
 * Created by 戴尔 on 2017/11/22.
 */

public interface IBase {

    BasePresenter getPresenter();

    View createView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState);

    void bindView(Bundle savedInstanceState);

    View getView();

    int getContentLayout();


}
