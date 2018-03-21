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

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessEmployeesActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_loyalty_programs)
    ListView lvLoyaltyPrograms;


    private List<String> lps;
    private LoyaltyProgramsAdapter adapter;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.lp);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lps = Arrays.asList(getResources().getStringArray(R.array.bes));
        adapter = new LoyaltyProgramsAdapter(this,lps);
        lvLoyaltyPrograms.setAdapter(adapter);

        lvLoyaltyPrograms.setOnItemClickListener(this);



    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_programs;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,CreateNewUserActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,UserListActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,UserInformationActivity.class));
                break;
            case 3:
                startActivity(new Intent(this,ChangeAdminPassWordActivity.class));
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
