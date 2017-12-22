package com.odd.sportal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.extra.presenter.BasePresenter;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.odd.sportal.service.ParseXmlService;

import java.io.File;

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
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.ck_proxy_server)
    CheckBox ckProxyServer;
    @BindView(R.id.ck_connect)
    CheckBox ckConnect;
    @BindView(R.id.et_ip)
    EditText etIp;
    @BindView(R.id.et_port)
    EditText etPort;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.ll_proxy_server)
    LinearLayout llProxyServer;



    @Override
    protected Toolbar getToolBar() {
        return null;
    }

    @Override
    protected void initWindow() {
        boolean isloading = SPUtils.getBoolean(this,ContextValue.ISLOADING);
        if (!isloading)
            if (!SPUtils.getBoolean(this,ContextValue.ISLOADDATA)){
//            loadData();
                ToastUtils.showToast("Data loading, please do not kill this app");
                Intent intent=new Intent(this,ParseXmlService.class);
                startService(intent);
            }

    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.btn_register:
                test();
                break;
        }
    }

    private void test() {



    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(this);
        initView();
    }

    private void initView() {
        ckProxyServer.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)llProxyServer.setVisibility(View.VISIBLE);else llProxyServer.setVisibility(View.GONE);
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }
    private String getSDCardPath(){
        File sdcardDir = null;
        //判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(sdcardExist){
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }
}
