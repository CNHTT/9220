package com.odd.sportal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.odd.sportal.R;
import com.odd.sportal.inter.OnBetAdapterListener;
import com.odd.sportal.model.BetBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2017/12/4.
 */

public class BetAdapter extends BaseListAdapter<BetBean> {

    private OnBetAdapterListener listener;

    public BetAdapter(Context mContext, List<BetBean> mDatas, OnBetAdapterListener listener) {
        super(mContext, mDatas);
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (DataUtils.isEmpty(convertView)) {
            convertView = ContextUtil.inflate(mContext, R.layout.layout_bet_item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        BetBean bean = getItem(position);
        viewHolder.tvDate.setText(bean.getDate());
        viewHolder.tvStartTime.setText(bean.getStartTime());
        viewHolder.tvRn.setText(bean.getRn());
        viewHolder.tvSport.setText(bean.getSport());

        viewHolder.tvHomeTeam.setText(bean.getHomeTeam());
        viewHolder.tvAwayTeam.setText(bean.getAwayTeam());
        viewHolder.tvCompetition.setText(bean.getCompetition());

        viewHolder.llBetView.setOnClickListener( v ->{
            listener.getBetBean(bean);
        });


        return convertView;
    }


    static  class ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_rn)
        TextView tvRn;
        @BindView(R.id.tv_sport)
        TextView tvSport;
        @BindView(R.id.tv_home_team)
        TextView tvHomeTeam;
        @BindView(R.id.tv_away_team)
        TextView tvAwayTeam;
        @BindView(R.id.tv_competition)
        TextView tvCompetition;
        @BindView(R.id.ll_bet_view)
        LinearLayout llBetView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
