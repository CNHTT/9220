package com.extra.loyalty.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.presenter.BasePresenter;
import com.extra.utils.StatusBarUtil;
import com.extra.view.activity.BaseActivity;
import com.mikepenz.fastadapter.IItem;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.setting_listview)
    ListView settingListview;
    public static String JUMP_KEY = "JUMP_KEY" ;
    private static final String MAP_TV = "MAP_TV" ;
    private static final String MAP_IV = "MAP_IV" ;
    private ArrayList<HashMap<String,Object>> list ;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.setting);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initView();
    }

    private void initView() {
        settingListview.setAdapter(formatAdapter2());
        settingListview.setOnItemClickListener(this);
    }


    private ListAdapter formatAdapter2(){
        list = new ArrayList<>();
        String[] names = getResources().getStringArray(R.array.setting_items2);
        for (int i = 0 ; i < names.length ; i++){
            HashMap<String,Object> map = new HashMap<>();
            map.put(MAP_TV , names[i]);
            map.put(MAP_IV , ConstantValue.IMGS2[i]);
            list.add(map);
        }
        return new SimpleAdapter(this , list , R.layout.setting_list_item,
                new String[]{MAP_IV , MAP_TV},new int[]{R.id.setting_listitem_iv,R.id.setting_listitem_tv});
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        String text = ((TextView)view.findViewById(R.id.setting_listitem_tv)).getText().toString();
        intent.putExtra(JUMP_KEY , text);
        switch (position){
            case 0:
                intent.setClass(this , CommunSettings.class);
                break;
            case 1:
                intent.setClass(this , TransSetting.class);
                break;
            case 2:
                intent.setClass(this , PrivateSettings.class);
                break;
        }
        startActivity(intent);
    }
}
