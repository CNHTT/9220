package com.szfp.bettwostar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.extra.adapter.BaseListAdapter;
import com.extra.utils.ContextUtil;
import com.szfp.bettwostar.R;
import com.szfp.bettwostar.model.entities.GroupBean;
import com.szfp.bettwostar.model.entities.MakeBet;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 戴尔 on 2018/1/24.
 */

public class ItemAdapter extends BaseListAdapter<MakeBet> {

    private MakeBet makeBet;
    private MakeBet<List<GroupBean>> listMakeBet;


    private List<String> types;
    private List<String> sorts;
    public ItemAdapter(Context mContext, List<MakeBet> mDatas) {
        super(mContext, mDatas);
        types   = Arrays.asList(mContext.getResources().getStringArray(R.array.types));
        sorts   = Arrays.asList(mContext.getResources().getStringArray(R.array.sorts));

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView== null )
        {
            convertView = ContextUtil.inflate(mContext,R.layout.item_layout);
            vh = new VH(convertView);
            convertView.setTag(vh);
        }else  vh = (VH) convertView.getTag();
        vh.setData(getItem(position),position);

        return convertView;
    }

    private class  VH {
        private TextView mTv_title;
        private TextView mTv_result;


        public VH(View view) {
            mTv_title = (TextView) view.findViewById(R.id.tv_title);
            mTv_result = (TextView)view.findViewById(R.id.tv_result);

        }

        @SuppressLint("SetTextI18n")
        public void setData(MakeBet data, int position) {
            if (data.getType().equals("3")){
                listMakeBet = data;

                mTv_title .setText(position+1+"  "+types.get(Integer.valueOf(data.getType())-1)+"  " +sorts.get( Integer.valueOf(data.getSort())-1) +
                        "  U"  +  data.getUnder() + "  #"+data.getAmount());
                mTv_result.setText("Games: " +  data.getGames().toString().replace("[","").replace("]",""));
            }else { makeBet = data;
                mTv_title .setText(position+1+"  "+types.get(Integer.valueOf(data.getType())-1)+"  " + sorts.get(Integer.valueOf(data.getSort())-1) +
                        "  U"  + data.getUnder() + "  #"+data.getAmount());
                mTv_result.setText("Games: " + (String) data.getGames());

            }
        }
    }
}
