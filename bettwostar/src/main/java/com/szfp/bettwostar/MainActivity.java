package com.szfp.bettwostar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.AppManager;
import com.extra.utils.JsonUtil;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.player.util.L;
import com.szfp.bettwostar.adapter.ReprintBetAdapter;
import com.szfp.bettwostar.adapter.StringAdapter;
import com.szfp.bettwostar.model.entities.LoginBean;
import com.szfp.bettwostar.model.entities.PostResult;
import com.szfp.retrofit.HttpBuilder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_list)
    ListView lvList;


    private List<String> strings;
    private StringAdapter adapter;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.choose_operation);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        strings = Arrays.asList(getResources().getStringArray(R.array.chooses));
        adapter = new StringAdapter(this,strings);
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(this);


    }

    /**
     * set  choose click
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,MakeBetActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,PosfunctionResultActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,ReprintActivity.class));
                break;
            case 3:
                startActivity(new Intent(this,WinListActivity.class));
                break;
            case 4:
                startActivity(new Intent(this,ReportActivity.class));
                break;
            case 5:
                startActivity(new Intent(this,CreditLimitActivity.class));
                break;
            case 6:
                startActivity(new Intent(this,ChangePasswordActivity.class));
                break;
            case 7:
                startActivity(new Intent(this,DeleteBetActivity.class));
                break;
            case 8:
                logout();
                break;
        }

    }

    private PostResult postResult;


    private LoginBean resultBean;
    private LoginBean.DataBean dataBean;
    /**
     *
     */
    private void logout() {postResult = new PostResult();
        postResult.setSn(SPUtils.getString(this,AppUrl.value_sn));
        postResult.setToken(SPUtils.getString(this,AppUrl.value_token));

        L.d(JsonUtil.objectToString(postResult));

        showProgressDialog(R.string.xw_ptr_loading);

        new HttpBuilder(AppUrl.url_make_posfunction_logout)
                .tag(this)
                .setBody(JsonUtil.objectToString(postResult))
                .success( s ->{
                    cancleProgressDialog();
                    resultBean = (LoginBean) JsonUtil.stringToObject(s,LoginBean.class);
                    if (resultBean.getRst()){

                        SPUtils.putString(this, AppUrl.value_token,"");
                        SPUtils.putString(this, AppUrl.value_sn,"");
                        SPUtils.putBoolean(this,AppUrl.value_boolean,false);
                        AppManager.getAppManager().finishAllActivityAndExit(this);

                    }else {
                        ToastUtils.error(resultBean.getDetail());

                    }


                })
                .error( e ->{
                    cancleProgressDialog(); L.d(e.toString());
                })
                .postBady();
    }
}
