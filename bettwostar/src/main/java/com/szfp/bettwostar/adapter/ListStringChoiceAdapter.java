package com.szfp.bettwostar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.szfp.bettwostar.R;
import com.szfp.bettwostar.inter.OnClickSuccess;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 戴尔 on 2018/1/23.
 */

public class ListStringChoiceAdapter extends BaseListAdapter<String> {

    List<String> list = new ArrayList<>();
    OnClickSuccess onClickSuccess;
    public ListStringChoiceAdapter(Context mContext, List<String> mDatas,OnClickSuccess onClickSuccess) {
        super(mContext, mDatas);
        this.onClickSuccess = onClickSuccess;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      ViewHolder viewHolder;
        if (convertView ==null){
            convertView = ContextUtil.inflate(mContext, R.layout.layout_text);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else  viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.layTv.setOnClickListener(v -> {
            if (list.contains(String.valueOf(position+3))){
                list.remove(String.valueOf(position+3));
                v.setSelected(false);
            }else {
                list.add(String.valueOf(position+3));
                v.setSelected(true);
            }

            onClickSuccess.OnClickCallBack(list.size()==0?new ArrayList<>():list);
        });


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
