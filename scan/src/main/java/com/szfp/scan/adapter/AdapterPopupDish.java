package com.szfp.scan.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szfp.scan.R;
import com.szfp.scan.bean.ShopCardModel;
import com.szfp.scan.bean.ShopModel;
import com.szfp.scan.inter.ShopCartInterface;

import java.util.ArrayList;

/**
 * Created by 戴尔 on 2017/12/21.
 */

public class AdapterPopupDish extends RecyclerView.Adapter{
    private static String TAG = "PopupDishAdapter";
    private ShopCardModel mShopCardModel;
    private Context context;
    private int itemCount;
    private ArrayList<ShopModel> mModelDishList;
    private ShopCartInterface shopCartImp;

    public AdapterPopupDish(ShopCardModel mShopCardModel, Context context) {
        this.mShopCardModel = mShopCardModel;
        this.context = context;
        this.itemCount = mShopCardModel.getDishAccount();
        this.mModelDishList = new ArrayList<>();
        mModelDishList.addAll(mShopCardModel.getShoppingSingle().keySet());

    }
    public void setShopCartInterface(ShopCartInterface shopCartImp) {
        this.shopCartImp = shopCartImp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_dish_item1, parent, false);
        DishViewHolder viewHolder = new DishViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DishViewHolder dishholder = (DishViewHolder)holder;
         final ShopModel modelDish = getDishByPosition(position);
        if(modelDish !=null) {
            dishholder.right_dish_name_tv.setText(modelDish.getName());
            dishholder.right_dish_price_tv.setText(modelDish.getPrice() + "");
            int num = mShopCardModel.getShoppingSingle().get(modelDish);
            dishholder.right_dish_account_tv.setText(num+"");
            dishholder.right_dish_num.setText(modelDish.getNum()+"");

            dishholder.right_dish_add_iv.setOnClickListener(view -> {
                if(mShopCardModel.addShoppingSingle(modelDish,2)) {
                    notifyItemChanged(position);
                    if(shopCartImp!=null)
                        shopCartImp.add(view,position);
                }
            });

            dishholder.right_dish_remove_iv.setOnClickListener(view -> {
                if(mShopCardModel.subShoppingSingle(modelDish)){
                    mModelDishList.clear();
                    mModelDishList.addAll(mShopCardModel.getShoppingSingle().keySet());
                    itemCount = mShopCardModel.getDishAccount();
                    notifyDataSetChanged();
                    if(shopCartImp!=null)
                        shopCartImp.remove(view,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.itemCount;
    }

    public ShopModel getDishByPosition(int position){
        return mModelDishList.get(position);
    }

    private class DishViewHolder extends RecyclerView.ViewHolder{
        private TextView right_dish_name_tv;
        private TextView right_dish_price_tv;
        private LinearLayout right_dish_layout;
        private ImageView right_dish_remove_iv;
        private ImageView right_dish_add_iv;
        private TextView right_dish_num;
        private TextView right_dish_account_tv;

        public DishViewHolder(View itemView) {
            super(itemView);
            right_dish_num = (TextView)itemView.findViewById(R.id.right_dish_num);
            right_dish_name_tv = (TextView)itemView.findViewById(R.id.right_dish_name);
            right_dish_price_tv = (TextView)itemView.findViewById(R.id.right_dish_price);
            right_dish_layout = (LinearLayout)itemView.findViewById(R.id.right_dish_item);
            right_dish_remove_iv = (ImageView)itemView.findViewById(R.id.right_dish_remove);
            right_dish_add_iv = (ImageView)itemView.findViewById(R.id.right_dish_add);
            right_dish_account_tv = (TextView) itemView.findViewById(R.id.right_dish_account);
        }

    }
}
