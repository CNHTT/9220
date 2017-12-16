package com.odd.sportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.utils.StatusBarUtil;
import com.extra.view.activity.BaseActivity;
import com.odd.sportal.webservice.RetrofitGenerator;
import com.odd.sportal.webservice.request.RequestBody;
import com.odd.sportal.webservice.request.RequestEnvelope;
import com.odd.sportal.webservice.request.RequestModel;
import com.odd.sportal.webservice.response.ResponseEnvelope;
import com.player.util.L;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        RequestEnvelope requestEnvelop = new RequestEnvelope();
        RequestBody requestBody = new RequestBody();
        RequestModel requestModel = new RequestModel();
        requestModel.theCityName = "深圳";
        requestModel.cityNameAttribute = "http://WebXml.com.cn/";
        requestBody.getWeatherbyCityName = requestModel;
        requestEnvelop.body = requestBody;
        Call<ResponseEnvelope> call = RetrofitGenerator.getWeatherInterfaceApi().getWeatherbyCityName(requestEnvelop);
        call.enqueue(new Callback<ResponseEnvelope>() {
            @Override
            public void onResponse(Call<ResponseEnvelope> call, Response<ResponseEnvelope> response) {
                ResponseEnvelope responseEnvelope = response.body();
                if (responseEnvelope != null ) {
                    L.d(responseEnvelope.body.getWeatherbyCityNameResponse.result.get(0).toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseEnvelope> call, Throwable t) {

            }

        });

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
}
