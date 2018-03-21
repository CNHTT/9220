package com.extra.loyalty.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.adapter.RetrieveCampaignsAdapter;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.PickerView;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.dialog.BaseDialog;
import com.extra.widget.listview.InnerScrollerListView;
import com.player.util.L;
import com.pos.device.led.Led;
import com.pos.device.picc.MifareClassic;
import com.pos.device.picc.PiccReader;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerBalanceHistoryActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.tv_code)
    EditText tvCode;
    PickerView pvAllowedCampaigns;
    @BindView(R.id.bt_find)
    Button btFind;

    private String card_number;
    private String code;

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @BindView(R.id.lv_list)
    InnerScrollerListView lvList;
    private  String  campaign_id;

    private Result.CampaignsBean campaignsBean;
    private Result.CampaignsBean.CustomerBean customerBean;
    private List<Result.CampaignsBean.CustomerBean.TransactionsBean> transactionsBeans;

    private Map<String, String> map;
    private List<String>  data;
    private List<String>  dataCopy;
    private List<Result.CampaignsBean> list = new ArrayList<>();
    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.ch);
    }

    @OnClick({R.id.tv_card_number, R.id.bt_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_card_number:showCardNumber();
                break;
            case R.id.bt_find:

                showFind();
                break;
        }
    }

    private void showFind() {
        map = ConstantValue.getPostMap1(map);
        code = tvCode.getText().toString();
        map.put(ConstantValue.TYPE,ConstantValue.customer_balance);
        map.put(ConstantValue.campaign_id,campaign_id);
        if (DataUtils.isNullString(card_number)&&DataUtils.isNullString(code)){
            ToastUtils.showToast("Please input Code  or Card Number");
            return;
        }
        if (!DataUtils.isNullString(card_number))
            map.put(ConstantValue.card_number,card_number);
        else  map.put(ConstantValue.code,code);

        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success( s->{
                    L.d(s);
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){

                        campaignsBean = result.getCampaign();
                        customerBean  = campaignsBean.getCustomer();


                        tvInfo.setText(DataUtils.printTwoData("ACCOUNT NAME",result.getAccount_name()));
                        tvInfo.setText(DataUtils.printTwoData("CAMPAIGN NAME",campaignsBean.getCampaign_name()));
                        tvInfo.append(DataUtils.printTwoData("\nCARD NUMBER",customerBean.getCard_number()));
                        tvInfo.append(DataUtils.printTwoData("\nNAME",customerBean.getFirst_name()+" "+customerBean.getLast_name()));
                        tvInfo.append(DataUtils.printTwoData("\nBALANCE",customerBean.getBalanceX()));

                        if (campaignsBean.getCampaign_type().equals("points")){
                            tvInfo.append(DataUtils.printTwoData("\nlast_transaction_points",customerBean.getLast_transaction_points()));
                            tvInfo.append(DataUtils.printTwoData("\nlast_redemption_points",customerBean.getLast_redemption_points()));
                        }





                    }else {
                        ToastUtils.showToast(result.getError());
                    }

                })
                .error( e ->{
                    L.d(e.toString());

                }).post();



    }


    /**
     *   录入卡号查找
     *
     */

    private Subscription subscription;
    private BaseDialog swipeDailog;
    private void showCardNumber() {
        if (swipeDailog == null) {
            View view = ContextUtil.inflate(this, R.layout.layout_swipe_dialog);
            swipeDailog = new BaseDialog(this, R.style.AlertDialogStyle);
            swipeDailog.setCancelable(false);
            swipeDailog.setContentView(view);


        }
        PiccReader msg = PiccReader.getInstance();
        if (msg != null) {

            msg.startSearchCard(10 * 1000, (result, cardType) -> {
                try {

                    MifareClassic card = MifareClassic.connect();
                    if (card != null) {

//                        Led.setLight(Led.LED_IC_CARD,Led.LED_OFF);
                        card_number = DataUtils.toHexString(card.getUID());
                        tvCardNumber.setText(card_number);
                        playBeepSoundAndVibrate();
                        swipeDailog.cancel();
                    }


                } catch (Exception e) {
                    swipeDailog.cancel();
                    L.d(e.getMessage());
                }
            });
        } else {
            ToastUtils.showToast("please check device");
            swipeDailog.dismiss();
        }
        swipeDailog.show();
    }









    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_customer_balance_history;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pvAllowedCampaigns = findViewById(R.id.pv_allowed_campaigns);


        List<String> list1 = new ArrayList<>();
        list1.add("Campaigns");
        pvAllowedCampaigns.setData(list1);
        initView();
    }

    private void initView() {
        /**
         * Campaigns List
         * 获取所有的
         */

        map = ConstantValue.getPostMap(map);
        map.put(ConstantValue.TYPE, ConstantValue.CAMPAIGNS_LIST);
        showProgressDialog(R.string.xw_ptr_loading);
        new HttpBuilder(ConstantValue.localhost)
                .tag(this)
                .params(map)
                .success(s -> {
                    cancleProgressDialog();
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){
                        list = result.getCampaigns();
                        dataCopy =  new ArrayList<>();
                        data =  new ArrayList<>();
                        for (int i = 0; i <list.size() ; i++) {
                         data.add(list.get(i).getName()+"("+list.get(i).getType()+")");
                            dataCopy.add(list.get(i).getName()+"("+list.get(i).getType()+")");
                        }
                        pvAllowedCampaigns.setData(data);
                        pvAllowedCampaigns.setSelected(0);
                        campaign_id =  list.get(0).getId();

                        pvAllowedCampaigns.setOnSelectListener((text, position) -> {
                            campaign_id = list.get(dataCopy.indexOf(text)).getId();
                            L.d(text +  campaign_id +"  "+dataCopy.indexOf(text)+"  "+ position);
                        });

                    }else {
                        showDialogSuccessToast(result.getError());
                    }

                })
                .error(e -> {
                    cancleProgressDialog();

                })
                .post();


    }
}
