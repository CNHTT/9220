package com.extra.loyalty.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.adapter.BaseRecycleAdapter;
import com.extra.loyalty.R;
import com.extra.loyalty.inter.OnLongCustomerListener;
import com.extra.loyalty.model.entities.CustomerBean;
import com.extra.utils.DataUtils;

import java.util.List;

/**
 * Created by 戴尔 on 2018/1/9.
 */

public class CustomersAdapter  extends BaseRecycleAdapter<CustomerBean> {

    private OnLongCustomerListener listener;


    public CustomersAdapter(List<CustomerBean> datas,OnLongCustomerListener listener) {
        super(datas);
        this.listener = listener;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bindData(BaseViewHolder holder, int position) {
        CustomerBean model = datas . get(position);
        ((TextView)holder.getView(R.id.v_card_number)).setText(DataUtils.isNullString(model.getCard_number())?"No Card":model.getCard_number());
        ((TextView)holder.getView(R.id.v_user_name)).setText(model.getFirst_name()+" "+ model.getLast_name());
        ((TextView)holder.getView(R.id.v_user_phone)).setText(DataUtils.isNullString(model.getPhone())?"---":model.getPhone());
        ((TextView)holder.getView(R.id.v_user_email)).setText(DataUtils.isNullString(model.getEmail())?"---":model.getEmail());
        holder.getView(R.id.ll_view).setOnLongClickListener(v -> {
            listener.OnLongCustomer(model);
            return false;
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_customer_item;
    }

    public void remove(CustomerBean bean) {
        datas.remove(bean);notifyDataSetChanged();
    }
}
