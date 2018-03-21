package com.extra.loyalty.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.adapter.CustomersAdapter;
import com.extra.loyalty.inter.OnLongCustomerListener;
import com.extra.loyalty.model.entities.CustomerBean;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.dialog.DialogSureCancel;
import com.player.util.L;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerFindActivity extends BaseActivity implements OnLongCustomerListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_user_first_name)
    EditText etUserFirstName;
    @BindView(R.id.bt_search)
    ImageButton btSearch;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.ns_include_balances)
    BetterSpinner nsIncludeBalances;


    private Map<String, String> map;
    private String etStr;
    private String include_balances="N";

    private List<String> lsincludes;
    private ArrayAdapter<String> adapter1;

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
        toolbar.setTitle(R.string.cf);
    }

    @OnClick(R.id.bt_search)
    public void onViewClicked() {
        etStr = etUserFirstName.getText().toString();

        if (DataUtils.isNullString(etStr))
        {
            ToastUtils.showToast("Please Input Edit");
            return;
        }


        showProgressDialog(R.string.xw_ptr_loading);
        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.TYPE,ConstantValue.customer_find);
        map.put(ConstantValue.find_customer,etStr);
        map.put(ConstantValue.include_balances,include_balances);

        showProgressDialog(R.string.xw_ptr_loading);
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
                            rvList.setAdapter(customersAdapter);
                        }else  customersAdapter.refresh(customerBeans);

                    }else {
                        ToastUtils.showToast(result.getError());
                    }
                })
                .error( e ->{
                    cancleProgressDialog();
                    L.d(Arrays.toString(e));
                }).post();

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_customer_find;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();


    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(manager);
        lsincludes = Arrays.asList(getResources().getStringArray(R.array.includes));
        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, lsincludes);
        nsIncludeBalances.setAdapter(adapter1);
        nsIncludeBalances.setOnItemClickListener((parent, view, position, id) -> {
            include_balances=lsincludes.get(position);
        });
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
