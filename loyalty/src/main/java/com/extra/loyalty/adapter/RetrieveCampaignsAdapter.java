package com.extra.loyalty.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.Result;
import com.extra.utils.ContextUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2018/1/11.
 */

public class RetrieveCampaignsAdapter extends BaseListAdapter<Result.CampaignsBean> {
    public RetrieveCampaignsAdapter(Context mContext, List<Result.CampaignsBean> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView == null) {
            convertView = ContextUtil.inflate(mContext, R.layout.item_retrieve_campaigns);
            vh = new VH(convertView);
            convertView.setTag(vh);
        }else  vh = (VH) convertView.getTag();

        Result.CampaignsBean campaignsBean =getItem(position);

        vh.tvName.setText(campaignsBean.getName());
        vh.tvType.setText(campaignsBean.getType());
        vh.tvBalance.setText(campaignsBean.getBalance());
        vh.tvCumulative.setText(campaignsBean.getCumulative());
        vh.tvBalanceCoalition.setText(campaignsBean.getBalance_coalition());
        vh.tvCumulativeCoalition.setText(campaignsBean.getCumulative_coalition());
        return convertView;
    }


    static class VH {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_balance)
        TextView tvBalance;
        @BindView(R.id.tv_cumulative)
        TextView tvCumulative;
        @BindView(R.id.tv_balance_coalition)
        TextView tvBalanceCoalition;
        @BindView(R.id.tv_cumulative_coalition)
        TextView tvCumulativeCoalition;

        VH(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
