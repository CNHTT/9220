package com.extra.loyalty.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.adapter.BaseRecycleAdapter;
import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.inter.OnClickUserModelListener;
import com.extra.loyalty.model.entities.UserBean;

import java.util.List;

/**
 * Created by 戴尔 on 2017/12/29.
 */


public class UserListAdapter  extends BaseRecycleAdapter<UserBean>{

    private OnClickUserModelListener listener;

    public UserListAdapter(List<UserBean> datas, OnClickUserModelListener listener) {
        super(datas);
        this.listener = listener;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bindData(BaseViewHolder holder, int position) {
        UserBean model = datas.get(position);
        ((TextView)holder.getView(R.id.v_user_id)).setText("USER ID: "+model.getUser_id());
        ((TextView)holder.getView(R.id.v_user_name)).setText("USER NAME: "+model.getUser_first_name()
                +" "+model.getUser_last_name());
        ((TextView)holder.getView(R.id.v_user_role)).setText(ConstantValue.getUserRole(model.getUser_role()));


        //设置点击事件  修改操作人员的个人信息
        ((LinearLayout)holder.getView(R.id.ll_view)).setOnClickListener(v -> {
            listener.OnClickUserModel(model);
        });
        //1+1 =1

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_user_item;
    }
}
