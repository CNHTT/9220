package com.szfp.bettwostar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.player.util.L;
import com.szfp.bettwostar.R;
import com.szfp.bettwostar.model.entities.GroupBean;
import com.szfp.bettwostar.model.entities.MakeBet;

import java.util.List;

import static com.szfp.bettwostar.App.sorts;
import static com.szfp.bettwostar.App.types;

/**
 * Created by 戴尔 on 2018/1/31.
 */

public class ReprintBetAdapter extends BaseListAdapter<MakeBet> {


    private MakeBet makeBet;
    private MakeBet<List<GroupBean>> listMakeBet;
    public ReprintBetAdapter(Context mContext, List<MakeBet> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH  vh;
        if (convertView == null){
            convertView = ContextUtil.inflate(mContext, R.layout.bet_layout);
            vh = new VH(convertView);
            convertView.setTag(vh);
        } else vh = (VH) convertView.getTag();
        vh.setData(getItem(position),position);
        return convertView;
    }


    private class VH{

        private TextView mTv_title;
        private TextView mTv_result;
        private TextView mTv_id;
         VH(View view) {

            mTv_title = view.findViewById(R.id.tv_title);
            mTv_result = view.findViewById(R.id.tv_result);
            mTv_id = view.findViewById(R.id.tv_id);

        }

        @SuppressLint("SetTextI18n")
        void setData(MakeBet data, int position) {
            try {
                mTv_id.setText(data.getBet_id());
                mTv_title .setText(position+1+"  "+types.get(Integer.valueOf(data.getType())-1)+"  " +sorts.get( Integer.valueOf(data.getSort())-1) +
                        "  U"  +  data.getUnder() + "  #"+data.getAmount());
                if (data.getType().equals("3")){
                    mTv_result.setText("Games: " +  data.getGames().toString());
                }else {
                    mTv_result.setText("Games: " + data.getGames().toString().replace(",","-"));
                }
            }catch (Exception e){
                L.d(e.getMessage());
            }

        }
    }
}
