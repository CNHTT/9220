package com.extra.loyalty.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.adapter.UserListAdapter;
import com.extra.loyalty.inter.OnClickUserModelListener;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.model.entities.UserBean;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.player.util.L;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListActivity extends BaseActivity implements OnClickUserModelListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.show_data)
    TextView showData;
    @BindView(R.id.custom_view)
    XRefreshView xRefreshView;

    private int offset = 0;
    private int length = 10;
    
    
    private List<UserBean> list;
    private UserListAdapter adapter;


    private Map<String, String> map;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.users_list);
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

        initView();
    }

    private void initView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);

        onDataRefresh();

        xRefreshView.setPullRefreshEnable(true);
        // 设置是否可以上拉加载
        xRefreshView.setPullLoadEnable(true);
        //当下拉刷新被禁用时，调用这个方法并传入false可以不让头部被下拉
        xRefreshView.setMoveHeadWhenDisablePullRefresh(true);
        // 设置时候可以自动刷新
        xRefreshView.setAutoRefresh(true);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                onDataRefresh();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                onDataLoadMore();
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

    private void onDataLoadMore() {
        map  = ConstantValue.getPostMap(map);
        map.put(ConstantValue.TYPE,ConstantValue.users_list);
        map.put(ConstantValue.offset, String.valueOf(offset));
        map.put(ConstantValue.length, String.valueOf(length));
        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success( s ->{
                    xRefreshView.stopLoadMore(false);
                    L.d(s);
                    Result result = (Result) JsonUtil.stringToObject(s, Result.class);
                    if (result.checkResult()) {
                        list  = result.getUsers();
                        initDataView(2);
                        offset++;

                        xRefreshView.stopRefresh(true);
                    } else {
                        ToastUtils.showToast(R.string.no_data);
                    }
                })
                .error(e ->{
                    xRefreshView.stopRefresh();
                    L.d(e.toString());
                })
                .post();
    }

    private void onDataRefresh() {
        offset=0;
        map  = ConstantValue.getPostMap(map);
        map.put(ConstantValue.TYPE,ConstantValue.users_list);
        map.put(ConstantValue.offset, String.valueOf(offset));
        map.put(ConstantValue.length, String.valueOf(length));
        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success( s ->{
                    xRefreshView.stopRefresh();
                    L.d(s);
                    Result result = (Result) JsonUtil.stringToObject(s, Result.class);
                    if (result.checkResult()) {
                        list  = result.getUsers();
                        initDataView(1);
                        offset++;

                    } else {
                        ToastUtils.showToast(R.string.no_data);
                    }
                })
                .error(e ->{
                    xRefreshView.stopRefresh();
                    L.d(e.toString());
                })
                .post();
    }

    private void initDataView(int type) {

        if (adapter==null){
            adapter = new UserListAdapter(list,this);
            rvList.setAdapter(adapter);
        }else {
            if (type==1){
                adapter.refresh(list);
            }else adapter.addData(list);
        }

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_user_list;
    }

    /**
     * 修改会员信息
     * @param userBean
     */
    @Override
    public void OnClickUserModel(UserBean userBean) {
        Intent intent =  new Intent(this,UserUpdateActivity.class);
        Bundle bundle  =  new Bundle();
        bundle.putSerializable("M",userBean);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
