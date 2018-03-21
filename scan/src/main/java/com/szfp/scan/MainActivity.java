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
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.extra.utils.JsonUtil;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.player.util.L;
import com.szfp.scan.bean.ContinueTradingModel;
import com.szfp.scan.bean.ShopCardModel;
import com.szfp.scan.bean.ShopModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.add_operation)
    TextView tvOperation;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
    }
    private Map<ShopModel,Integer> shoppingSingle;
    @OnClick({R.id.tv_trading,R.id.add_operation, R.id.tv_continue_trading, R.id.tv_view_trade, R.id.tv_check_inv, R.id.tv_add_inv, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_trading:
                Intent intent=new Intent(this,TradingActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE,Constant.REQUEST_SCAN_MODE_ALL_MODE);
                startActivity(intent);
                break;
            case R.id.tv_continue_trading:
                String str = SPUtils.getString(this,"mShopCardModel");
                L.d(str);
                if (DataUtils.isNullString(str)||str.length()<10)
                    startActivity(new Intent(this,TradingActivity.class));else
                {

                    ShopCardModel shopCardModel = new ShopCardModel();
                    shopCardModel.setShoppingAccount(SPUtils.getInt(this,"num"));
                    shopCardModel.setShoppingTotalPrice(Double.parseDouble(SPUtils.getString(this,"total")));
                    List<ContinueTradingModel> list = JsonUtil.stringToList(str, ContinueTradingModel.class);

                    shoppingSingle =  new HashMap<>();
                    for (ContinueTradingModel mode:list) {
                        shoppingSingle.put(mode.getShopModel(),mode.getNum());
                    }
                    shopCardModel.setShoppingSingle(shoppingSingle);

                    Intent intent1 = new Intent(this, ContinueTradingActivity.class);
                    intent1.putExtra("mShopCardModel", shopCardModel);
                    startActivity(intent1);
                }


                break;
            case R.id.tv_view_trade:
                startActivity(new Intent(this,ViewLastTradeActivity.class));
                break;
            case R.id.tv_check_inv:
                startActivity(new Intent(this,CheckInventoryActivity.class));
                break;
            case R.id.tv_add_inv:
                if (App.isTurn)
                startActivity(new Intent(this,AddInvActivity.class));else ToastUtils.showToast("Insufficient authority");
                break;
            case R.id.logout:
                AppManager.getAppManager().finishAllActivityAndExit(this);

                SPUtils.putBoolean(this,"L",false);
                break;
            case R.id.add_operation:
                startActivity(new Intent(this,AddOperatorActivity.class));
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
        if (!App.isTurn) tvOperation.setVisibility(View.GONE);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }
}
