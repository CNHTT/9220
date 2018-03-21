package com.extra.loyalty.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.CustomerBean;
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
import com.pos.device.picc.MifareClassic;
import com.pos.device.picc.PiccReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransactionDeleteActivity extends BaseActivity {

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
    private Map<String, String> map;
    private String code;
    private  String  campaign_id;
    private String card_number;
    private Result.CampaignsBean campaignsBean;
    private CustomerBean customerBean;

    private String transaction_id;
    private List<String> data;
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
        toolbar.setTitle(R.string.delete_a_transaction);

    }

    @OnClick({R.id.tv_card_number, R.id.bt_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_card_number:
                showCardNumber();
                break;
            case R.id.bt_find:
                showPostData();
                break;
        }
    }

    /**
     * 删除交易
     */
    private void showPostData() {

        transaction_id = tvAmount.getText().toString();
        if (DataUtils.isNullString(transaction_id)){
            ToastUtils.showToast("Please input transaction id");return;
        }
        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.TYPE,"transaction_delete");
        map.put(ConstantValue.campaign_id,campaign_id);
        map.put(ConstantValue.code,code);
        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success( s->{cancleProgressDialog();
                    L.d(s);
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){
                       ToastUtils.showToast("Success");
                    }else {
                        ToastUtils.showToast(result.getError());
                    }

                })
                .error( e ->{cancleProgressDialog();
                    L.d(e.toString());

                }).post();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_transaction_delete;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<String> list1 = new ArrayList<>();
        list1.add("Campaigns");
        pvAllowedCampaigns.setData(list1);
        initView();
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
        map.put(ConstantValue.TYPE,ConstantValue.customer_info);
        map.put(ConstantValue.card_number,card_number);
        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success( s->{
                    L.d(s);
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){
                        customerBean  = result.getCustomer();
                        code = customerBean.getCode();
                        if (!DataUtils.isNullString(customerBean.getFirst_name()))
                            tvCardNumber.setText(customerBean.getFirst_name()+" "+customerBean.getLast_name());
                    }else {
                        ToastUtils.showToast(result.getError());
                    }

                })
                .error( e ->{
                    L.d(e.toString());

                }).post();

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
                        campaignsBean = list.get(0);
                        campaign_id =  list.get(0).getId();

                        pvAllowedCampaigns.setOnSelectListener((text, position) -> {
                            campaign_id = list.get(dataCopy.indexOf(text)).getId();

                            campaign_id =  list.get(dataCopy.indexOf(text)).getId();
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
