package com.extra.loyalty.view.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.adapter.CampaignsAdapter;
import com.extra.loyalty.adapter.TypeRestrictAdapter;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.retrofit.HttpUtil;
import com.extra.utils.ContextUtil;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.loyalty.model.entities.Result.CampaignsBean;
import com.extra.widget.dialog.BaseDialog;
import com.player.util.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeactivatedCampaignsListActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.show_data)
    TextView showData;
    @BindView(R.id.custom_view)
    XRefreshView xRefreshView;

    private List<CampaignsBean> list = new ArrayList<>();
    private CampaignsAdapter adapter;
    private int mLoadCount = 0;
    private Map<String, String> map;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        toolbar.setTitle( R.string.deactivated_campaigns_list);
        StatusBarUtil.setTranslucent(this);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        map = ConstantValue.getPostMap(map);
        map.put(ConstantValue.TYPE, ConstantValue.CAMPAIGNS_INACTIVE_LIST);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(manager);

        adapter = new CampaignsAdapter(list,this);
        rvList.setAdapter(adapter);
        loadData(map);

//设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        //设置静默加载时提前加载的item个数
//        xefreshView1.setPreLoadCount(4);
        //设置Recyclerview的滑动监听
        xRefreshView.setOnRecyclerViewScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                loadData(map);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        for (int i = 0; i < 6; i++) {
//                            recyclerviewAdapter.insert(new Person("More ", mLoadCount + "21"),
//                                    recyclerviewAdapter.getAdapterItemCount());
//                        }
                        mLoadCount++;
                        if (mLoadCount >= 3) {//模拟没有更多数据的情况
                            xRefreshView.setLoadComplete(true);
                        } else {
                            // 刷新完成必须调用此方法停止加载
                            xRefreshView.stopLoadMore(true);
                            //当数据加载失败 不需要隐藏footerview时，可以调用以下方法，传入false，不传默认为true
                            // 同时在Footerview的onStateFinish(boolean hideFooter)，可以在hideFooter为false时，显示数据加载失败的ui
//                            xRefreshView1.stopLoadMore(false);
                        }
                    }
                }, 1000);
            }
        });
//		// 实现Recyclerview的滚动监听，在这里可以自己处理到达底部加载更多的操作，可以不实现onLoadMore方法，更加自由
//		xRefreshView1.setOnRecyclerViewScrollListener(new OnScrollListener() {
//			@Override
//			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//				super.onScrolled(recyclerView, dx, dy);
//				lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//			}
//
//			public void onScrollStateChanged(RecyclerView recyclerView,
//											 int newState) {
//				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//					isBottom = recyclerviewAdapter.getItemCount() - 1 == lastVisibleItem;
//				}
//			}
//		});








    }

    private void loadData(Map<String, String> map) {
        showProgressDialog(R.string.xw_ptr_loading);
        new HttpBuilder(ConstantValue.localhost)
                .tag(this)
                .params(map)
                .success(s -> {
                    cancleProgressDialog();
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){
                        adapter.refresh(result.getCampaigns());
                        xRefreshView.stopRefresh();

                    }else {
                        ToastUtils.showToast(R.string.no_data);
                        showData.setVisibility(View.VISIBLE);
                    }

                })
                .error(e -> {
                    cancleProgressDialog();

                })
                .get();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtil.cancel(this);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_deactivated_campaigns_list;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.campaigm_list_memu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                break;
            case R.id.action_other:
                showOther();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private BaseDialog dialog;
    private ListView lvType;
    private List<String> types;
    private List<String> selects;
    private String type_restrict;
    private TypeRestrictAdapter typeRestrictAdapter;
    private void showOther() {
        if (dialog==null){
            View view = ContextUtil.inflate(this,R.layout.dialog_type_restrict);
            lvType =(ListView) view.findViewById(R.id.lv_type);
            types = Arrays.asList(getResources().getStringArray(R.array.type_restrict));
            view.findViewById(R.id.tv_sure).setOnClickListener( new DialogOnClickListener());
            view.findViewById(R.id.tv_cancel).setOnClickListener( new DialogOnClickListener());
            dialog = new BaseDialog(this,R.style.AlertDialogStyle);
            dialog.setContentView(view);
            dialog.setCancelable(false);
        }
        typeRestrictAdapter = new TypeRestrictAdapter(this,types);
        lvType.setAdapter(typeRestrictAdapter);
        dialog.show();

    }

    private class DialogOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_cancel:
                    dialog.cancel();
                    break;
                case R.id.tv_sure:
                    L.d(typeRestrictAdapter.getResult().toString());
                    selects  =typeRestrictAdapter.getResult();
                    if (selects.size()==0){
                        map =  ConstantValue.getPostMap(map);
                        map.put(ConstantValue.TYPE, ConstantValue.CAMPAIGNS_INACTIVE_LIST);
                        loadData(map);
                    }
                    else {
                        type_restrict =typeRestrictAdapter.getResult().toString().replace("[","").replace("]","");
                        map = ConstantValue.getPostMap(map);
                        map.put(ConstantValue.TYPE, ConstantValue.CAMPAIGNS_INACTIVE_LIST);
                        map.put(ConstantValue.TYPE_RESTRICT,type_restrict);
                        loadData(map);
                    }
                    dialog.cancel();
                    break;
            }
        }
    }
}
