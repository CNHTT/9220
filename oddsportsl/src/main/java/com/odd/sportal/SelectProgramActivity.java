package com.odd.sportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.StatusBarUtil;
import com.extra.view.activity.BaseActivity;
import com.odd.sportal.adapter.SelectCouponAdapter;
import com.odd.sportal.model.SelectCouponBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectProgramActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_refresh)
    Button btnRefresh;
    @BindView(R.id.ck_full_load)
    CheckBox ckFullLoad;
    @BindView(R.id.lv_listView)
    ListView lvListView;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    private SelectCouponAdapter adapter;
    private List<SelectCouponBean> list;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

        ButterKnife.bind(this);

        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.select_program_title);
    }

    @OnClick({R.id.btn_refresh, R.id.btn_ok, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_refresh:
                break;
            case R.id.btn_ok:
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        list = new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            SelectCouponBean selectCouponBean = new SelectCouponBean();
            selectCouponBean.setCurrent(false);
            selectCouponBean.setCoupon("0"+i+"/"+"12/2017-ACE");
            selectCouponBean.setFrom("0"+i+"/"+"12/2017");
            selectCouponBean.setTo("0"+i+"/"+"12/2017");
            list.add(selectCouponBean);
        }
        adapter = new SelectCouponAdapter(this,list);
        lvListView.setAdapter(adapter);



    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_select_program;
    }
}
