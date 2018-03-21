package com.szfp.bettwostar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.szfp.bettwostar.print.PrintManager;
import com.szfp.retrofit.HttpBuilder;
import com.szfp.retrofit.HttpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReprintActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.lv_list)
    ListViewForScrollView lvList;


    private PostResult postResult;
    private ReprintBetAdapter adapter;


    private LoginBean resultBean;
    private LoginBean.DataBean dataBean;



    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.reprint);

    }

    @SuppressLint("SetTextI18n")
    private void initView() {

        postResult = new PostResult();
        postResult.setSn(SPUtils.getString(this,AppUrl.value_sn));
        postResult.setToken(SPUtils.getString(this,AppUrl.value_token));

        L.d(JsonUtil.objectToString(postResult));

        showProgressDialog(R.string.xw_ptr_loading);

        new HttpBuilder(AppUrl.url_make_posfunction_reprint)
                .tag(this)
                .setBody(JsonUtil.objectToString(postResult))
                .success( s ->{
                    cancleProgressDialog();
                    resultBean = (LoginBean) JsonUtil.stringToObject(s,LoginBean.class);
                    if (resultBean.getRst()){
                        ivNoData.setVisibility(View.GONE);
                        dataBean = resultBean.getData();
                        tvResult.setText("Transaction Number : "+dataBean.getTx_id()+"\n");
                        tvResult.append("Week : " + dataBean.getWeek()+"\n");
                        tvResult.append("Played Date : " + dataBean.getPlayed_date()+"\n");
                        tvResult.append("Close Date : " + dataBean.getClose_date()+"\n");
                        tvResult.append("Validity : " + dataBean.getValidity()+"\n");
                        tvResult.append("Total Stake : " + dataBean.getTotal_stake()+"\n");
                         adapter = new ReprintBetAdapter(this,dataBean.getBets());
                         lvList.setAdapter(adapter);
                        PrintManager.getmInstance(this).printPosRecord1(resultBean.getData() );


                    }else {
                        tvResult.setText(resultBean.getDetail());
                    }


                })
                .error( e ->{
                    cancleProgressDialog(); L.d(e.toString());
                })
                .postBady();


    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_reprint;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtil.cancel(this);
    }
}
