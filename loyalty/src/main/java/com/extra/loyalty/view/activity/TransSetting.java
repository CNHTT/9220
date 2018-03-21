package com.extra.loyalty.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.presenter.BasePresenter;
import com.extra.utils.DataUtils;
import com.extra.utils.RegexUtils;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.extra.loyalty.view.activity.SettingActivity.JUMP_KEY;

public class TransSetting extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.setting_listitem_iv)
    ImageView settingListitemIv;
    @BindView(R.id.setting_listitem_tv)
    TextView settingListitemTv;
    @BindView(R.id.ll_petrol_station)
    LinearLayout llPetrolStation;
    @BindView(R.id.et_scale)
    EditText etScale;
    @BindView(R.id.ll_show_petrol_station)
    LinearLayout llShowPetrolStation;
    private String scale;

    private boolean petrolStationBollen=false;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(getIntent().getExtras().getString(JUMP_KEY));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_trans_setting;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        scale  = SPUtils.getContent(this, ConstantValue.SCALE);
        if (DataUtils.isNullString(scale)) etScale.setText("100");else etScale.setText(scale);
    }

    @OnClick(R.id.ll_petrol_station)
    public void onViewClicked() {
        if (petrolStationBollen){
            llShowPetrolStation.setVisibility(View.GONE);
            petrolStationBollen=false;
        }else {
            llShowPetrolStation.setVisibility(View.VISIBLE);
            petrolStationBollen=true;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.save :
                saveIP();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveIP() {
        scale =  etScale.getText().toString();
        if (DataUtils.isNullString(scale))return;
            SPUtils.putString(this,ConstantValue.SCALE,scale);
            ToastUtils.showToast(ConstantValue.SCALE+":"+scale);
    }
}
