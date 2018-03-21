package com.extra.loyalty.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.extra.inter.OnBaseSureListener;
import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.adapter.RetrieveCampaignsAdapter;
import com.extra.loyalty.model.entities.CustomerBean;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.dialog.BaseDialog;
import com.extra.widget.listview.InnerScrollerListView;
import com.player.util.L;
import com.pos.device.led.Led;
import com.pos.device.picc.MifareClassic;
import com.pos.device.picc.PiccReader;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RetrieveCustomerActivity extends BaseActivity implements OnBaseSureListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_input_show)
    TextView tvInputShow;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.et_user_code)
    EditText etUserCode;
    @BindView(R.id.et_hide_custom_field)
    EditText etHideCustomField;
    @BindView(R.id.ns_include_balances)
    BetterSpinner nsIncludeBalances;
    @BindView(R.id.ll_input_box)
    LinearLayout llInputBox;
    @BindView(R.id.bt_find)
    Button btFind;

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @BindView(R.id.lv_list)
    InnerScrollerListView lvList;


    private TranslateAnimation mShowAction;

    private Map<String, String> map;
    private List<String> lsincludes;
    private boolean isShowViewInput=false;

    @BindView(R.id.tv_allowed_campaigns)
    TextView tvAllowedCampaigns;

    private String card_number;
    private String include_balances="N";
    private String code;
    private String campaign_id;
    private String hide_custom_field;
    private List<Result.CampaignsBean> list = new ArrayList<>();

    private String allowed_campaigns;
    private ArrayAdapter<String> adapter1;
    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.rc);
        setOnBaseSureListener(this);
    }

    @OnClick({R.id.tv_input_show, R.id.tv_card_number, R.id.bt_find, R.id.tv_allowed_campaigns})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_input_show:
                showInputView();
                break;
            case R.id.tv_card_number:
                showCardNumber();
                break;
            case R.id.bt_find:
                showFind();
                break;
            case R.id.tv_allowed_campaigns:
                startActivityForResult(new Intent(this, AllowedCampaignActivity.class), 1);
                break;
        }
    }


    private CustomerBean customerBean;
    private List<Result.CampaignsBean> campaignsBeanList;
    private RetrieveCampaignsAdapter adapter;

    @SuppressLint("SetTextI18n")
    private void showFind() {

        Led.setLight(Led.LED_NFC_1,Led.LED_OFF);
        Led.setLight(Led.LED_NFC_2,Led.LED_OFF);
        Led.setLight(Led.LED_NFC_3,Led.LED_OFF);
        Led.setLight(Led.LED_NFC_4,Led.LED_OFF);
        isShowViewInput= true;
        llInputBox.setVisibility(View.GONE);
        map = ConstantValue.getPostMap1(map);
        code = etUserCode.getText().toString();
        hide_custom_field = etHideCustomField.getText().toString();

        map.put(ConstantValue.TYPE,ConstantValue.customer_info);

        if (DataUtils.isNullString(card_number)&&DataUtils.isNullString(code)){
            ToastUtils.showToast("Please input Code  or Card Number");
            return;
        }

        if (!DataUtils.isNullString(card_number))
            map.put(ConstantValue.card_number,card_number);
        else  map.put(ConstantValue.code,code);

        if (!DataUtils.isNullString(allowed_campaigns)) map.put(ConstantValue.campaign_id,allowed_campaigns);
        if (!DataUtils.isNullString(hide_custom_field)) map.put(ConstantValue.hide_custom_field,hide_custom_field);

        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success( s->{
                    L.d(s);
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){

                        customerBean  = result.getCustomer();
                        campaignsBeanList =result.getCampaigns();
                        tvInfo.setText(DataUtils.printTwoData("CODE",customerBean.getCode()));
                        tvInfo.append(DataUtils.printTwoData("\nCARD NUMBER",customerBean.getCard_number()));
                        tvInfo.append(DataUtils.printTwoData("\nNAME",customerBean.getFirst_name()+" "+customerBean.getLast_name()));
                        tvInfo.append(DataUtils.printTwoData("\nPHONE",customerBean.getPhone()));
                        tvInfo.append(DataUtils.printTwoData("\nEMAIL",customerBean.getEmail()));
                        tvInfo.append(DataUtils.printTwoData("\nSTREET",customerBean.getStreet1()+ " "
                                +customerBean.getStreet2() +customerBean.getCity())+customerBean.getState()+customerBean.getPostal_code());
                        tvInfo.append(DataUtils.printTwoData("\nDATA",customerBean.getCustom_date()));

                        if (!DataUtils.isEmpty(campaignsBeanList)){
                            adapter = new RetrieveCampaignsAdapter(this, campaignsBeanList);
                            lvList.setAdapter(adapter);
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

                        Led led = new Led();
                        Led.setLight(Led.LED_NFC_1,Led.LED_FLASH);
                        Led.setLight(Led.LED_NFC_2,Led.LED_FLASH);
                        Led.setLight(Led.LED_NFC_3,Led.LED_FLASH);
                        Led.setLight(Led.LED_NFC_4,Led.LED_FLASH);
                        showDialogSuccessToast("Transaction amount $100.00 \n Current balance $900.00");
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


    /**
     * 显示和隐藏输入框
     * Show and hide the input box
     */
    private void showInputView() {
        if (isShowViewInput){
            isShowViewInput =false;
            llInputBox.startAnimation(mShowAction);
            llInputBox.setVisibility(View.VISIBLE);
        }else  {
            isShowViewInput= true;
            llInputBox.setVisibility(View.GONE);
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_retrieve_customer;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 3) {
            list = (ArrayList<Result.CampaignsBean>) data.getSerializableExtra("L");
            L.d(list.toString());
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    tvAllowedCampaigns.setText(list.get(i).getName());
                    allowed_campaigns = list.get(i).getId();
                } else {
                    tvAllowedCampaigns.append("," + list.get(i).getName());
                    allowed_campaigns = allowed_campaigns + "," + list.get(i).getId();
                }
            }
            L.d(allowed_campaigns);
        }
    }
    ////////////////////**

    private void initView() {
        lsincludes = Arrays.asList(getResources().getStringArray(R.array.includes));
        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, lsincludes);
        nsIncludeBalances.setAdapter(adapter1);
        nsIncludeBalances.setOnItemClickListener((parent, view, position, id) -> {
            include_balances=lsincludes.get(position);
        });
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
    }

    @Override
    public void success() {

        Led.setLight(Led.LED_NFC_1,Led.LED_OFF);
        Led.setLight(Led.LED_NFC_2,Led.LED_OFF);
        Led.setLight(Led.LED_NFC_3,Led.LED_OFF);
        Led.setLight(Led.LED_NFC_4,Led.LED_OFF);
    }
}
