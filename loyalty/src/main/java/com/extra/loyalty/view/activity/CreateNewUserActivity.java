package com.extra.loyalty.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.extra.loyalty.R;
import com.extra.presenter.BasePresenter;
import com.extra.utils.ContextUtil;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.TimeUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.NiceSpinner;
import com.extra.widget.RunTextView;
import com.extra.widget.dialog.BaseDialog;
import com.player.util.L;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNewUserActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_password)
    EditText etUserPassword;
    @BindView(R.id.et_user_first_name)
    EditText etUserFirstName;
    @BindView(R.id.et_user_last_name)
    EditText etUserLastName;
    @BindView(R.id.et_user_custom)
    EditText etUserCustom;
    @BindView(R.id.et_user_pin)
    RunTextView etUserPin;
    @BindView(R.id.ns_language)
    BetterSpinner nsLanguage;
    @BindView(R.id.ns_user_role)
    BetterSpinner nsUserRole;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_allowed_campaigns)
    TextView tvAllowedCampaigns;
    private List<String> languages;
    private List<String> languagesStr;
    private List<String> roles;
    private List<String> rolesStr;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;



    private String user_name;

    private String user_new_password;
    private String user_first_name;
    private String user_last_name;
    private String user_custom1;
    private String user_PIN;

    private String language_selector="EN";
    private String user_role="A";
    private String allowed_campaigns ="all";


    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user_PIN = TimeUtils.getUUID();
        etUserPin.setText(user_PIN);
        tvAllowedCampaigns.setText(allowed_campaigns);

        languages = Arrays.asList(getResources().getStringArray(R.array.available_languages));
        languagesStr = Arrays.asList(getResources().getStringArray(R.array.available_languages_str));
        roles = Arrays.asList(getResources().getStringArray(R.array.user_permission_levels));
        rolesStr = Arrays.asList(getResources().getStringArray(R.array.user_permission_levels_str));
        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, languagesStr);

        adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, rolesStr);


        nsLanguage.setAdapter(adapter1);
        nsUserRole.setAdapter(adapter2);

        nsLanguage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.d(position+"");
                language_selector=languages.get(position);
            }
        });

        nsUserRole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.d(position+"");
                user_role = roles.get(position);
            }
        });

    }



    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);

        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.create_new_user);
    }

    @OnClick({R.id.tv_allowed_campaigns, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_allowed_campaigns:
                showCampaign();
                break;
            case R.id.btn_login:
                break;
        }
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
            View view =ContextUtil.inflate(this,R.layout.dialog_listview);
            listView = (ListView) view.findViewById(R.id.lv_allowed);
            allowedCampaigs = Arrays.asList(getResources().getStringArray(R.array.allowed_campaigns));
            allowedAdapter = new ArrayAdapter<String>(this,R.layout.simple_list_item, allowedCampaigs);
            listView.setAdapter(allowedAdapter);
            campaginDialog = new BaseDialog(this,R.style.AlertDialogStyle);
            campaginDialog.setContentView(view);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position!=1){
                        allowed_campaigns=allowedCampaigs.get(position);
                        tvAllowedCampaigns.setText(allowed_campaigns);
                        campaginDialog.cancel();
                    }else {
                        campaginDialog.cancel();
                        showCampaigns();
                    }
                }
            });
        }
        campaginDialog.show();
    }

    private void showCampaigns() {
        startActivityForResult(new Intent(this,AllowedCampaignActivity.class),1);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_create_new_user;
    }
}
