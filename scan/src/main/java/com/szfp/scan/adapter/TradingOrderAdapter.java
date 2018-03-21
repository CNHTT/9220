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
import com.szfp.scan.inter.OnLoadTradingOrderBeanListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2017/12/25.
 */

public class TradingOrderAdapter extends BaseListAdapter<TradingOrderModel> {

    OnLoadTradingOrderBeanListener listener;

    public TradingOrderAdapter(Context mContext, List<TradingOrderModel> mDatas, OnLoadTradingOrderBeanListener listener) {
        super(mContext, mDatas);
        this.listener = listener;
    }

    public TradingOrderAdapter(Context context) {
        super(context);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VH vh;

        if (DataUtils.isEmpty(convertView)) {
            convertView = ContextUtil.inflate(mContext, R.layout.layout_order_trading);
            vh = new VH(convertView);
            convertView.setTag(vh);
        };vh = (VH) convertView.getTag();

        TradingOrderModel model = mDatas.get(position);
        vh.tradingView.setOnClickListener( view->{
            listener.loadOraderBean(model);
        });

        vh.sn.setText(model.getSn());
        vh.time.setText(model.getCreateTime().toString());
        vh.totalNum.setText(model.getTotalNum()+"");
        vh.totalAmount.setText(model.getTotalAmoumt()+"");
        vh.operator.setText(model.getOpterator());
        return convertView;
    }


    static class VH {
        @BindView(R.id.sn)
        TextView sn;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.total_num)
        TextView totalNum;
        @BindView(R.id.total_amount)
        TextView totalAmount;
        @BindView(R.id.operator)
        TextView operator;
        @BindView(R.id.trading_View)
        LinearLayout tradingView;

        VH(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
