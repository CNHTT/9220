package com.extra.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.extra.inter.IBase;
import com.extra.presenter.BasePresenter;
import com.extra.view.impl.IBaseView;
import com.player.util.L;

/**
 * Created by 戴尔 on 2017/11/23.
 */

public abstract class BaseFragment<T extends BasePresenter<IBaseView>> extends Fragment implements IBase {

    private BasePresenter mPresenter;
    private Context context;
    private View mRootView;


    private String TAG = "BaseFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        mPresenter =  getPresenter();
        if (mPresenter!=null&&this instanceof IBaseView)
            mPresenter.attach((IBaseView) this);
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView !=null)
        {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent!=null)
                parent.removeView(mRootView);
        }
        else  mRootView = createView(inflater,container,savedInstanceState);

        context = mRootView.getContext();

        return mRootView;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        return inflater.inflate(getContentLayout(),viewGroup,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"OnActivityCreated");
        bindView(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        if (mPresenter !=null && this instanceof IBaseView)
        {
            mPresenter.detachView();
            mPresenter=null;
        }
        context = null;
        super.onDestroy();
    }

    public View getmRootView() {
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"ONRESUME");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"OnPause");
    }



}
