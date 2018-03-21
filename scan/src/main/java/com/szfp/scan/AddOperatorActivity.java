package com.szfp.scan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.extra.presenter.BasePresenter;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.szfp.scan.bean.ManagerModel;
import com.szfp.scan.util.DbHelper;

import java.util.concurrent.atomic.DoubleAccumulator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddOperatorActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ck_operator)
    CheckBox ckOperator;
    @BindView(R.id.ck_administrator)
    CheckBox ckAdministrator;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_password)
    EditText etUserPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private boolean isTurn=true;
    private String name;
    private String pass;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle("Add Operator");
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        name =etUserName.getText().toString();
        pass = etUserPassword.getText().toString();

        if (DataUtils.isNullString(name)|| DataUtils.isNullString(pass)){
            ToastUtils.showToast("Please Input Edit");
            return;
        }

        ManagerModel m = new ManagerModel();
        m.setName(name);
        m.setPass(pass);
        m.setTure(isTurn);
        if (DbHelper.insertManager(m)){
            etUserName.setText("");
            etUserPassword.setText("");ToastUtils.showToast("Add Success");
        } else ToastUtils.showToast("Error");
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
        ckOperator.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                isTurn = false;
                ckAdministrator.setChecked(false);
            }

        });
        ckAdministrator.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                isTurn = true;
                ckOperator.setChecked(false);
            }

        });


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_add_operator;
    }
}
