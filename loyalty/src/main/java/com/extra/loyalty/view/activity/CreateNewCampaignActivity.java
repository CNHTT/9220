package com.extra.loyalty.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.Result;
import com.extra.loyalty.utils.JsonUtil;
import com.extra.presenter.BasePresenter;
import com.extra.retrofit.HttpBuilder;
import com.extra.retrofit.HttpUtil;
import com.extra.utils.Constant;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.wheelhorizontal.AbstractWheel;
import com.extra.widget.wheelhorizontal.ArrayWheelAdapter;
import com.extra.widget.wheelhorizontal.OnWheelClickedListener;
import com.extra.widget.wheelhorizontal.OnWheelScrollListener;
import com.extra.widget.wheelhorizontal.WheelHorizontalView;
import com.player.util.L;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.extra.utils.DataUtils.isNullString;

public class CreateNewCampaignActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wh_type)
    WheelHorizontalView whType;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.et_campaign_name)
    EditText etCampaignName;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.et_points_ratio)
    EditText etPointsRatio;
    @BindView(R.id.et_reward_ratio)
    EditText etRewardRatio;
    @BindView(R.id.lv_point)
    LinearLayout lvPoint;
    @BindView(R.id.et_amount_per_event)
    EditText etAmountPerEvent;
    @BindView(R.id.lv_event)
    LinearLayout lvEvent;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private List<String> list;
    ArrayWheelAdapter<String> adapter;

    private Map<String,String> map;
    private String type = "giftcard";
    private String description ="";
    private String campaign_name;
    private String points_ratio ;
    private String reward_ratio ;
    private String amount_per_event ;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.create_new_campaign);
        StatusBarUtil.setTranslucent(this);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        list = Arrays.asList(getResources().getStringArray(R.array.type_restrict));
        adapter = new ArrayWheelAdapter<String>(this, getResources().getStringArray(R.array.type_restrict_value));
        adapter.setItemResource(R.layout.layout_wheel_type);
        adapter.setItemTextResource(R.id.tv_type);

        whType.setViewAdapter(adapter);
        whType.setCurrentItem(0);
        whType.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(AbstractWheel wheel) {

            }

            @Override
            public void onScrollingFinished(AbstractWheel wheel) {
                L.d(wheel.getCurrentItem() + "");
                type = list.get(wheel.getCurrentItem());
                showView();
            }
        });
        whType.addClickingListener(new OnWheelClickedListener() {
            @Override
            public void onItemClicked(AbstractWheel wheel, int itemIndex) {
                whType.setCurrentItem(itemIndex, true);
                type = list.get(itemIndex);
                showView();
            }
        });
    }

    private void showView() {
        switch (type){
            case "points":
                lvEvent.setVisibility(View.VISIBLE);
                lvPoint.setVisibility(View.GONE);
                break;

            case "earned":
                lvPoint.setVisibility(View.VISIBLE);
                lvEvent.setVisibility(View.GONE);
                break;
            default:
                lvEvent.setVisibility(View.GONE);
                lvPoint.setVisibility(View.GONE);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_create_new_campaign;
    }



    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        campaign_name = etCampaignName.getText().toString();
        description = etDescription.getText().toString();
        amount_per_event = etAmountPerEvent.getText().toString();
        points_ratio = etPointsRatio.getText().toString();
        reward_ratio = etRewardRatio.getText().toString();

        map = ConstantValue.getPostMap1(map);
        map.put(ConstantValue.TYPE,ConstantValue.CAMPAIGN_NEW);
        map.put(ConstantValue.ACTION,ConstantValue.CAMPAIGN);
        map.put(ConstantValue.CAMPAIGN_TYPE,type);
        map.put(ConstantValue.DESCRIPTION,description);
        map.put(ConstantValue.CAMPAIGN_NAME,campaign_name);

        if (type.equals("earned")){
            if (isNullString(amount_per_event)){
                ToastUtils.showToast(R.string.no_null);
                return;
            }else {
                map.put(ConstantValue.AMOUNT_PER_EVENT,amount_per_event);
            }

        }else if (type.equals("points")){
            if (isNullString(points_ratio)||isNullString(reward_ratio)){
                ToastUtils.showToast(R.string.no_null);
                return;
            }else {
                map.put(ConstantValue.POINTS_RATIO,amount_per_event);
                map.put(ConstantValue.REWARD_RATIO,amount_per_event);
            }
        }

        showProgressDialog(R.string.xw_ptr_loading);
        new HttpBuilder(ConstantValue.localhost)
                .tag(this)
                .params(map)
                .success(s ->{
                    cancleProgressDialog();
                    Result result  = (Result) JsonUtil.stringToObject(s,Result.class);
                    if (result.checkResult()){

                    }else {
                        showDialogToast(result.getError());
                    }
                })
                .error( e ->{
                    cancleProgressDialog();

                    L.d(e.toString());
                    showDialogToast(e[0].toString());
                })
                .post();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtil.cancel(this);
    }
}
