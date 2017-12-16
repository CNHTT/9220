package com.extra.loyalty.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.AccountInfo;
import com.extra.loyalty.presenter.MainPresenter;
import com.extra.presenter.BasePresenter;
import com.extra.utils.StatusBarUtil;
import com.extra.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_loyalty_programs)
    TextView tvLoyaltyPrograms;
    @BindView(R.id.tv_business_employees)
    TextView tvBusinessEmployees;
    @BindView(R.id.tv_login_out)
    TextView tvLoginOut;


    private AccountInfo accountInfo;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    public BasePresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        accountInfo = (AccountInfo) getIntent().getSerializableExtra(ConstantValue.ACCOUNT_INFO);

        toolbar.setTitle(accountInfo.getBiz_name());


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }




    @OnClick({R.id.tv_loyalty_programs, R.id.tv_business_employees, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_loyalty_programs:
                startActivity(new Intent(this,ProgramsActivity.class));
                break;
            case R.id.tv_business_employees:
                startActivity(new Intent(this,BusinessEmployeesActivity.class));
                break;
            case R.id.tv_login_out:
                break;
        }
    }
}
