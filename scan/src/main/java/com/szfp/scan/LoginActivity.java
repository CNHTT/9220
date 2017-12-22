package com.szfp.scan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.extra.presenter.BasePresenter;
import com.extra.utils.StatusBarUtil;
import com.extra.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected Toolbar getToolBar() {
        return null;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(this);
        initView();
    }

    private void initView() {

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
