package com.extra.saas.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.extra.adapter.BaseListAdapter;
import com.extra.saas.R;
import com.extra.saas.SubjectImageView;
import com.extra.saas.model.SmetaBean.Photo;
import com.extra.utils.ContextUtil;

import java.util.List;

/**
 * Created by 戴尔 on 2017/11/17.
 */

public class PhotoAdapter extends BaseListAdapter<Photo> {

    public PhotoAdapter(Context mContext, List<Photo> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = ContextUtil.inflate(mContext, R.layout.video_image);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(getItem(position).getUrl()).into(viewHolder.tvContent);
        return convertView;
    }

    private class ViewHolder {
        SubjectImageView tvContent;

        public ViewHolder(View convertView) {
            tvContent = (SubjectImageView) convertView.findViewById(R.id.image);
        }

    }
}