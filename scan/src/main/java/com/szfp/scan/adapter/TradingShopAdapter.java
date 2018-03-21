package com.szfp.scan.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.szfp.scan.R;
import com.szfp.scan.bean.TradingOrderModel;
import com.szfp.scan.bean.TradingShopModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2017/12/25.
 */

public class TradingShopAdapter extends BaseListAdapter<TradingShopModel> {
    public TradingShopAdapter(Context mContext, List<TradingShopModel> mDatas) {
        super(mContext, mDatas);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        VH vh;

        if (DataUtils.isEmpty(convertView)) {
            convertView = ContextUtil.inflate(mContext, R.layout.layout_order_shop);
            vh = new VH(convertView);
            convertView.setTag(vh);
        };vh = (VH) convertView.getTag();

        TradingShopModel shopModel = getItem(position);
        vh.rightDishName.setText(shopModel.getName());
        vh.rightDishPrice.setText(shopModel.getPrice()+"");
        vh.rightDishNum.setText(shopModel.getNum()+"");
        vh.rightDishAccount.setText(shopModel.getSellNum()+"");
        return convertView;

    }

    static class VH {
        @BindView(R.id.right_dish_name)
        TextView rightDishName;
        @BindView(R.id.right_dish_price)
        TextView rightDishPrice;
        @BindView(R.id.right_dish_num)
        TextView rightDishNum;
        @BindView(R.id.right_dish_account)
        TextView rightDishAccount;
        @BindView(R.id.right_dish_item)
        LinearLayout rightDishItem;

        VH(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
