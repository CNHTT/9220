package com.szfp.scan.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szfp.scan.R;
import com.szfp.scan.adapter.AdapterPopupDish;
import com.szfp.scan.bean.ShopCardModel;
import com.szfp.scan.inter.OnStartScanListener;
import com.szfp.scan.inter.ShopCartInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 戴尔 on 2017/12/21.
 */

public class DialogShopCart extends Dialog implements View.OnClickListener, ShopCartInterface {


    @BindView(R.id.clear_layout)
    LinearLayout clearLayout;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.linearlayout)
    LinearLayout linearlayout;
    @BindView(R.id.shopping_cart_total_tv)
    TextView totalPriceTextView;
    @BindView(R.id.shopping_cart_bottom)
    LinearLayout shoppingCartBottom;
    @BindView(R.id.shopping_cart)
    ImageView shoppingCart;
    @BindView(R.id.shopping_cart_layout)
    FrameLayout shoppingCartLayout;
    @BindView(R.id.lineview)
    TextView lineview;
    @BindView(R.id.shopping_cart_total_num)
    TextView totalPriceNumTextView;
    private ShopCardModel shopCardModel;
    private AdapterPopupDish dishAdapter;
    private ShopCartDialogImp shopCartDialogImp;

    private OnStartScanListener startScan;

    public void setStartScan(OnStartScanListener startScan) {
        this.startScan = startScan;
    }

    public DialogShopCart(Context context, ShopCardModel shopCardModel, int themeResId) {
        super(context, themeResId);
        this.shopCardModel = shopCardModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_popupview);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dishAdapter = new AdapterPopupDish(shopCardModel,getContext() );
        recyclerView.setAdapter(dishAdapter);
        dishAdapter.setShopCartInterface(this);
        showTotalPrice();
    }

    @SuppressLint("SetTextI18n")
    private void showTotalPrice() {
        if(shopCardModel !=null && shopCardModel.getShoppingTotalPrice()>0){
            totalPriceTextView.setVisibility(View.VISIBLE);
            totalPriceTextView.setText("$ "+ shopCardModel.getShoppingTotalPrice());
            totalPriceNumTextView.setVisibility(View.VISIBLE);
            totalPriceNumTextView.setText(""+ shopCardModel.getShoppingAccount());

        }else {
            totalPriceTextView.setVisibility(View.GONE);
            totalPriceNumTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void add(View view, int postion) {
        showTotalPrice();
    }

    @Override
    public void remove(View view, int postion) {
        showTotalPrice();
        if(shopCardModel.getShoppingAccount()==0){
            this.dismiss();
        }
    }
    @Override
    public void show() {
        super.show();
        animationShow(500);
    }

    @Override
    public void dismiss() {animationHide(500);
    }

    @OnClick({R.id.clear_layout, R.id.shopping_cart_total_tv, R.id.btn_new, R.id.shopping_cart_layout, R.id.shopping_cart_total_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_layout:
                dismiss();
                if (startScan!=null)startScan.startScan();
                break;
            case R.id.shopping_cart_total_tv:
                break;
            case R.id.btn_new:
                dismiss();
                startScan.startCheckOut();
                break;
            case R.id.shopping_cart_layout:
                this.dismiss();
                break;
            case R.id.shopping_cart_total_num:
                break;
        }
    }



    public interface ShopCartDialogImp {
        public void dialogDismiss();
    }

    @Override
    public void onClick(View v) {

    }
    public ShopCartDialogImp getShopCartDialogImp() {
        return shopCartDialogImp;
    }

    public void setShopCartDialogImp(ShopCartDialogImp shopCartDialogImp) {
        this.shopCartDialogImp = shopCartDialogImp;
    }



    private void animationShow(int mDuration) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(linearlayout, "translationY",1000, 0).setDuration(mDuration)
        );
        animatorSet.start();
    }

    private void animationHide(int mDuration) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(linearlayout, "translationY",0,1000).setDuration(mDuration)
        );
        animatorSet.start();

        if(shopCartDialogImp!=null){
            shopCartDialogImp.dialogDismiss();
        }

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                DialogShopCart.super.dismiss();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    public void clear(){
        shopCardModel.clear();
        showTotalPrice();
        if(shopCardModel.getShoppingAccount()==0){
            this.dismiss();
        }
    }
}
