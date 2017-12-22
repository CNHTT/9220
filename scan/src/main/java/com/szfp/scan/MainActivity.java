package com.szfp.scan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.AppManager;
import com.extra.utils.Constant;
import com.extra.utils.StatusBarUtil;
import com.extra.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_trading)
    TextView tvTrading;
    @BindView(R.id.tv_continue_trading)
    TextView tvContinueTrading;
    @BindView(R.id.tv_view_trade)
    TextView tvViewTrade;
    @BindView(R.id.tv_check_inv)
    TextView tvCheckInv;
    @BindView(R.id.tv_add_inv)
    TextView tvAddInv;
    @BindView(R.id.logout)
    TextView logout;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

    }

    @OnClick({R.id.tv_trading, R.id.tv_continue_trading, R.id.tv_view_trade, R.id.tv_check_inv, R.id.tv_add_inv, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_trading:
                Intent intent=new Intent(this,TradingActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE,Constant.REQUEST_SCAN_MODE_ALL_MODE);
                startActivity(intent);
                break;
            case R.id.tv_continue_trading:
                startActivity(new Intent(this,TradingActivity.class));
                break;
            case R.id.tv_view_trade:
                startActivity(new Intent(this,TradingActivity.class));
                break;
            case R.id.tv_check_inv:
                startActivity(new Intent(this,TradingActivity.class));
                break;
            case R.id.tv_add_inv:
                startActivity(new Intent(this,AddInvActivity.class));
                break;
            case R.id.logout:
                AppManager.getAppManager().finishAllActivityAndExit(this);
                break;
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.scan);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }
}
