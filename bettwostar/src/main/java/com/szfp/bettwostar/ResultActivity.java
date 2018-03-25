package com.szfp.bettwostar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.AppManager;
import com.extra.utils.DataUtils;
import com.extra.utils.JsonUtil;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.dialog.DialogSureCancel;
import com.player.util.L;
import com.szfp.bettwostar.adapter.ItemAdapter;
import com.szfp.bettwostar.model.entities.GroupBean;
import com.szfp.bettwostar.model.entities.LoginBean;
import com.szfp.bettwostar.model.entities.MakeBet;
import com.szfp.bettwostar.model.entities.PostMakeBet;
import com.szfp.bettwostar.print.PrintManager;
import com.szfp.retrofit.HttpBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.szfp.bettwostar.App.sorts;
import static com.szfp.bettwostar.App.types;

public class ResultActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.bt_cancel)
    Button btCancel;
    @BindView(R.id.bt_enter)
    Button btEnter;

    private LoginBean resultBean;
    private LoginBean.DataBean dataBean;
    private String type;
    private MakeBet<String> makeBetStr;
    private MakeBet<List<GroupBean>> makeBetGroup;
    private List<MakeBet> data;

    private ItemAdapter adapter;
    private String str;
    private int mSize=0;
    private int gSize=0;



    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.view);
    }

    @OnClick({R.id.bt_cancel, R.id.bt_enter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:

                SPUtils.putString(this,"LIST_MAKE_BET","");
                AppManager.getAppManager().finishActivity(this);
                startActivity(new Intent(this,MainActivity.class));

                break;
            case R.id.bt_enter:
                postMakeBet();
                break;
        }
    }

    /**
     * 提交数据
     */

    private StringBuffer  string;
    private void postMakeBet() {
        PostMakeBet postMakeBet  = new PostMakeBet();
        postMakeBet.setData(data);
        postMakeBet.setSn(SPUtils.getString(this,AppUrl.value_sn));
        postMakeBet.setToken(SPUtils.getString(this,AppUrl.value_token));
        new HttpBuilder(AppUrl.url_make_bet)
                .tag(this)
                .setBody(JsonUtil.objectToString(postMakeBet))
                .success( s ->{
                    L.d(s);
                    resultBean = (LoginBean) JsonUtil.stringToObject(s,LoginBean.class);
                    if (resultBean.getRst()){
                        dataBean = resultBean.getData();

//                        PrintManager.getmInstance(this).printPosRecord(dataBean);
                        string = new StringBuffer();
                        string.append("TSN : " +dataBean.getTx_id()+"\n");
                        string  .append("MatchPlayed : " +dataBean.getPlayed_date()+"\n");
                        string  .append("ClosingTime : " +dataBean.getClose_date()+"\n");
                        string  .append("Validity : " +dataBean.getValidity()+"\n");
                        string  .append("Total Stake : " +dataBean.getTotal_stake()+"\n");
                        List<MakeBet> list = dataBean.getBets();
                        MakeBet item;
                        for (int i = 0; i <list.size() ; i++) {
                            item = list.get(i);
                            if (item.getUnder()==null)
                                string.append( types.get(Integer.valueOf(item.getType())-1)+"  " +sorts.get( Integer.valueOf(item.getSort())-1) +
                                        "  U"  +  item.getUnder() + "  N"+item.getAmount()+"\n");
                            if (item.getType().equals("3")){
                                string.append("Games: " +  item.getGames().toString()+"\n");
                            }else {
                                string.append("Games: " + item.getGames()+"\n");
                            }
                            string.append(item.getDescription()+"\n");
                        }

                        DialogSureCancel dialogSureCancel = new DialogSureCancel(this,R.style.AlertDialogStyle);
                        dialogSureCancel.setTitle("Result");
                        dialogSureCancel.setContent(string.toString());
                        dialogSureCancel.setSureListener(v -> {
//                            PrintManager.getmInstance(ResultActivity.this).printPosRecord(dataBean);
                            AppManager.getAppManager().finishActivity(ResultActivity.this);
                                dialogSureCancel.cancel();});
                        dialogSureCancel.setCancelListener(v -> {
                            AppManager.getAppManager().finishActivity(ResultActivity.this);
                            dialogSureCancel.cancel();
                        });

                        dialogSureCancel.setCancelable(false);
                        dialogSureCancel.show();
                    }else {
                        ToastUtils.error(resultBean.getDetail());
                    }
                })
                .error(e ->{

                })
                .postBady();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_result;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

    }

    private void initView() {
        str  = SPUtils.getString(this,"LIST_MAKE_BET");
        if (DataUtils.isNullString(str)){
            data = new ArrayList<>();
            mSize=0;
        }else {
            data = JsonUtil.stringToList(str,MakeBet.class);
            mSize = data.size();
        }



        type = getIntent().getStringExtra("TYPE");
        if (type.equals("STRING"))
        {
            makeBetStr = (MakeBet<String>) getIntent().getSerializableExtra("MAKE_BET");
            makeBetStr.setId(String.valueOf(mSize+1));
            data.add(makeBetStr);
        }else {
            makeBetGroup = (MakeBet<List<GroupBean>>) getIntent().getSerializableExtra("MAKE_BET");
            makeBetGroup.setId(String.valueOf(mSize++));
            data.add(makeBetGroup);
        }


        adapter = new ItemAdapter(this,data);
        lvList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mean_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_add:SPUtils.putString(this,"LIST_MAKE_BET",JsonUtil.objectToString(data));
            startActivity(new Intent(this,MakeBetActivity.class));
                break;
        }
        return true;
    }
}
