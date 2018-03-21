package com.szfp.bettwostar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.DataUtils;
import com.extra.utils.JsonUtil;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.player.util.L;
import com.szfp.bettwostar.adapter.ReprintBetAdapter;
import com.szfp.bettwostar.model.entities.LoginBean;
import com.szfp.bettwostar.model.entities.PostResult;
import com.szfp.retrofit.HttpBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pv_point_based)
    TextView pvPointBased;
    @BindView(R.id.tv_old_pass)
    EditText tvOldPass;
    @BindView(R.id.ll_view_point)
    LinearLayout llViewPoint;
    @BindView(R.id.tv_new_pass)
    EditText tvNewPass;
    @BindView(R.id.bt_find)
    Button btFind;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.chanage_pass);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_change_password;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private String  oldPass;
    private String  newPass;

    private PostResult postResult;
    private LoginBean resultBean;
    private LoginBean.DataBean dataBean;

    @OnClick(R.id.bt_find)
    public void onViewClicked() {
        oldPass = tvOldPass.getText().toString();
        newPass = tvNewPass.getText().toString();


        if (DataUtils.isNullString(oldPass)||DataUtils.isNullString(newPass ))
        {
            ToastUtils.showToast("please input"); return;
        }

        postResult = new PostResult();
        postResult.setSn(SPUtils.getString(this,AppUrl.value_sn));
        postResult.setToken(SPUtils.getString(this,AppUrl.value_token));
        postResult.setNew_password(newPass);
        postResult.setOld_password(oldPass);

        showProgressDialog(R.string.xw_ptr_loading);

        new HttpBuilder(AppUrl.url_make_posfunction_change_pass)
                .tag(this)
                .setBody(JsonUtil.objectToString(postResult))
                .success( s ->{
                    cancleProgressDialog();
                    resultBean = (LoginBean) JsonUtil.stringToObject(s,LoginBean.class);
                    if (resultBean.getRst()){

                        ToastUtils.showToast(resultBean.getDetail());
                        tvNewPass.setText("");
                        tvOldPass.setText("");

                    }else {
                       ToastUtils.showToast(resultBean.getDetail());
                    }


                })
                .error( e ->{
                    cancleProgressDialog(); L.d(e.toString());
                })
                .postBady();




    }
}
