package com.szfp.scan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.andview.refreshview.XRefreshView;
import com.extra.presenter.BasePresenter;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.listview.ListViewForScrollView;
import com.szfp.scan.adapter.ShopModelAdapter;
import com.szfp.scan.adapter.TradingOrderAdapter;
import com.szfp.scan.bean.ShopCardModel;
import com.szfp.scan.bean.ShopModel;
import com.szfp.scan.bean.TradingOrderModel;
import com.szfp.scan.inter.OnShopModelLongClickListener;
import com.szfp.scan.util.DbHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckInventoryActivity extends BaseActivity implements OnShopModelLongClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_order)
    ListViewForScrollView lvOrder;
    @BindView(R.id.custom_view)
    XRefreshView customView;

    public static long lastRefreshTime;
    int offset = 0;

    int position=-1;
    private ShopModel shopModel;

    private List<ShopModel> shopModels;
    private ShopModelAdapter adapter;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle("Check Inventory");
    }

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
        DbHelper.loadShopModels(0, models -> {
            if (models.size()!=0){
                offset++;
                if (adapter !=null){
                    adapter.updateItems(models);
                }else {
                    adapter = new ShopModelAdapter(CheckInventoryActivity.this,models,this);
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
                DbHelper.loadShopModels(offset,models -> {
                    customView.stopRefresh();
                    if (models.size()!=0){
                        offset++;
                        if (adapter !=null){
                            adapter.updateItems(models);
                        }
                    }
                });
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                DbHelper.loadShopModels(offset,models -> {
                    customView.stopLoadMore();
                    if (models.size()!=0){
                        offset++;
                        if (adapter !=null){
                            adapter.updateItems(models);
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

    private void showView(ShopModel model) {
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_check_inventory;
    }

    @Override
    public void loadShopModel(ShopModel shopModel,int position) {
        this.position=position;
        this.shopModel =shopModel;
        Intent i = new Intent();
        i.putExtra("ShopModel",shopModel);
        i.setClass(this,AddInvActivity.class);
        startActivity(i);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shopModel!=null){
            shopModel = DbHelper.getShopModel(shopModel.getItemNo());
            adapter.updateItem(shopModel,position);

        }
    }
}
