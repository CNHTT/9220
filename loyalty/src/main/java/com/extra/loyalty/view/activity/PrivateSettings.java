package com.extra.loyalty.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.extra.loyalty.App;
import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.presenter.BasePresenter;
import com.extra.utils.DataUtils;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.utils.Utils;
import com.extra.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.extra.loyalty.view.activity.SettingActivity.JUMP_KEY;

public class PrivateSettings extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_head)
    EditText etHead;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_footer)
    EditText etFooter;
    @BindView(R.id.bt_find)
    Button btFind;


    private String head;
    private String title;
    private String foot;

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
        return R.layout.activity_private_settings;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {

        Utils.hideKeyboard(getView(),this);
        head = SPUtils.getString(this, ConstantValue.HEAD);
        title = SPUtils.getString(this, ConstantValue.TITLE);
        foot = SPUtils.getString(this, ConstantValue.FOOT);

        if (DataUtils.isNullString(head)) etHead.setText(App.HEAD);
        else etHead.setText(head);
        if (DataUtils.isNullString(title)) etTitle.setText(App.TITlE);
        else etTitle.setText(title);
        if (DataUtils.isNullString(foot)) etFooter.setText(App.FOOT);
        else etFooter.setText(foot);

    }


    @OnClick({R.id.bt_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_find:
                foot = etFooter.getText().toString();
                head = etHead.getText().toString();
                title = etTitle.getText().toString();

                SPUtils.putString(this,ConstantValue.FOOT,foot);
                SPUtils.putString(this,ConstantValue.HEAD,head);
                SPUtils.putString(this,ConstantValue.TITLE,title);
                ToastUtils.error("Save Success");
                break;
        }
    }
}
