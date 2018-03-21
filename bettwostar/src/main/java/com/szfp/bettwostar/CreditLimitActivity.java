package com.szfp.bettwostar;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.JsonUtil;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.listview.ListViewForScrollView;
import com.player.util.L;
import com.szfp.bettwostar.adapter.ReprintBetAdapter;
import com.szfp.bettwostar.model.entities.LoginBean;
import com.szfp.bettwostar.model.entities.PostResult;
import com.szfp.retrofit.HttpBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreditLimitActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.lv_list)
    ListViewForScrollView lvList;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.credit_limit);

    }

    private LoginBean resultBean;
    private LoginBean.DataBean dataBean;
    private PostResult postResult;
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_credit_limit;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);initView();
        
    }

    @SuppressLint("SetTextI18n")
    private void initView() {

        postResult = new PostResult();
        postResult.setSn(SPUtils.getString(this,AppUrl.value_sn));
        postResult.setToken(SPUtils.getString(this,AppUrl.value_token));

        L.d(JsonUtil.objectToString(postResult));

        showProgressDialog(R.string.xw_ptr_loading);

        new HttpBuilder(AppUrl.url_make_posfunction_creditlimit)
                .tag(this)
                .setBody(JsonUtil.objectToString(postResult))
                .success( s ->{
                    cancleProgressDialog();
                    resultBean = (LoginBean) JsonUtil.stringToObject(s,LoginBean.class);
                    if (resultBean.getRst()){
                        ivNoData.setVisibility(View.GONE);
                        dataBean = resultBean.getData();
                        tvResult.setText("Credit Limit : "+dataBean.getCredit_limit()+"\n");

                    }else {
                        tvResult.setText(resultBean.getDetail());
                    }


                })
                .error( e ->{
                    cancleProgressDialog(); L.d(e.toString());
                })
                .postBady();
    }
}
