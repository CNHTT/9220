package com.extra.loyalty.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.adapter.AllowedCampaignAdapter;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.model.entities.Result.CampaignsBean;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.retrofit.HttpUtil;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.player.util.L;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.extra.utils.DataUtils.isNullString;

public class AllowedCampaignActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_campaign)
    ListView lvCampaign;
    @BindView(R.id.bt_ok)
    Button btOk;

    private List<CampaignsBean> campaignsBeans ;
    private List<CampaignsBean> list = new ArrayList<>();
    private AllowedCampaignAdapter adapter;
    private String campaignStr;

    private Map<String, String> map;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.campaigns_list);
        StatusBarUtil.setTranslucent(this);
    }


    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadData();
    }

    private void loadData() {
        campaignStr = SPUtils.getContent(this, ConstantValue.CAMPAIGN_STR);
        if (isNullString(campaignStr)) {
            map = new HashMap<>();
            map.put(ConstantValue.USER_ID, ConstantValue.USER_ID_VALUE);
            map.put(ConstantValue.USER_API_KEY, ConstantValue.USER_API_KEY_VALUE);
            map.put(ConstantValue.ACCOUNT_ID, ConstantValue.ACCOUNT_ID_VALUE);
            map.put(ConstantValue.TYPE, ConstantValue.CAMPAIGNS_LIST);
            map.put(ConstantValue.API, ConstantValue.API_VALUE);
            showProgressDialog(R.string.xw_ptr_loading);
            new HttpBuilder(ConstantValue.localhost)
                    .tag(this)
                    .params(map)
                    .success(s -> {
                        cancleProgressDialog();
                        Result result = (Result) JsonUtil.stringToObject(s, Result.class);
                        if (result.checkResult()) {
                            list = result.getCampaigns();
                            adapter = new AllowedCampaignAdapter(this, list);
                            lvCampaign.setAdapter(adapter);


                        } else {
                            ToastUtils.showToast(R.string.no_data);
                        }

                    })
                    .error(e -> {
                        cancleProgressDialog();

                    })
                    .post();

        } else {

        }

    }


    @Override
    public int getContentLayout() {
        return R.layout.activity_allowed_campaign;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_clear,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_clear:
                if (adapter!=null)adapter.clear();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtil.cancel(this);
    }

    @OnClick(R.id.bt_ok)
    public void onViewClicked() {
        if (adapter!=null)
        {
           campaignsBeans =  adapter.getList();

            L.d(campaignsBeans.toString());

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("L", (Serializable) campaignsBeans);
            intent.putExtras(bundle);
            setResult(3,intent);
            finish();


        }
    }
}
