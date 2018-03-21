package com.extra.loyalty.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.model.entities.UserBean;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.extra.utils.EncodeUtils;
import com.extra.utils.EncryptUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.RunTextView;
import com.extra.widget.dialog.BaseDialog;
import com.player.util.L;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserUpdateActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_user_name)
    TextView etUserName;
    @BindView(R.id.et_user_password)
    EditText etUserPassword;
    @BindView(R.id.et_user_first_name)
    EditText etUserFirstName;
    @BindView(R.id.et_user_last_name)
    EditText etUserLastName;
    @BindView(R.id.et_user_custom)
    EditText etUserCustom;
    @BindView(R.id.et_user_pin)
    EditText etUserPin;
    @BindView(R.id.ns_language)
    BetterSpinner nsLanguage;
    @BindView(R.id.ns_timezone)
    BetterSpinner nsTimezone;
    @BindView(R.id.ns_user_role)
    BetterSpinner nsUserRole;
    @BindView(R.id.tv_allowed_campaigns)
    TextView tvAllowedCampaigns;
    @BindView(R.id.btn_login)
    Button btnLogin;


    private Map<String, String> map;

    private String user_name;
    private String user_new_password;
    private String user_first_name;
    private String user_last_name;
    private String user_custom1;
    private String user_PIN;
    private UserBean model;


    private List<String> languages;
    private List<String> languagesStr;
    private List<String> roles;
    private List<String> rolesStr;
    private List<String> timezones;
    private List<String> timezonesStr;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;

    private String language_selector="EN";
    private String user_role="A";
    private String allowed_campaigns ="all";
    private String timezone_selector = "57";

    private List<Result.CampaignsBean>  list = new ArrayList<>();

    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.update_user_info);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
    @Override
    public int getContentLayout() {
        return R.layout.activity_user_update;
    }
    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();

    }

    //得到用户信息修改数据
    private void initView() {
        model = (UserBean) getIntent().getExtras().getSerializable("M");

        user_first_name =model.getUser_first_name();
        user_last_name = model.getUser_last_name();
        user_custom1  =  model.getUser_addtl_info();
        user_PIN = model.getUser_PIN();

        //
        language_selector = model.getUser_language();
        user_role = model.getUser_role();
        allowed_campaigns = model.getUser_allowed_campaigns().getStatus();
        timezone_selector   = model.getUser_timezone();



        etUserName.setText(model.getUser_id());
        etUserFirstName.setText(user_first_name);
        etUserLastName.setText(user_last_name);
        etUserCustom.setText(user_custom1);
        if (user_PIN!=null) etUserPin.setText(user_PIN);

        tvAllowedCampaigns.setText(allowed_campaigns);
        languages = Arrays.asList(getResources().getStringArray(R.array.available_languages));
        languagesStr = Arrays.asList(getResources().getStringArray(R.array.available_languages_str));
        roles = Arrays.asList(getResources().getStringArray(R.array.user_permission_levels));
        rolesStr = Arrays.asList(getResources().getStringArray(R.array.user_permission_levels_str));
        timezones = Arrays.asList(getResources().getStringArray(R.array.timezones));
        timezonesStr = Arrays.asList(getResources().getStringArray(R.array.timezones_str));


        //显示语言

        for (int i = 0; i <languages.size() ; i++) {
            if (languages.get(i).equals(language_selector))
                nsLanguage.setText(languagesStr.get(i));
        }

        //显示时区
        for (int i = 0; i <timezones.size() ; i++) {
            if (timezones.get(i).equals(timezone_selector))
                nsTimezone.setText(timezonesStr.get(i));
        }

        //显示权限
        for (int i = 0; i <languages.size() ; i++) {
            if (roles.get(i).equals(user_role))
                nsUserRole.setText(rolesStr.get(i));

        }

        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, languagesStr);
        adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, rolesStr);
        adapter3= new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, timezonesStr);

        nsLanguage.setAdapter(adapter1);
        nsUserRole.setAdapter(adapter2);
        nsTimezone.setAdapter(adapter3);

        nsLanguage.setOnItemClickListener((parent, view, position, id) -> {
            L.d(position+"");
            language_selector=languages.get(position);
        });

        nsUserRole.setOnItemClickListener((parent, view, position, id) -> {
            L.d(position+"");
            user_role = roles.get(position);
        });
        nsTimezone.setOnItemClickListener((parent, view, position, id) -> {
            L.d(position+"");
            timezone_selector=timezones.get(position);
        });
    }
    @OnClick({R.id.tv_allowed_campaigns, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_allowed_campaigns:
                showCampaign();
                break;
            case R.id.btn_login:
                toUpdateUser();
                break;
        }
    }

    private void toUpdateUser() {
        user_new_password  =etUserPassword.getText().toString();
        user_first_name = etUserFirstName.getText().toString();
        user_last_name  =  etUserLastName.getText().toString();
        user_custom1 = etUserCustom .getText().toString();
        user_PIN = etUserPin .getText().toString();

        if (
                        DataUtils.isNullString(user_first_name)||
                        DataUtils.isNullString(user_last_name)||
                        DataUtils.isNullString(user_custom1)){
            ToastUtils.showToast("Please Input/Edit");
            return;
        }

        //判断用户信息是否修改
        showProgressDialog(R.string.xw_ptr_loading);
        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.USER_ID, model.getUser_id());

        if (!DataUtils.isNullString(user_new_password))
        if (!EncryptUtils.encryptMD5ToString(user_new_password).equals(model.getUser_api_key()))
            map.put(ConstantValue.USER_PASSWORD, user_new_password);

        if (!user_first_name.equals(model.getUser_first_name()))
            map.put(ConstantValue.USER_FIRST_NAME,user_first_name);

        if (!user_last_name.equals(model.getUser_last_name()))
        map.put(ConstantValue.USER_LAST_NAME,user_last_name);

        if (!user_custom1.equals(model.getUser_addtl_info()))
        map.put(ConstantValue.user_custom1,user_custom1);

        if (!DataUtils.isNullString(user_PIN))
            if (!user_PIN.equals(model.getUser_PIN()))
                map.put(ConstantValue.user_PIN,user_PIN);

        if (!language_selector.equals(model.getUser_language()))
            map.put(ConstantValue.language_selector,language_selector);

        if (!timezone_selector.equals(model.getUser_timezone()))
        map.put(ConstantValue.timezone_selector,timezone_selector);

        if (!user_role.equals(model.getUser_role()))
        map.put(ConstantValue.user_role,user_role);


        new HttpBuilder(ConstantValue.localhost)
                .tag(this)
                .params(map)
                .success( (String s) ->{
                    L.d(s);
                    cancleProgressDialog();
                    Result result = (Result) JsonUtil.stringToObject(s, Result.class);
                    if (result.checkResult()) {
                        onBackPressed();
                    } else {
                        ToastUtils.showToast(R.string.no_data);
                    }
                })
                .error( e ->{
                    L.d(e.toString());
                })
                .post();
    }

    /**
     * 显示Campaign
     */
    private BaseDialog campaginDialog;
    private ListView listView;
    private List<String> allowedCampaigs;
    ArrayAdapter<String> allowedAdapter;
    private void showCampaign() {
        if (campaginDialog==null){
            View view = ContextUtil.inflate(this,R.layout.dialog_listview);
            listView = (ListView) view.findViewById(R.id.lv_allowed);
            allowedCampaigs = Arrays.asList(getResources().getStringArray(R.array.allowed_campaigns));
            allowedAdapter = new ArrayAdapter<String>(this,R.layout.simple_list_item, allowedCampaigs);
            listView.setAdapter(allowedAdapter);
            campaginDialog = new BaseDialog(this,R.style.AlertDialogStyle);
            campaginDialog.setContentView(view);

            listView.setOnItemClickListener((parent, view1, position, id) -> {
                if (position!=1){
                    allowed_campaigns=allowedCampaigs.get(position);
                    tvAllowedCampaigns.setText(allowed_campaigns);
                    campaginDialog.cancel();
                }else {
                    campaginDialog.cancel();
                    showCampaigns();
                }
            });
        }
        campaginDialog.show();
    }
    private void showCampaigns() {
        startActivityForResult(new Intent(this,AllowedCampaignActivity.class),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==3){
            list = (ArrayList<Result.CampaignsBean>) data.getSerializableExtra("L");
            L.d(list.toString());
            for (int i = 0; i <list.size() ; i++) {
                if(i==0)
                {
                    tvAllowedCampaigns.setText(list.get(i).getName());
                    allowed_campaigns = list.get(i).getId();
                }
                else{
                    tvAllowedCampaigns.append(","+list.get(i).getName());
                    allowed_campaigns = allowed_campaigns + "," + list.get(i).getId();
                }
            }
            L.d(allowed_campaigns);
        }
    }
}
