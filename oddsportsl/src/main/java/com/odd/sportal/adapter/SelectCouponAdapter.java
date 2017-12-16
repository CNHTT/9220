package com.odd.sportal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.odd.sportal.R;
import com.odd.sportal.model.SelectCouponBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 戴尔 on 2017/12/4.
 */

public class SelectCouponAdapter extends BaseListAdapter<SelectCouponBean> {

   public SelectCouponBean selectCouponBean;

    public SelectCouponAdapter(Context mContext, List<SelectCouponBean> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (DataUtils.isEmpty(convertView)){
            convertView = ContextUtil.inflate(mContext, R.layout.layout_select_coupon_item);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else viewHolder  = (ViewHolder) convertView.getTag();
        final SelectCouponBean bean = getItem(position);

        viewHolder.tvTo.setText(bean.getTo());
        viewHolder.tvFrom.setText(bean.getFrom());
        viewHolder.tvCoupon.setText(bean.getCoupon());
        viewHolder.ckCurrent.setChecked(bean.isCurrent());

        viewHolder.ckCurrent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    bean.setCurrent(isChecked);
                    for (SelectCouponBean s:mDatas) {
                        if (s!=bean){
                            s.setCurrent(false);
                        }
                    }
                    selectCouponBean = bean;
                }else{
                    bean.setCurrent(false);
                    selectCouponBean = null ;
                }
                notifyDataSetChanged();
            }
        });



        return convertView;
    }


    public SelectCouponBean getSelectCouponBean() {
        return selectCouponBean;
    }

    static class ViewHolder {
        @BindView(R.id.tv_from)
        TextView tvFrom;
        @BindView(R.id.tv_to)
        TextView tvTo;
        @BindView(R.id.ck_current)
        CheckBox ckCurrent;
        @BindView(R.id.tv_coupon)
        TextView tvCoupon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
