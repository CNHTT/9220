package com.szfp.bettwostar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.DataUtils;
import com.extra.utils.JsonUtil;
import com.extra.utils.KeyboardUtils;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.player.util.L;
import com.szfp.bettwostar.model.entities.LoginBean;
import com.szfp.bettwostar.model.entities.PostResult;
import com.szfp.bettwostar.print.PrintManager;
import com.szfp.retrofit.HttpBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PosfunctionResultActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_user_first_name)
    EditText etUserFirstName;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.tv_result)
    TextView tvResult;




    private PostResult postResult;
    private String week;
    private String resultStr;

    private LoginBean  result;






    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.result);
    }

    @SuppressLint("SetTextI18n")
    @OnClick(R.id.bt_search)
    public void onViewClicked() {
        KeyboardUtils.hideSoftInput(this);
        week = etUserFirstName .getText().toString();
        if (DataUtils.isNullString(week)){
            ToastUtils.showToast("Please Input Week");
            return;
        }


        showProgressDialog(R.string.xw_ptr_loading);
            postResult = new PostResult();
            postResult.setSn(SPUtils.getString(this,AppUrl.value_sn));
            postResult.setToken(SPUtils.getString(this,AppUrl.value_token));
            postResult.setWeek(week);
            new HttpBuilder(AppUrl.url_make_posfunction_result)
                    .tag(this)
                    .setBody(JsonUtil.objectToString(postResult))
                    .success( s ->{
                        cancleProgressDialog();
                        result = (LoginBean) JsonUtil.stringToObject(s,LoginBean.class);
                        if (result.getRst()){
                            tvResult.setText("Week: " + result.getData().getWeek()+"\n");
                            tvResult.append("Drawn: " + result.getData().getDrawn());
                            PrintManager.getmInstance(this).printWeek(result);

                        }else {
                            tvResult.setText( result.getDetail());
                        }
                    })
                    .error(e ->{
                        cancleProgressDialog();
                        L.d(e.toString());
                })
                .postBady();


    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_posfunction_result;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
