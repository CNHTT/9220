package com.extra.loyalty.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.extra.adapter.BaseListAdapter;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.Result.CampaignsBean;
import com.extra.utils.ContextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2017/11/30.
 */

public class AllowedCampaignAdapter extends BaseListAdapter<CampaignsBean> {
    private Map<Integer,Boolean> map=new HashMap<>();// 存放已被选中的CheckBox
    private List<CampaignsBean> list = new ArrayList<>();
    public AllowedCampaignAdapter(Context mContext, List<CampaignsBean> mDatas) {
        super(mContext, mDatas);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = ContextUtil.inflate(mContext, R.layout.item_campaign_check_layout);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else  viewHolder = (ViewHolder) convertView.getTag();

        CampaignsBean campaignsBean = getItem(position);

        viewHolder.ckCampaign.setText(campaignsBean.getName()+"  ("+campaignsBean.getType()+")");
        viewHolder.ckCampaign.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    list.add(campaignsBean);
                    map.put(position,true);
                }else {
                    map.remove(position);
                    if (list.contains(campaignsBean)) list.remove(campaignsBean);
                }

            }
        });

        if(map.containsKey(position)){
            viewHolder.ckCampaign.setChecked(true);
        }else {
            viewHolder.ckCampaign.setChecked(false);
        }

        return convertView;
    }

    public void clear(){
        list = new ArrayList<>();
        notifyDataSetChanged();
    }

    public List<CampaignsBean> getList() {
        return list;
    }

    static class ViewHolder {
        @BindView(R.id.ck_campaign)
        CheckBox ckCampaign;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
