package com.extra.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.extra.R;
import com.extra.inter.IBase;
import com.extra.presenter.BasePresenter;
import com.extra.utils.AndroidBug5497Workaround;
import com.extra.utils.AppManager;
import com.extra.utils.ContextUtil;
import com.extra.utils.SystemBarTintManager;
import com.extra.view.impl.IBaseView;
import com.extra.widget.dialog.DialogSure;
import com.player.util.L;

/**
 * Created by 戴尔 on 2017/11/22.
 */

public abstract   class BaseActivity<T extends BasePresenter<IBaseView>> extends AppCompatActivity implements IBase {

    /**
     * 主线程
     */
    private long mUIThreadId;
    private Toolbar mToolbar;
    protected View mRootView;
    private SystemBarTintManager tintManager;
    private void setActionBar(){};
    



    /**
     * 是否设置沉浸式
     *
     * @return
     */
    protected boolean isSetStatusBar() {
        return false;
    }


    protected BasePresenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUIThreadId = android.os.Process.myTid();
        AppManager.getAppManager().addActivity(this);
        mPresenter = getPresenter();
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.attach((IBaseView) this);
        }

        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
        initWindow();
        if  (getToolBar()!=null){
            mToolbar =getToolBar();
            setSupportActionBar(mToolbar);
        }
        setActionBar();
        bindView(savedInstanceState);

    }

    protected abstract Toolbar getToolBar();

    protected abstract void initWindow();


    @Override
    public View createView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = ContextUtil.inflate(this,getContentLayout());
        return view;
    }




    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }


    public long getmUiThreadId() {
        return mUIThreadId;
    }

    @Override
    public View getView() {
        return mRootView;
    }



    private boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  android.R.id.home:
                onBackPressed();
                // 处理返回逻辑
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected DialogSure dialogSure;
    protected void showDialogToast(String toast) {
        if (dialogSure==null)dialogSure = new DialogSure(this);
        dialogSure.setTitle("PROMPT");
        dialogSure.getTvContent().setText(toast);
        dialogSure.getTvSure().setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dialogSure.cancel();
            }
        });
        dialogSure.show();
    }

    private ProgressDialog progressDialog;
    protected void showProgressDialog(int resId) {
        if (progressDialog==null)
            progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getResources().getString(resId));
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    protected void cancleProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }


}
