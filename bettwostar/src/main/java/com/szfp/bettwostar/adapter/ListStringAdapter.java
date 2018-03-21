package com.szfp.bettwostar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.szfp.bettwostar.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2018/1/23.
 */

public class ListStringAdapter extends BaseListAdapter<String> {
    public ListStringAdapter(Context mContext, List<String> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView ==null){
            convertView = ContextUtil.inflate(mContext, R.layout.layout_text);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else  viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.setData(getItem(position));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.lay_tv)
        TextView layTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(String data) {
            layTv.setText(data);
        }
    }
}
