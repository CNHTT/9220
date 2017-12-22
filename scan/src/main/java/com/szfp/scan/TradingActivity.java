package com.szfp.scan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.extra.presenter.BasePresenter;
import com.extra.utils.Constant;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.google.zxing.Result;
import com.pos.device.scanner.OnScanListener;
import com.pos.device.scanner.Scanner;
import com.szfp.scan.bean.ShopCardModel;
import com.szfp.scan.bean.ShopModel;
import com.szfp.scan.inter.OnStartScanListener;
import com.szfp.scan.view.DialogShopCart;
import com.szfp.scan.view.MyImageView;
import com.szfp.scan.zxing.ScanListener;
import com.szfp.scan.zxing.ScanManager;
import com.szfp.scan.zxing.decode.DecodeThread;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class TradingActivity extends BaseActivity implements DialogShopCart.ShopCartDialogImp, ScanListener, OnStartScanListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shopping_cart_total_tv)
    TextView shoppingCartTotalTv;
    @BindView(R.id.shopping_cart_bottom)
    LinearLayout shoppingCartBottom;
    @BindView(R.id.shopping_cart)
    ImageView shoppingCart;
    @BindView(R.id.shopping_cart_layout)
    FrameLayout shoppingCartLayout;
    @BindView(R.id.shopping_cart_total_num)
    TextView shoppingCartTotalNum;
    @BindView(R.id.trading_layout)
    View tradingLayout;
    @BindView(R.id.capture_preview)
    SurfaceView capturePreview;
    @BindView(R.id.tv_scan_result)
    TextView tvScanResult;
    @BindView(R.id.scan_hint)
    TextView scanHint;
    @BindView(R.id.iv_light)
    TextView ivLight;
    @BindView(R.id.qrcode_ic_back)
    TextView qrcodeIcBack;
    @BindView(R.id.qrcode_g_gallery)
    TextView qrcodeGGallery;
    @BindView(R.id.bottom_mask)
    RelativeLayout bottomMask;
    @BindView(R.id.left_mask)
    ImageView leftMask;
    @BindView(R.id.right_mask)
    ImageView rightMask;
    @BindView(R.id.capture_scan_line)
    ImageView captureScanLine;
    @BindView(R.id.scan_image)
    MyImageView scanImage;
    @BindView(R.id.capture_crop_view)
    View captureCropView;
    @BindView(R.id.previewLayout)
    RelativeLayout previewLayout;
    View view;


    private ShopCardModel mShopCardModel;

    ScanManager scanManager;
    final int PHOTOREQUESTCODE = 1111;
    private int scanMode;//扫描模型（条形，二维码，全部）
    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void showCart(View view) {
        if (mShopCardModel != null && mShopCardModel.getShoppingAccount() > 0) {
            DialogShopCart dialog = new DialogShopCart(this, mShopCardModel, R.style.cartdialog);
            Window window = dialog.getWindow();
            dialog.setShopCartDialogImp(this);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.setStartScan(this);
            dialog.show();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            params.dimAmount = 0.5f;
            window.setAttributes(params);
        }
    }
    private boolean isContinueScan = false;
    private boolean isBackCamera = true;
    private boolean isBeep = true;
    private boolean isTorchOn = false;
    @Override
    public void bindView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.trading);
        initData();
        Bundle realBundle = new Bundle();
        realBundle.putBoolean(Scanner.SCANNER_CONTINUE_SCAN, isContinueScan);
        realBundle.putBoolean(Scanner.SCANNER_IS_BACK_CAMERA, isBackCamera);
        realBundle.putBoolean(Scanner.SCANNER_PLAY_BEEP,isBeep);
        realBundle.putBoolean(Scanner.SCANNER_IS_TORCH_ON,isTorchOn);
//        Observable.create((ObservableOnSubscribe<Integer>) e -> {
//            e.onNext(1);
//            e.onComplete();
//        }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
           view=  Scanner.getInstance().initScanner(this,realBundle);

            if(previewLayout != null && view != null) {
                previewLayout.removeAllViews();
                previewLayout.addView(view);
            }


        isScan=true;
            Scanner.getInstance().startScan(2000, new OnScanListener() {
                @Override
                public void onScanResult(int result, byte[] data) {
                    isScan=false;
                    try {
                        if (result == 0) {
                            System.out.println("扫码成功，获得结果data：" + new String(data));
                            scanImage.setVisibility(View.VISIBLE);
                            tvScanResult.setVisibility(View.VISIBLE);
                            tvScanResult.setText("result："+ new String(data));



                            mShopCardModel.addShoppingSingle(new ShopModel( new String(data), "12312313", 10, 11.01));
                            showCart(shoppingCartLayout);
                            showTotalPrice();
                        } else if (result == -1) {
                            System.out.println("用户退出扫码");
                        } else if (result == -3) {
                            System.out.println("扫码超时");
                        } else {
                            System.out.println("其他错误");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
//        });


    }

    private Scanner scanner;
    private void initData() {

        scanMode=getIntent().getIntExtra(Constant.REQUEST_SCAN_MODE,Constant.REQUEST_SCAN_MODE_ALL_MODE);
        mShopCardModel = new ShopCardModel();
        mShopCardModel.addShoppingSingle(new ShopModel("A", "12312313", 10, 11.01));
        showTotalPrice();
        switch (scanMode){
            case DecodeThread.BARCODE_MODE:
                toolbar.setTitle(R.string.scan_barcode_title);
                scanHint.setText(R.string.scan_barcode_hint);
                break;
            case DecodeThread.QRCODE_MODE:
                toolbar.setTitle(R.string.scan_qrcode_title);
                scanHint.setText(R.string.scan_qrcode_hint);
                break;
            case DecodeThread.ALL_MODE:
                toolbar.setTitle(R.string.scan_allcode_title);
                scanHint.setText(R.string.scan_allcode_hint);
                break;
        }



//        scanManager = new ScanManager(this, capturePreview, tradingLayout, captureCropView,captureScanLine, scanMode,this);
    }

    @SuppressLint("SetTextI18n")
    private void showTotalPrice() {
        if (mShopCardModel != null && mShopCardModel.getShoppingTotalPrice() > 0) {
            shoppingCartTotalTv.setVisibility(View.VISIBLE);
            shoppingCartTotalTv.setText("¥ " + mShopCardModel.getShoppingTotalPrice());
            shoppingCartTotalNum.setVisibility(View.VISIBLE);
            shoppingCartTotalNum.setText("" + mShopCardModel.getShoppingAccount());

        } else {
            shoppingCartTotalTv.setVisibility(View.GONE);
            shoppingCartTotalNum.setVisibility(View.GONE);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_trading;
    }

    @Override
    public void dialogDismiss() {
        showTotalPrice();
    }



    @OnClick(R.id.shopping_cart_layout)
    public void onViewClicked() {
        showCart(shoppingCartLayout);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void scanResult(Result rawResult, Bundle bundle) {

        if (!scanManager.isScanning()) { //如果当前不是在扫描状态
            //设置再次扫描按钮出现
//            scanImage.setVisibility(View.VISIBLE);
//            Bitmap barcode = null;
//            byte[] compressedBitmap = bundle.getByteArray(DecodeThread.BARCODE_BITMAP);
//            if (compressedBitmap != null) {
//                barcode = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
//                barcode = barcode.copy(Bitmap.Config.ARGB_8888, true);
//            }
//            scanImage.setImageBitmap(barcode);
        }
        scanImage.setVisibility(View.VISIBLE);
        tvScanResult.setVisibility(View.VISIBLE);
        tvScanResult.setText("result："+rawResult.getText());



        mShopCardModel.addShoppingSingle(new ShopModel(rawResult.getText(), "12312313", 10, 11.01));
        showCart(shoppingCartLayout);
        showTotalPrice();

    }
    @Override
    public void onResume() {
        super.onResume();
//        scanManager.onResume();
        scanImage.setVisibility(View.GONE);

    }

    @Override
    public void onPause() {
        super.onPause();
//        scanManager.onPause();
    }
    @Override
    public void scanError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        //相机扫描出错时
        if(e.getMessage()!=null&&e.getMessage().startsWith("相机")){
            capturePreview .setVisibility(View.INVISIBLE);
        }
    }

    private boolean isScan =false;
    @Override
    public void startScan() { showTotalPrice();
//        scanManager.reScan();

        if (isScan)return;



        isScan=true;
        Bundle realBundle = new Bundle();
        realBundle.putBoolean(Scanner.SCANNER_CONTINUE_SCAN, isContinueScan);
        realBundle.putBoolean(Scanner.SCANNER_IS_BACK_CAMERA, isBackCamera);
        realBundle.putBoolean(Scanner.SCANNER_PLAY_BEEP,isBeep);
        realBundle.putBoolean(Scanner.SCANNER_IS_TORCH_ON,isTorchOn);
        view=  Scanner.getInstance().initScanner(this,realBundle);

        if(previewLayout != null && view != null) {
            previewLayout.removeAllViews();
            previewLayout.addView(view);
        }
        Scanner.getInstance().startScan(2000, new OnScanListener() {
            @Override
            public void onScanResult(int result, byte[] data) {
                isScan =false;
                try {
                    if (result == 0) {
                        System.out.println("扫码成功，获得结果data：" + new String(data));
                        scanImage.setVisibility(View.VISIBLE);
                        tvScanResult.setVisibility(View.VISIBLE);
                        tvScanResult.setText("result："+ new String(data));



                        mShopCardModel.addShoppingSingle(new ShopModel( new String(data), "12312313", 10, 11.01));
                        showCart(shoppingCartLayout);
                        showTotalPrice();
                    } else if (result == -1) {
                        System.out.println("用户退出扫码");
                    } else if (result == -3) {
                        System.out.println("扫码超时");
                    } else {
                        System.out.println("其他错误");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
