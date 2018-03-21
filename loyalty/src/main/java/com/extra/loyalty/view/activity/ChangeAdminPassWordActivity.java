package com.extra.loyalty.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.listview.InnerScrollerListView;
import com.player.util.L;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeAdminPassWordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.bt_find)
    Button btFind;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.lv_list)
    InnerScrollerListView lvList;

    private String userName;
    private String pass;
    private Map<String, String> map;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.change_pass);
    }

    @OnClick(R.id.bt_find)
    public void onViewClicked() {
        userName = etUserName.getText().toString();
        pass =  etUserName.getText().toString();

        if (DataUtils.isNullString(userName)||DataUtils.isNullString(pass)){
            ToastUtils.showToast("please input ");return;
        }
        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.TYPE,"user_update");
        map.put(ConstantValue.user_name,userName);
        map.put(ConstantValue.user_new_password	,pass);

        showProgressDialog(R.string.xw_ptr_loading);
        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success( s->{cancleProgressDialog();
                    L.d(s);
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){
                        etPass.setText("");
                        ToastUtils.success("Success");
                    }else {
                        ToastUtils.error(result.getError());
                    }

                })
                .error( e ->{cancleProgressDialog();
                    L.d(e.toString());

                }).post();


    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_change_admin_pass_word;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etUserName.setText(ConstantValue.USER_ID_VALUE);
    }
}
