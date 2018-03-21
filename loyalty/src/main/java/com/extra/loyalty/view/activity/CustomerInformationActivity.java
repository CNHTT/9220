package com.extra.loyalty.view.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
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
import com.player.util.L;
import com.pos.device.picc.MifareClassic;
import com.pos.device.picc.PiccReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerInformationActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_allowed_campaigns)
    TextView tvAllowedCampaigns;
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
    @BindView(R.id.et_street1)
    EditText etStreet1;
    @BindView(R.id.et_street2)
    EditText etStreet2;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_state)
    EditText etState;
    @BindView(R.id.et_postal_code)
    EditText etPostalCode;
    @BindView(R.id.et_country)
    EditText etCountry;
    @BindView(R.id.et_auto_add)
    EditText etAutoAdd;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.ns_customer_action)
    TextView nsCustomerAction;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.et_custom_field)
    EditText etCustomField;
    @BindView(R.id.et_customer_username)
    EditText etCustomerUsername;
    @BindView(R.id.et_customer_password)
    EditText etCustomerPassword;
    @BindView(R.id.et_custom_PIN)
    EditText etCustomPIN;
    private BaseDialog customerActionDialog;

    private String customer_action = "new";
    private String allowed_campaigns;
    private String card_number;
    private String code;
    private String custom_date;
    private int card_number_generate;

    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postal_data;
    private String country;
    private String auto_add;
    private String custom_field;
    private String customer_username;
    private String customer_password;
    private String customer_PIN;


    private Map<String, String> map;
    private List<Result.CampaignsBean> list = new ArrayList<>();

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.ruc);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_customer_information;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @OnClick({R.id.ns_customer_action, R.id.tv_allowed_campaigns, R.id.tv_card_number, R.id.tv_code, R.id.tv_date, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ns_customer_action:
                showCustomerAction();
                break;
            case R.id.tv_allowed_campaigns:
                startActivityForResult(new Intent(this, AllowedCampaignActivity.class), 1);
                break;
            case R.id.tv_card_number:
                showCardNumber();
                break;
            case R.id.tv_code:
                showCode();
                break;
            case R.id.tv_date:
                showDate();
                break;
            case R.id.btn_ok:
                showRecordUpdate();
                break;
        }
    }

    /**
     * Record / Update Customer Information
     * Create or update a customer account; Returns a unique Account ID.
     */
    private void showRecordUpdate() {
        first_name = etUserFirstName.getText().toString();
        last_name = etUserLastName.getText().toString();
        phone = etPhone.getText().toString();
        email = etEmail.getText().toString();
        street1 = etStreet1.getText().toString();
        street2 = etStreet2.getText().toString();
        city = etCity.getText().toString();
        state = etState.getText().toString();
        postal_data = etPostalCode.getText().toString();
        country = etCountry.getText().toString();
        auto_add = etAutoAdd.getText().toString();

        custom_field = etCustomField.getText().toString();
        customer_username  = etCustomerUsername.getText().toString();
        customer_password =  etCustomerPassword.getText().toString();
        customer_PIN  = etCustomPIN.getText().toString();

        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.TYPE,ConstantValue.record_customer);
        map.put(ConstantValue.customer_action,customer_action);

        // 	campaign_id		1234567890123456 or 12344234,1234234,1234124      	Optional
        if (!DataUtils.isNullString(allowed_campaigns))
        map.put(ConstantValue.campaign_id,allowed_campaigns);

        //	card_number		1212121212		Optional
        //  card_number_generate		(# of digits)		Optional
        if (!DataUtils.isNullString(card_number)){
            map.put(ConstantValue.card_number,card_number);
//            map.put(ConstantValue.card_number_generate, String.valueOf(card_number_generate));
        }

        //code		89898989898989		Optional
        //new_code		Yes		Optional
        if (!DataUtils.isNullString(code))
            map.put(ConstantValue.code,code);
        else map.put(ConstantValue.new_code,"Yes");

        //first_name		John		Optional
        if (!DataUtils.isNullString(first_name))
            map.put(ConstantValue.first_name,first_name);

        //last_name		Doe		Optional
        if (!DataUtils.isNullString(last_name))
            map.put(ConstantValue.last_name,last_name);

        //phone		555-555-2455		Optional
        if (!DataUtils.isNullString(phone))
            map.put(ConstantValue.phone,phone);

        //email		test@email.com		Optional
        if (!DataUtils.isNullString(email))
            map.put(ConstantValue.email,email);

        //street1		123 Main St.		Optional
        if (!DataUtils.isNullString(street1))
            map.put(ConstantValue.street1,street1);

        //street2		Apt 3G		Optional
        if (!DataUtils.isNullString(street2))
            map.put(ConstantValue.street2,street2);

        //city		Anytown		Optional
        if (!DataUtils.isNullString(city))
            map.put(ConstantValue.city,city);

        //state		ZZ		Optional
        if (!DataUtils.isNullString(state))
            map.put(ConstantValue.state,state);

        //postal_code		55555		Optional
        if (!DataUtils.isNullString(postal_data))
            map.put(ConstantValue.postal_code,postal_data);

        //country		New Zealand		Optional
        if (!DataUtils.isNullString(country))
            map.put(ConstantValue.country,country);

        // custom_date		1970-07-14		Optional
        if (!DataUtils.isNullString(custom_date))
            map.put(ConstantValue.custom_date,custom_date);

        //custom_field		John likes cheese.		Optional
        if (!DataUtils.isNullString(custom_field))
            map.put(ConstantValue.custom_field,custom_field);

        //customer_username		jdoe1970		Optional
        if (!DataUtils.isNullString(customer_username))
            map.put(ConstantValue.customer_username,customer_username);

        //customer_password		ilovecheese		Optional
        if (!DataUtils.isNullString(customer_password))
            map.put(ConstantValue.customer_password,customer_password);

        //customer_PIN		1234		Optional
        if (!DataUtils.isNullString(customer_PIN))
            map.put(ConstantValue.customer_PIN,customer_PIN);


        new HttpBuilder(ConstantValue.localhost)
                .params(map)
                .tag(this)
                .success( s->{
                    L.d(s);
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){
                        ToastUtils.showToast("Add Success");
                    }else {
                        ToastUtils.showToast(result.getError());
                    }

                })
                .error( e ->{
                    L.d(e.toString());

                }).post();




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

    /**
     * If this is a "customer_action = new" call,
     * the 'card_number' will be used as the 'code' and that's what will be returned in the API response.
     * If you don't want that to happen,
     * then include the code_new parameter set to Yes to force the generation of a 16-digit UID.
     */
    private void showCode() {
        code = DataUtils.getLoyaltyCode();
        L.d("CODE  ==" + code);
        tvCode.setText(code);
    }

    /**
     * 读取卡号
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
                        card_number_generate = card_number.length();
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
     * select customer action
     */
    private TextView titleTextView;
    private ListView lvListView;
    private List<String> customers;
    private ArrayAdapter<String> arrayAdapter;

    @SuppressLint("SetTextI18n")
    private void showCustomerAction() {
        if (customerActionDialog == null) {
            View view = ContextUtil.inflate(this, R.layout.dialog_customer_action_layout);
            titleTextView = view.findViewById(R.id.tv_title);
            lvListView = view.findViewById(R.id.lv_list);
            customers = Arrays.asList(getResources().getStringArray(R.array.customer_action));
            arrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, customers);
            lvListView.setAdapter(arrayAdapter);
            titleTextView.setText("customer action");
            customerActionDialog = new BaseDialog(this, R.style.AlertDialogStyle);
            customerActionDialog.setContentView(view);
            customerActionDialog.setCancelable(false);
            lvListView.setOnItemClickListener((parent, view1, position, id) -> {
                customer_action = customers.get(position);
                nsCustomerAction.setText(customer_action);
                customerActionDialog.cancel();
            });
        }
        customerActionDialog.show();
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

    /**
     * 日期选择器回掉接口
     *
     * @param view
     * @param year
     * @param month
     * @param dayOfMonth
     */
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

}
