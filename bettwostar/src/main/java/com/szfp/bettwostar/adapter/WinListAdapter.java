package com.szfp.bettwostar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.extra.view.activity.BaseActivity;
import com.szfp.bettwostar.R;
import com.szfp.bettwostar.model.entities.WinListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2018/2/1.
 */

public class WinListAdapter extends BaseListAdapter<WinListBean> {


    public WinListAdapter(Context mContext, List<WinListBean> mDatas) {
        super(mContext, mDatas);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VH  vh;

        if (convertView == null) {
            convertView = ContextUtil.inflate(mContext, R.layout.layout_text);
            vh = new VH(convertView);
            convertView.setTag(vh);
        }else vh  = (VH) convertView .getTag();

        vh.setData(getItem(position),position);
        return convertView;
    }

     class  VH {


        @BindView(R.id.lay_tv)
        TextView layTv;

        VH(View view) {
            ButterKnife.bind(this, view);
        }

        @SuppressLint("SetTextI18n")
        void setData(WinListBean data, int position) {
            layTv.setText(  "Bet : "+ data.getBet_id() + " \n Amount : " + data.getAmount());
        }
    }
}
