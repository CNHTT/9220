package com.szfp.bettwostar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.player.util.L;
import com.szfp.bettwostar.adapter.ListStringAdapter;
import com.szfp.bettwostar.adapter.ListStringChoiceAdapter;
import com.szfp.bettwostar.inter.OnClickSuccess;
import com.szfp.bettwostar.model.entities.M;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MakeBetActivity extends BaseActivity implements OnClickSuccess, AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_game_type)
    ListView lvGameType;
    @BindView(R.id.ll_game_type)
    LinearLayout llGameType;
    @BindView(R.id.lv_game_sort)
    ListView lvGameSort;
    @BindView(R.id.ll_game_sort)
    LinearLayout llGameSort;
    @BindView(R.id.lv_game_under)
    ListView lvGameUnder;
    @BindView(R.id.bt_next)
    Button btNext;
    @BindView(R.id.ll_game_under)
    LinearLayout llGameUnder;

    private String  under;

    private List<String> types;
    private List<String> sorts;
    private List<String> unders;


    private String string1;
    private String string2;

    private ListStringAdapter typeAdapter;
    private ListStringAdapter sortAdapter;
    private ListStringChoiceAdapter underAdapter;

    private int type = 0;

    private M m = new M();

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.makebet);
    }

    @OnClick(R.id.bt_next)
    public void onViewClicked() {
        L.d(string1);
        L.d(under);

        if (!string1.equals("Combination")){
            if (under.length()>1) {ToastUtils.showToast("Nap or Prem and Grouping can only choose one"); return;}else {
                m.setUnder(under);
                Intent intent = new Intent();
                intent.setClass(this,GamesActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("M",m);
                intent.putExtras(b);
                startActivity(intent);
            }
        }else {
            if (under.length()<2){ToastUtils.showToast("Combination can be multiple choice  >1"); }else {
                m.setUnder(under);
                Intent intent = new Intent();
                intent.setClass(this,GamesActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("M",m);
                intent.putExtras(b);
                startActivity(intent);
            }
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_make_bet;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar()!=null)getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

    }

    private void initView() {
        types   = Arrays.asList(getResources().getStringArray(R.array.types));
        sorts   = Arrays.asList(getResources().getStringArray(R.array.sorts));
        unders  = Arrays.asList(getResources().getStringArray(R.array.unders));

        typeAdapter = new ListStringAdapter(this,types);
        sortAdapter = new ListStringAdapter(this,sorts);
        underAdapter = new ListStringChoiceAdapter(this,unders,this);

        lvGameType.setAdapter(typeAdapter);
        lvGameSort.setAdapter(sortAdapter);
        lvGameUnder.setAdapter(underAdapter);

        lvGameType.setOnItemClickListener(this);
        lvGameSort.setOnItemClickListener(this);
    }

    @Override
    public void OnClickCallBack(List<String> list) {
        Collections.sort(list);
        under = list.toString().replace(" ","").replace("[","").replace("]","");

        toolbar.setTitle(string1+"  > "+string2+"  > U"+under);
        L.d(under);
        if (!string1.equals("Combination")){
            if (list.size()>1) ToastUtils.showToast("Nap or Prem and Grouping can only choose one");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lv_game_type:

                type = 1;
                showSelect();
                m.setType(String.valueOf(position+1));
                string1 = types.get(position);
                m.setString1(string1);
                toolbar.setTitle(string1);
                break;
            case R.id.lv_game_sort:
                type = 2;
                showSelect();
                m.setSort(String.valueOf(position+1));
                string2 = sorts.get(position);
                m.setString2(string2);
                toolbar.setTitle(string1+"  > "+string2);
                break;

        }
    }

    private void showSelect() {
        switch (type){
            case 0:
                llGameType.setVisibility(View.VISIBLE);
                llGameSort.setVisibility(View.GONE);
                llGameUnder.setVisibility(View.GONE);
                break;
            case 1:
                llGameType.setVisibility(View.GONE);
                llGameSort.setVisibility(View.VISIBLE);
                llGameUnder.setVisibility(View.GONE);
                break;
            case 2:
                llGameType.setVisibility(View.GONE);
                llGameSort.setVisibility(View.GONE);
                llGameUnder.setVisibility(View.VISIBLE);
                break;
        }


    }

    @Override
    public void onBackPressed() {
        switch (type) {
            case 0:
                super.onBackPressed();
                break;
            case 1:
                type = 0;
                showSelect();
                break;
            case 2:
                type = 1;
                showSelect();
                break;
        }
    }
}
