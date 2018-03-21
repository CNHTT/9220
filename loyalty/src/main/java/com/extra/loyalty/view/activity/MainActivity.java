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
import com.extra.utils.AppManager;
import com.extra.utils.SPUtils;
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




    @OnClick({R.id.tv_loyalty_programs, R.id.tv_business_employees,R.id.tv_customers,R.id.tv_setting, R.id.tv_login_out, R.id.tv_transaction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_loyalty_programs:
                startActivity(new Intent(this,ProgramsActivity.class));
                break;
            case R.id.tv_business_employees:
                startActivity(new Intent(this,BusinessEmployeesActivity.class));
                break;
            case R.id.tv_customers:
                startActivity(new Intent(this,CustomersActivity.class));
                break;
            case R.id.tv_transaction:
                startActivity(new Intent(this,TransactionsActivity.class));
                break;
            case R.id.tv_setting:
                startActivity(new Intent(this,SettingActivity.class));
                break;
            case R.id.tv_login_out:
                SPUtils.putBoolean(this,ConstantValue.ISLOGIN,false);
                SPUtils.putString(this,ConstantValue.ACCOUNT_ID_VALUE,"");
                SPUtils.putString(this,ConstantValue.USER_ID_VALUE,"");
                SPUtils.putString(this,ConstantValue.USER_API_KEY_VALUE,"");
                AppManager.getAppManager().finishAllActivityAndExit(this);
                break;
        }
    }
}
