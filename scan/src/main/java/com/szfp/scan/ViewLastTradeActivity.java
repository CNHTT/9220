package com.szfp.scan;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.extra.presenter.BasePresenter;
import com.extra.utils.ContextUtil;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.dialog.BaseDialog;
import com.extra.widget.listview.ListViewForScrollView;
import com.szfp.scan.adapter.TradingOrderAdapter;
import com.szfp.scan.adapter.TradingShopAdapter;
import com.szfp.scan.bean.TradingOrderModel;
import com.szfp.scan.bean.TradingShopModel;
import com.szfp.scan.util.DbHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewLastTradeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_order)
    ListViewForScrollView lvOrder;
    @BindView(R.id.custom_view)
    XRefreshView customView;

    private List<TradingOrderModel> tradingOrderModels;

    private TradingOrderAdapter adapter;

    public static long lastRefreshTime;
    int offset = 0;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initDate();

    }



    private void initDate() {

        DbHelper.loadTradingOrderModels(0,models -> {
            if (models.size()!=0){
                offset++;
                if (adapter !=null){
                    adapter.updateItems(models);
                }else {
                    adapter = new TradingOrderAdapter(ViewLastTradeActivity.this,models, this::showView);
                    lvOrder.setAdapter(adapter);
                }
            }
        });
        customView.setPullRefreshEnable(true);
        // 设置是否可以上拉加载
        customView.setPullLoadEnable(true);
        // 设置上次刷新的时间
        customView.restoreLastRefreshTime(lastRefreshTime);
        //当下拉刷新被禁用时，调用这个方法并传入false可以不让头部被下拉
        customView.setMoveHeadWhenDisablePullRefresh(true);
        // 设置时候可以自动刷新
        customView.setAutoRefresh(false);
        customView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {

                offset=0;
                 DbHelper.loadTradingOrderModels(offset,models -> {
                     customView.stopRefresh();
                     if (models.size()!=0){
                         offset++;
                         if (adapter !=null){
                             adapter.updateItems(models);
                         }else {
                             adapter = new TradingOrderAdapter(ViewLastTradeActivity.this,models,model->{
                                 showView(model);
                             });
                             lvOrder.setAdapter(adapter);
                         }
                     }
                });
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                DbHelper.loadTradingOrderModels(offset,models -> {
                    customView.stopLoadMore();
                    if (models.size()!=0){
                        offset++;
                        if (adapter !=null){
                            adapter.updateItems(models);
                        }else {
                            adapter = new TradingOrderAdapter(ViewLastTradeActivity.this,models,model->{
                                showView(model);
                            });
                            lvOrder.setAdapter(adapter);
                        }
                    }

                });


            }

            @Override
            public void onRelease(float direction) {
                super.onRelease(direction);
                if (direction > 0) {
                    ToastUtils.showToast("下拉");
                } else {
                    ToastUtils.showToast("上拉");
                }
            }
        });



    }

    BaseDialog dialog;
    private List<TradingShopModel> shopModels;
    private ListView listView;
    private void showView(TradingOrderModel model) {
        if (dialog == null){
            View view = ContextUtil.inflate(this,R.layout.dialog_shop);
            listView = (ListView)view.findViewById(R.id.lv);
            dialog = new BaseDialog(this,R.style.AlertDialogStyle);
            dialog.setContentView(view);
        }
        shopModels = DbHelper.getTradingModel(model.getSn());
        if (shopModels!=null){
            TradingShopAdapter shopAdapter = new TradingShopAdapter(this,shopModels);
            listView.setAdapter(shopAdapter);
        }
        dialog.show();

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_view_last_trade;
    }

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle("View Last Trade");
    }
}
