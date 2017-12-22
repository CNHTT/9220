package com.szfp.scan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.extra.presenter.BasePresenter;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
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
    EditText etShopSn;
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

    @OnClick(R.id.btn_new)
    public void onViewClicked() {
        name = etShopName.getText().toString();
        sn = etShopSn .getText().toString();
        price  = etPrice.getText().toString();
        num  = etQuantity.getText().toString();

        if (DataUtils.isNullString(name)||DataUtils.isNullString(sn)||DataUtils.isNullString(price)||DataUtils.isNullString(num))
        {
            ToastUtils.showToast("Please Input Edit"); return;
        }
        ShopModel shopModel = new ShopModel(name,sn,Integer.parseInt(num),DataUtils.stringToDouble(DataUtils.format2Decimals(price)));
         if (DbHelper.insert(shopModel)){
             etShopName.setText("");
             etShopSn.setText("");
             etPrice.setText("");
             etQuantity.setText("");
         }

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_add_inv;
    }
}
