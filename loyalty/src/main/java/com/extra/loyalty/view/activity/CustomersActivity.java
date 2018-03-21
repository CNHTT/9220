package com.extra.loyalty.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.extra.loyalty.R;
import com.extra.loyalty.adapter.LoyaltyProgramsAdapter;
import com.extra.presenter.BasePresenter;
import com.extra.utils.StatusBarUtil;
import com.extra.view.activity.BaseActivity;
import com.pos.device.led.Led;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomersActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_list)
    ListView lvList;

    private List<String> list;
    private LoyaltyProgramsAdapter adapter;



    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.cs);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_customers;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //加载数据
        list = Arrays.asList(getResources().getStringArray(R.array.customers));
        adapter = new LoyaltyProgramsAdapter(this,list);
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Led.setLight(Led.LED_IC_CARD,Led.LED_ON);
                startActivity(new Intent(this,CustomerInformationActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,CustomerSearchActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,CustomerFindActivity.class));
                break;
            case 3:
                startActivity(new Intent(this,RetrieveCustomerActivity.class));
                break;
            case 4:
                startActivity(new Intent(this,CustomerBalanceHistoryActivity.class));
                break;
        }
    }
}
