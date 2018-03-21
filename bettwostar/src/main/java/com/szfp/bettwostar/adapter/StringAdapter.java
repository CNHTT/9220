package com.szfp.bettwostar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.szfp.bettwostar.R;

import java.util.List;

/**
 * Created by 戴尔 on 2018/1/22.
 */

public class StringAdapter extends BaseListAdapter<String> {

    public StringAdapter(Context mContext, List<String> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = ContextUtil.inflate(mContext, R.layout.item_loyalty_programs_text);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else holder = (ViewHolder) convertView.getTag();

        holder.tvLoyaltyPrograms.setText(position+1+"."+mDatas.get(position));
        return convertView;
    }

    private class ViewHolder {
        TextView tvLoyaltyPrograms;

        ViewHolder(View view) {
            tvLoyaltyPrograms = view.findViewById(R.id.tv_loyalty_programs);
        }
    }
}
