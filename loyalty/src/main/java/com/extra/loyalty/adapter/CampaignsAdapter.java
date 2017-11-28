package com.extra.loyalty.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.Result.CampaignsBean;
import com.extra.utils.ContextUtil;

import java.util.List;

/**
 * Created by 戴尔 on 2017/11/27.
 */

public class CampaignsAdapter extends BaseRecyclerAdapter<CampaignsAdapter.ViewHolder> {

    private List<CampaignsBean> list;
    private int largeCardHeight, smallCardHeight;


    public CampaignsAdapter(List<CampaignsBean> list, Context context) {
        this.list = list;
        largeCardHeight = ContextUtil.dip2px(context, 150);
        smallCardHeight = ContextUtil.dip2px(context, 100);
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view,false);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_campaigs_item, parent, false);
        ViewHolder vh = new ViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, boolean isItem) {
        CampaignsBean bean = getItem(position);
        holder.mTv_id.setText(bean.getId());
        holder.mTv_name.setText(bean.getName());
        holder.mTv_type.setText(bean.getType());

    }

    private CampaignsBean getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

    @Override
    public int getAdapterItemCount() {
        return  list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv_id;
        private TextView mTv_name;
        private TextView mTv_type;
        public ViewHolder(View itemView, boolean b) {
            super(itemView);
            if (b){
                mTv_id = (TextView) itemView.findViewById(R.id.tv_id);
                mTv_name = (TextView) itemView.findViewById(R.id.tv_name);
                mTv_type = (TextView) itemView.findViewById(R.id.tv_type);
            }
        }
    }




    /**
     * 刷新数据
     * @param datas
     */
    public void refresh(List<CampaignsBean> datas){
        this.list.clear();
        this.list.addAll(datas);
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     * @param datas
     */
    public void addData(List<CampaignsBean> datas){
        this.list.addAll(datas);
        notifyDataSetChanged();
    }
}
