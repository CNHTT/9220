package com.szfp.bettwostar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.szfp.bettwostar.R;
import com.szfp.bettwostar.model.entities.MakeBet;
import com.szfp.bettwostar.model.entities.OddSummary;

import java.util.List;

import static com.szfp.bettwostar.App.sorts;

/**
 * Created by 戴尔 on 2018/2/1.
 */

public class OddSummaryAdapter  extends BaseListAdapter<OddSummary>{
    public OddSummaryAdapter(Context mContext, List<OddSummary> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView== null )
        {
            convertView = ContextUtil.inflate(mContext, R.layout.item_layout);
            vh = new VH(convertView);
            convertView.setTag(vh);
        }else  vh = (VH) convertView.getTag();
        vh.setData(getItem(position),position);

        return convertView;
    }
    private class  VH {
        private TextView mTv_title;
        private TextView mTv_result;


        VH(View view) {
            mTv_title = (TextView) view.findViewById(R.id.tv_title);
            mTv_result = (TextView)view.findViewById(R.id.tv_result);
        }

        @SuppressLint("SetTextI18n")
        void setData(OddSummary data, int position) {
                mTv_title .setText(position+1+"  " +sorts.get( Integer.valueOf(data.getSort())-1) +
                        "     Count"  +  data.getCount() );
                mTv_result.setText("  Gross: " +  data.getGross()
                        + "  Payable: "+data.getPayable() + "  Commission: "+data.getCommission());
        }
    }
}
