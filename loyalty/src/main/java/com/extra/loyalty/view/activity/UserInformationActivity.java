package com.extra.loyalty.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.model.entities.UserBean;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.view.activity.BaseActivity;
import com.player.util.L;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInformationActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_custom)
    TextView tvUserCustom;
    @BindView(R.id.tv_user_language)
    TextView tvUserLanguage;
    @BindView(R.id.tv_user_timezone)
    TextView tvUserTimezone;
    @BindView(R.id.tv_user_role)
    TextView tvUserRole;
    @BindView(R.id.tv_allowed_campaigns)
    TextView tv_allowed_campaigns;

    private String user_name;

    private Map<String, String> map;

    private String language_selector;
    private String user_role;
    private String allowed_campaigns;
    private String timezone_selector;

    private List<String> languages;
    private List<String> languagesStr;
    private List<String> roles;
    private List<String> rolesStr;
    private List<String> timezones;
    private List<String> timezonesStr;



    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.ui);
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_user_information;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        languages = Arrays.asList(getResources().getStringArray(R.array.available_languages));
        languagesStr = Arrays.asList(getResources().getStringArray(R.array.available_languages_str));
        roles = Arrays.asList(getResources().getStringArray(R.array.user_permission_levels));
        rolesStr = Arrays.asList(getResources().getStringArray(R.array.user_permission_levels_str));
        timezones = Arrays.asList(getResources().getStringArray(R.array.timezones));
        timezonesStr = Arrays.asList(getResources().getStringArray(R.array.timezones_str));
        etUserName.setHint(ConstantValue.USER_ID_VALUE);

    }

    @OnClick(R.id.ib_search)
    public void onViewClicked() {

        user_name = etUserName.getText().toString();

        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.TYPE, ConstantValue.user_info);
        if (!DataUtils.isNullString(user_name))
            map.put(ConstantValue.user_name, user_name);
        showProgressDialog(R.string.xw_ptr_loading);
        new HttpBuilder(ConstantValue.localhost)
                .tag(this)
                .params(map)
                .success((String s) -> {
                    L.d(s);
                    cancleProgressDialog();
                    Result result = (Result) JsonUtil.stringToObject(s, Result.class);
                    if (result.checkResult()) {
                        initView(result.getUser());
                    } else {
                        showDialogSuccessToast(result.getError());
                    }
                })
                .error(e -> {
                    cancleProgressDialog();
                    showDialogSuccessToast(e.toString());
                    L.d(e.toString());
                })
                .post();


    }

    @SuppressLint("SetTextI18n")
    private void initView(UserBean model) {
        tvUserId.setText(model.getUser_id());
        tvUserName.setText(model.getUser_first_name()+" "+model.getUser_last_name());
        tvUserCustom.setText(model.getUser_addtl_info());
        language_selector = model.getUser_language();
        user_role = model.getUser_level();
        allowed_campaigns = model.getAllowed_campaigns().getStatus();
        timezone_selector   = model.getUser_timezone();
        //显示语言

        for (int i = 0; i <languages.size() ; i++) {
            if (languages.get(i).equals(language_selector))
                tvUserLanguage.setText(languagesStr.get(i));
        }

        //显示时区
        for (int i = 0; i <timezones.size() ; i++) {
            if (timezones.get(i).equals(timezone_selector))
                tvUserTimezone.setText(timezonesStr.get(i));
        }

        //显示权限
        for (int i = 0; i <roles.size() ; i++) {
            if (roles.get(i).equals(user_role))
                tvUserRole.setText(rolesStr.get(i));

        }

       tv_allowed_campaigns.setText(model.getAllowed_campaigns().getStatus());




    }


}
