package com.extra.loyalty.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.CustomerBean;
import com.extra.loyalty.model.entities.ReceiptBean;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.loyalty.utils.PrintManager;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.PickerView;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.dialog.BaseDialog;
import com.extra.widget.listview.InnerScrollerListView;
import com.player.util.L;
import com.pos.device.picc.MifareClassic;
import com.pos.device.picc.PiccReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransactionRecordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.pv_allowed_campaigns)
    PickerView pvAllowedCampaigns;
    @BindView(R.id.tv_amount)
    EditText tvAmount;
    @BindView(R.id.bt_find)
    Button btFind;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.lv_list)
    InnerScrollerListView lvList;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.tv_description)
    EditText tvDescription;
    @BindView(R.id.cb_send_email)
    CheckBox cbSendEmail;
    private String card_number;
    private String code;
    private String campaign_id;
    private Result.CampaignsBean campaignsBean;
    private CustomerBean customerBean;
    private List<Result.CampaignsBean.CustomerBean.TransactionsBean> transactionsBeans;


    private ReceiptBean receiptBean;
    private Map<String, String> map;
    private List<String> data;
    private List<String> dataCopy;
    private List<Result.CampaignsBean> list = new ArrayList<>();
    private String amount;


    private boolean isSacle=false;
    private boolean isSendEmail=false;
    private String authorization;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.rct);
    }

    @OnClick({R.id.tv_card_number, R.id.bt_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_card_number:
                showCardNumber();
                break;
            case R.id.bt_find:
                showPost();
                break;
        }
    }


    /**
     * 提交
     */
    private void showPost() {

        String sacle = SPUtils.getString(this,ConstantValue.SCALE);
        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.TYPE, "record_activity");
        map.put(ConstantValue.campaign_id, campaign_id);
        map.put(ConstantValue.code, code);

        amount = tvAmount.getText().toString();
        authorization = tvDescription .getText().toString();
        if (!DataUtils.isNullString(authorization))map.put("authorization",authorization);
        if (isSacle){
            if (!DataUtils.isNullString(sacle)){
                double sc = Integer.parseInt(sacle);
                int a= (int) (Double.valueOf(amount)* (sc / 100));
                amount = String.valueOf(a);
            }

        }

        if (isSendEmail) map.put("send_transaction_email","Y");



        switch (campaignsBean.getType()) {
            case "points":
                if (!DataUtils.isNullString(amount))
                    map.put("amount", amount);
                break;
            case "giftcard":
                if (DataUtils.isNullString(amount)) {
                    ToastUtils.showToast("Please input");
                    return;
                }
                map.put("amount", amount);
                break;
            case "events":
                break;
            case "earned":
                if (DataUtils.isNullString(amount)) {
                    ToastUtils.showToast("Please input");
                    return;
                }
                map.put("service_product", amount);
                break;
            case "buyx":
                if (DataUtils.isNullString(amount)) {
                    ToastUtils.showToast("Please input");
                    return;
                }
                map.put("service_product", amount);
                break;
        }
        showProgressDialog(R.string.xw_ptr_loading);
        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success(s -> {
                    cancleProgressDialog();
                    L.d(s);
                    Result result = (Result) JsonUtil.stringToObject(s, Result.class);
                    if (result.checkResult()) {
                        receiptBean = result.getReceipt();
                        PrintManager.getmInstance(this).printReceipt(receiptBean,"Record Transaction",isSacle,true);

                        tvInfo.setText(DataUtils.printTwoData("ACCOUNT NAME", receiptBean.getAccount_name()));
                        tvInfo.setText(DataUtils.printTwoData("CAMPAIGN NAME", receiptBean.getCampaign().getName()));
                        tvInfo.append(DataUtils.printTwoData("\nNAME", receiptBean.getCustomer().getFirst_name() + " " + receiptBean.getCustomer().getLast_name()));

                        if (receiptBean.getCampaign().getType().equals("points")) {
                            tvInfo.append(DataUtils.printTwoData("\npurchase", receiptBean.getTransaction().getPurchase().getAmount()));
                            tvInfo.append(DataUtils.printTwoData("\nrecorded", receiptBean.getTransaction().getRecorded().getPoints()));
                            tvInfo.append(DataUtils.printTwoData("\nbalance", receiptBean.getTransaction().getBalance().getPoints()));
                            tvInfo.append(DataUtils.printTwoData("\ncumulative_balance", receiptBean.getTransaction().getCumulative_balance().getPoints()));
                        } else if (receiptBean.getCampaign().getType().equals("giftcard")) {
                            tvInfo.append(DataUtils.printTwoData("\nadd", receiptBean.getTransaction().getAdd().getAmount()));
                            tvInfo.append(DataUtils.printTwoData("\nbalance", receiptBean.getTransaction().getBalance().getAmount()));
                            tvInfo.append(DataUtils.printTwoData("\ncumulative_balance", receiptBean.getTransaction().getCumulative_balance().getAmount()));
                            tvInfo.append(DataUtils.printTwoData("\ncoalition_cumulative_balance", receiptBean.getTransaction().getCoalition_cumulative_balance().getAmount()));
                        }
                    } else {
                        ToastUtils.showToast(result.getError());
                    }

                })
                .error(e -> {
                    cancleProgressDialog();
                    L.d(e.toString());

                }).post();


    }

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
                        card_number = DataUtils.toHexString(card.getUID());

                        loadCodeFormCard(card_number);
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
     * 获取code
     */
    @SuppressLint("SetTextI18n")
    private void loadCodeFormCard(String card_number) {
        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.TYPE, ConstantValue.customer_info);
        map.put(ConstantValue.card_number, card_number);
        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success(s -> {
                    L.d(s);
                    Result result = (Result) JsonUtil.stringToObject(s, Result.class);
                    if (result.checkResult()) {
                        customerBean = result.getCustomer();
                        code = customerBean.getCode();
                        if (!DataUtils.isNullString(customerBean.getFirst_name()))
                            tvCardNumber.setText(customerBean.getFirst_name() + " " + customerBean.getLast_name());
                    } else {
                        ToastUtils.showToast(result.getMessage() );
                    }

                })
                .error(e -> {
                    L.d(e.toString());

                }).post();

    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_transaction_record;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                    Result result = (Result) JsonUtil.stringToObject(s, Result.class);
                    if (result.checkResult()) {
                        list = result.getCampaigns();
                        dataCopy = new ArrayList<>();
                        data = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            data.add(list.get(i).getName() + "(" + list.get(i).getType() + ")");
                            dataCopy.add(list.get(i).getName() + "(" + list.get(i).getType() + ")");
                        }
                        pvAllowedCampaigns.setData(data);
                        pvAllowedCampaigns.setSelected(0);
                        campaignsBean = list.get(0);
                        campaign_id = list.get(0).getId();

                        pvAllowedCampaigns.setOnSelectListener((text, position) -> {
                            campaign_id = list.get(dataCopy.indexOf(text)).getId();

                            campaign_id = list.get(dataCopy.indexOf(text)).getId();
                            L.d(text + campaign_id + "  " + dataCopy.indexOf(text) + "  " + position);
                        });

                    } else {
                        showDialogSuccessToast(result.getError());
                    }

                })
                .error(e -> {
                    cancleProgressDialog();

                })
                .post();
        cbSendEmail.setOnCheckedChangeListener((buttonView, isChecked) -> isSendEmail = isChecked);
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> isSacle = isChecked);
    }

}
