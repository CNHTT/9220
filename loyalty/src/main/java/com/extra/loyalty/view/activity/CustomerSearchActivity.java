package com.extra.loyalty.view.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.adapter.CustomersAdapter;
import com.extra.loyalty.inter.OnLongCustomerListener;
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
import com.extra.widget.dialog.DialogEditSureCancel;
import com.extra.widget.dialog.DialogSureCancel;
import com.player.util.L;
import com.pos.device.magcard.MagCardReader;
import com.pos.device.picc.IdentityCard;
import com.pos.device.picc.MifareClassic;
import com.pos.device.picc.PiccReader;
import com.pos.device.picc.PiccReaderCallback;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerSearchActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, OnLongCustomerListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ns_exact_match)
    BetterSpinner nsExactMatch;
    @BindView(R.id.ns_include_balances)
    BetterSpinner nsIncludeBalances;
    @BindView(R.id.ll_view)
    LinearLayout llView;
    @BindView(R.id.ll_input_box)
    LinearLayout ll_input_box;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.et_user_first_name)
    EditText etUserFirstName;
    @BindView(R.id.et_user_last_name)
    EditText etUserLastName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_state)
    EditText etState;
    @BindView(R.id.et_postal_code)
    EditText etPostalCode;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.et_custom_field)
    EditText etCustomField;
    @BindView(R.id.et_customer_username)
    EditText etCustomerUsername;
    @BindView(R.id.et_customer_password)
    EditText etCustomerPassword;
    @BindView(R.id.et_custom_PIN)
    EditText etCustomPIN;
    @BindView(R.id.rv_list)
    RecyclerView rvListV;

    private List<String> lsStr;
    ArrayAdapter<String> adapter;

    private List<String> lsincludes;
    private ArrayAdapter<String> adapter1;

    private boolean isShowViewInput=false;

    private TranslateAnimation mShowAction;
    private TranslateAnimation mHiddenAction;


    private Map<String, String> map;







    private String exact_match = "not included";
    private String account_id;
    private String card_number;
    private String first_name;
    private String last_name;
    private String phone;
    private String city;
    private String email;
    private String state;
    private String postal_code;
    private String custom_date;
    private String custom_field;
    private String customer_username;
    private String customer_password;
    private String customer_PIN;
    private String include_balances="N";

    private List<CustomerBean> customerBeans;
    private CustomersAdapter customersAdapter;



    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.customer_search);
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_customer_search;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initView();

    }

    private void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvListV.setLayoutManager(manager);
        lsStr = Arrays.asList(getResources().getStringArray(R.array.exact_matchs));
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, lsStr);
        nsExactMatch.setAdapter(adapter);

        lsincludes = Arrays.asList(getResources().getStringArray(R.array.includes));
        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, lsincludes);
        nsIncludeBalances.setAdapter(adapter1);

        nsExactMatch.setOnItemClickListener((parent, view, position, id) -> {
            exact_match = lsincludes.get(position);
        });
        nsIncludeBalances.setOnItemClickListener((parent, view, position, id) -> {
            include_balances=lsincludes.get(position);
        });
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);


    }


    @OnClick({R.id.tv_input_show,R.id.bt_search,R.id.tv_card_number,R.id.tv_date})
    public void onViewClicked(View view){
        switch (view.getId()){
            case  R.id.tv_input_show:
                showInputView();
                break;

            case  R.id.bt_search:
                search();
                break;
            case  R.id.tv_card_number:
                showCardNumber();
                break;

            case  R.id.tv_date:
                showDate();
                break;

        }
    }
    /**
     * 显示时间
     */
    private void showDate() {
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        int yrar = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //新建个日期对话框选择日期 ，并设置当前的时间为默认时间
        DatePickerDialog pickerDialog = new DatePickerDialog(this, this, yrar, month, day);
        pickerDialog.show();
    }
    public static byte[] intToBytes2(int value)
    {
        byte[] src = new byte[4];
        src[0] = (byte) ((value>>24) & 0xFF);
        src[1] = (byte) ((value>>16)& 0xFF);
        src[2] = (byte) ((value>>8)&0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }
    /**
     *   录入卡号查找
     *
     */
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
            ll_input_box.startAnimation(mShowAction);
            ll_input_box.setVisibility(View.VISIBLE);
        }else  {
            isShowViewInput= true;
            ll_input_box.setVisibility(View.GONE);
        }
    }

    /**
     *
     *Available
     * 查找用户记录
     *
     */
    private void search() {

        isShowViewInput= true;
        ll_input_box.setVisibility(View.GONE);


        showProgressDialog(R.string.xw_ptr_loading);
        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.TYPE,ConstantValue.customer_search);

        if (!exact_match.equals("not included"))map.put(ConstantValue.exact_match,exact_match);

        if (!DataUtils.isNullString(card_number))map.put(ConstantValue.card_number,card_number);

        first_name =etUserFirstName.getText().toString();
        if (!DataUtils.isNullString(first_name))map.put(ConstantValue.first_name,first_name);

        last_name = etUserLastName.getText().toString();
        if (!DataUtils.isNullString(last_name))map.put(ConstantValue.last_name,last_name);

        phone = etPhone.getText().toString();
        if (!DataUtils.isNullString(phone))map.put(ConstantValue.phone,phone);

        email = etEmail.getText().toString();
        if (!DataUtils.isNullString(email))map.put(ConstantValue.email,email);

        city = etCity.getText().toString();
        if (!DataUtils.isNullString(city))map.put(ConstantValue.city,city);

        state = etCity.getText().toString();
        if (!DataUtils.isNullString(state))map.put(ConstantValue.state,state);

        postal_code = etPostalCode.getText().toString();
        if (!DataUtils.isNullString(postal_code))map.put(ConstantValue.postal_code,postal_code);

        if (!DataUtils.isNullString(custom_date))map.put(ConstantValue.custom_date,custom_date);

        custom_date = etCustomerUsername.getText().toString();
        if (!DataUtils.isNullString(customer_username))map.put(ConstantValue.customer_username,customer_username);

        customer_password = etCustomerPassword.getText().toString();
        if (!DataUtils.isNullString(customer_password))map.put(ConstantValue.customer_password,customer_password);

        customer_PIN = etCustomPIN.getText().toString();
        if (!DataUtils.isNullString(customer_PIN))map.put(ConstantValue.customer_PIN,customer_PIN);

        if (!DataUtils.isNullString(include_balances))map.put(ConstantValue.include_balances,include_balances);

        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success( s -> {
                    cancleProgressDialog();
                    L.d(s);
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){
                        customerBeans = result.getCustomers();
                        if (customersAdapter == null){
                            customersAdapter = new CustomersAdapter(customerBeans,this);
                            rvListV.setAdapter(customersAdapter);
                        }else  customersAdapter.refresh(customerBeans);

                    }else {
                        ToastUtils.showToast(result.getMessage());
                        customersAdapter.refresh(new ArrayList<>());
                    }
                })
                .error( e ->{
                    cancleProgressDialog();
                    L.d(Arrays.toString(e));
                }).post();




    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = +1;
        String monthStr;
        String dayStr;

        if (month < 10) monthStr = "0" + month;
        else monthStr = String.valueOf(month);
        if (dayOfMonth < 10) dayStr = "0" + dayOfMonth;
        else dayStr = String.valueOf(dayOfMonth);
        custom_date = year + "-" + monthStr + "-" + dayStr;
        tvDate.setText(custom_date);
        L.d(custom_date);
    }


    private DialogSureCancel editSureCancel;
    @Override
    public void OnLongCustomer(CustomerBean bean) {

        editSureCancel = new DialogSureCancel(this,R.style.AlertDialogStyle);
        editSureCancel.setContent("Whether to delete change customers");
        editSureCancel.setSure("DELETE");
        editSureCancel.setCancel("CANCEL");
        editSureCancel.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSureCancel.cancel();
                showProgressDialog(R.string.xw_ptr_loading);
                map = ConstantValue.getPostMap1(map);
                map.put(ConstantValue.code,bean.getCode());
                map.put(ConstantValue.TYPE,ConstantValue.customer_delete);
                new HttpBuilder(ConstantValue.localhost)
                        .params(map)
                        .tag(this)
                        .success( s ->{
                            cancleProgressDialog();
                            Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                            if (result.checkResult()) {
                                customersAdapter.remove(bean);
                                ToastUtils.showToast("DELETE SUCCESS");
                            }else showDialogSuccessToast(result.getError());
                        }).error( e ->{
                    cancleProgressDialog();

                }).post();


            }
        });
        editSureCancel.setCancelListener(v -> editSureCancel.cancel());
        editSureCancel.show();

    }
}
