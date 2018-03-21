package com.extra.loyalty.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.presenter.BasePresenter;
import com.extra.utils.DataUtils;
import com.extra.utils.NetworkUtil;
import com.extra.utils.NetworkUtils;
import com.extra.utils.RegexUtils;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.extra.loyalty.view.activity.SettingActivity.JUMP_KEY;

public class CommunSettings extends BaseActivity {

    private String ip;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_pass)
    EditText etPass;



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
        return R.layout.activity_commun_settings;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ip = SPUtils.getString(this,"IP");
        if (DataUtils.isNullString(ip))etPass.setText(ConstantValue.localhost);
        else etPass.setText(ip);
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
        ip =  etPass.getText().toString();
        if (DataUtils.isNullString(ip))return;


        if (RegexUtils.isURL(ip)){
            ToastUtils.success(ip);
            ConstantValue.localhost=ip;
            SPUtils.putString(this,"IP",ip);
        }else ToastUtils.error("Please enter the correct IP address");
    }
}
