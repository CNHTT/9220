package com.szfp.scan.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.extra.utils.ToastUtils;
import com.szfp.scan.AddInvActivity;
import com.szfp.scan.App;
import com.szfp.scan.R;
import com.szfp.scan.bean.ShopModel;
import com.szfp.scan.bean.TradingShopModel;
import com.szfp.scan.inter.OnShopModelLongClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2017/12/25.
 */

public class ShopModelAdapter extends BaseListAdapter<ShopModel> {

    OnShopModelLongClickListener  listener;


    public ShopModelAdapter(Context mContext, List<ShopModel> mDatas,OnShopModelLongClickListener listener) {
        super(mContext, mDatas);
        this.listener=listener;
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

        ShopModel shopModel = getItem(position);
        vh.rightDishName.setText(shopModel.getName()+"-"+shopModel.getItemNo());
        vh.rightDishPrice.setText(shopModel.getPrice()+"");
        vh.rightDishNum.setText(shopModel.getNum()+"");
        vh.lv.setVisibility(View.GONE);

        vh.rightDishItem.setOnLongClickListener(v -> {

            if (App.isTurn)
                listener.loadShopModel(shopModel,position);else ToastUtils.showToast("Insufficient authority");

            return false;
        });


        return convertView;

    }

    public void updateItem(ShopModel shopModel, int position) {
        mDatas.set(position,shopModel
        );
        notifyDataSetChanged();
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
        @BindView(R.id.ll)
        LinearLayout lv;

        VH(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
