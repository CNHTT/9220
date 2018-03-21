package com.extra.loyalty.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.loyalty.R;
import com.extra.utils.ContextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 戴尔 on 2017/11/27.
 */

public class TypeRestrictAdapter extends BaseListAdapter<String> {

    private List<String> result = new ArrayList<>();
    public TypeRestrictAdapter(Context mContext, List<String> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = ContextUtil.inflate(mContext, R.layout.layout_check_item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(getItem(position));

        viewHolder.tvContent.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!result.contains(getItem(position))) result.add(getItem(position));
            }else
                if (result.contains(getItem(position))) result.remove(getItem(position));
        });


        return convertView;
    }

    private class ViewHolder {
        CheckBox tvContent;
        public ViewHolder(View convertView) {
            tvContent = (CheckBox) convertView.findViewById(R.id.ck_type);
        }
    }

    public List<String> getResult() {
        return result;
    }
}
