package com.odd.sportal.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.extra.utils.TimeUtils;
import com.odd.sportal.R;
import com.odd.sportal.inter.OnBetAdapterListener;
import com.odd.sportal.inter.OnEventAdapterListener;
import com.odd.sportal.model.BetBean;
import com.odd.sportal.model.EventModel;
import com.odd.sportal.model.GameModel;

import java.sql.Time;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2017/12/12.
 */

public class EventModelAdapter  extends BaseListAdapter<EventModel> {

    private OnEventAdapterListener listener;

    public EventModelAdapter(Context mContext, List<EventModel> mDatas, OnEventAdapterListener listener) {
        super(mContext, mDatas);
        this.listener = listener;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (DataUtils.isEmpty(convertView)) {
            convertView = ContextUtil.inflate(mContext, R.layout.layout_bet_item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        EventModel bean = getItem(position);
        viewHolder.tvDate.setText(TimeUtils.getBetStrTime(bean.getLIVE_DT()));
        viewHolder.tvStartTime.setText(TimeUtils.getBetStrStartTime(bean.getLIVE_DT()));
        viewHolder.tvRn.setText(bean.getEVENT_ID()+"");
        viewHolder.tvSport.setText(bean.getET_NAME());

        viewHolder.tvHomeTeam.setText(bean.getTEAM1_NAME());
        viewHolder.tvAwayTeam.setText(bean.getTEAM2_NAME());
        viewHolder.tvCompetition.setText(bean.getEVT_COMMENT_UNC());

        viewHolder.llBetView.setOnClickListener( v ->{
            listener.getEventBean(bean);
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
