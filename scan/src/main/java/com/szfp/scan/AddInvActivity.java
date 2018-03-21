package com.szfp.scan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.pos.device.scanner.OnScanListener;
import com.pos.device.scanner.Scanner;
import com.szfp.scan.bean.ShopModel;
import com.szfp.scan.util.DbHelper;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddInvActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_shop_name)
    EditText etShopName;
    @BindView(R.id.et_shop_sn)
    TextView etShopSn;
    @BindView(R.id.et_quantity)
    EditText etQuantity;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.btn_new)
    Button btnNew;


    private String name;
    private String sn;
    private String num;
    private String price;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.add_inv);
    }

    ShopModel shopModel;

    private boolean isUpdate = false;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initData();

    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        shopModel = (ShopModel) getIntent().getSerializableExtra("ShopModel");
        if (shopModel!=null){
            isUpdate =true;
            toolbar.setTitle("Update Product");
            etQuantity.setText(shopModel.getNum()+"");
            etShopName.setText(shopModel.getName());
            etPrice.setText(shopModel.getPrice()+"");
            etShopSn.setText(shopModel.getItemNo());
        }


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_add_inv;
    }

    boolean isScan =false;

    private boolean isContinueScan = false;
    private boolean isBackCamera = true;
    private boolean isBeep = true;
    private boolean isTorchOn = false;
    @OnClick({R.id.et_shop_sn, R.id.btn_new})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_shop_sn:
                if (isScan)return;
                isScan=true;
                Bundle realBundle = new Bundle();
                realBundle.putBoolean(com.pos.device.scanner.Scanner.SCANNER_CONTINUE_SCAN, isContinueScan);
                realBundle.putBoolean(com.pos.device.scanner.Scanner.SCANNER_IS_BACK_CAMERA, isBackCamera);
                realBundle.putBoolean(com.pos.device.scanner.Scanner.SCANNER_PLAY_BEEP, isBeep);
                realBundle.putBoolean(com.pos.device.scanner.Scanner.SCANNER_IS_TORCH_ON, isTorchOn);
                Scanner.getInstance().initScanner( realBundle);


                Scanner.getInstance().startScan(2000, (result, data) -> {
                    isScan = false;
                    try {
                        if (result == 0) {
                            System.out.println("扫码成功，获得结果data：" + new String(data));
                            etShopSn.setText(new String(data));
                        } else if (result == -1) {
                            System.out.println("用户退出扫码");
                        } else if (result == -3) {
                            System.out.println("扫码超时");
                        } else {
                            System.out.println("其他错误");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
            case R.id.btn_new:
                name = etShopName.getText().toString();
                sn = etShopSn .getText().toString();
                price  = etPrice.getText().toString();
                num  = etQuantity.getText().toString();

                if (DataUtils.isNullString(name)||DataUtils.isNullString(sn)||DataUtils.isNullString(price)||DataUtils.isNullString(num))
                {
                    ToastUtils.showToast("Please Input Edit"); return;
                }


                if (isUpdate){
                    shopModel.setName(name);
                    shopModel.setItemNo(sn);
                    shopModel.setNum(Integer.parseInt(num));
                    shopModel.setPrice(DataUtils.stringToDouble(DataUtils.format2Decimals(price)));
                   if (DbHelper.upDateShopModel(shopModel))finish();

                }else {

                    shopModel = new ShopModel(name,sn,Integer.parseInt(num),DataUtils.stringToDouble(DataUtils.format2Decimals(price)));
                    if (DbHelper.insert(shopModel)){
                        etShopName.setText("");
                        etShopSn.setText("");
                        etPrice.setText("");
                        etQuantity.setText("");
                    }
                    break;

                }



        }
    }
}
