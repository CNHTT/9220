package com.extra.loyalty.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class TransactionsActivity extends BaseActivity implements AdapterView.OnItemClickListener {
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
        toolbar.setTitle(R.string.transactions);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_transactions;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //加载数据
        list = Arrays.asList(getResources().getStringArray(R.array.transactions));
        adapter = new LoyaltyProgramsAdapter(this,list);
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,TransactionRecordActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,TransactionRedeemActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,TransactionBatchActivity.class));
                break;
            case 3:
                startActivity(new Intent(this,TransactionDeleteActivity.class));
                break;
        }
    }
}
