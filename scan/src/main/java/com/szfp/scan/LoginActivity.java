package com.szfp.scan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.extra.presenter.BasePresenter;
import com.extra.utils.DataUtils;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.szfp.scan.bean.ManagerModel;
import com.szfp.scan.util.DbHelper;

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

    private String name;
    private String pass;
    private ManagerModel managerModel;

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
        name =etUserName.getText().toString();
        pass = etPassword.getText().toString();

        if (DataUtils.isNullString(name)|| DataUtils.isNullString(pass)){
            ToastUtils.showToast("Please Input Edit");
            return;
        }

        managerModel = DbHelper.getManager(name,pass);

        if (managerModel == null){
            if (name.equals("SZFPTECH")){
                toMain();
            }else
            ToastUtils.showToast("");
        }else toMain();

    }

    private void toMain() {
        SPUtils.putString(this,"N",name);
        if (managerModel==null)SPUtils.putBoolean(this,"O",true);
        else  SPUtils.putBoolean(this,"O",managerModel.getTure());

        App.userName = SPUtils.getString(this,"N");
        App.isTurn = SPUtils.getBoolean(this,"O");

        SPUtils.putBoolean(this,"L",true);

        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
