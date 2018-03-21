package com.szfp.scan;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.extra.presenter.BasePresenter;
import com.extra.utils.Constant;
import com.extra.utils.DataUtils;
import com.extra.utils.JsonUtil;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.TimeUtils;
import com.extra.view.activity.BaseActivity;
import com.google.zxing.Result;
import com.player.util.L;
import com.pos.device.scanner.OnScanListener;
import com.pos.device.scanner.Scanner;
import com.szfp.scan.bean.ContinueTradingModel;
import com.szfp.scan.bean.ShopCardModel;
import com.szfp.scan.bean.ShopModel;
import com.szfp.scan.bean.TradingOrderModel;
import com.szfp.scan.bean.TradingShopModel;
import com.szfp.scan.inter.OnStartScanListener;
import com.szfp.scan.print.PrintManager;
import com.szfp.scan.util.DbHelper;
import com.szfp.scan.view.DialogShopCart;
import com.szfp.scan.view.MyImageView;
import com.szfp.scan.zxing.ScanListener;
import com.szfp.scan.zxing.ScanManager;
import com.szfp.scan.zxing.decode.DecodeThread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cache;

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

    private List<TradingShopModel> tradingShopModels;
    private TradingOrderModel tradingOrderModel;


    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.scan_allcode_title);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void bindView(Bundle savedInstanceState) {

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initData();

        //启动动画
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                0.9f);
        animation.setDuration(4500);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        captureScanLine.startAnimation(animation);
        Bundle realBundle = new Bundle();
        realBundle.putBoolean(Scanner.SCANNER_CONTINUE_SCAN, isContinueScan);
        realBundle.putBoolean(Scanner.SCANNER_IS_BACK_CAMERA, isBackCamera);
        realBundle.putBoolean(Scanner.SCANNER_PLAY_BEEP, isBeep);
        realBundle.putBoolean(Scanner.SCANNER_IS_TORCH_ON, isTorchOn);
//        Observable.create((ObservableOnSubscribe<Integer>) e -> {
//            e.onNext(1);
//            e.onComplete();
//        }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
        view = Scanner.getInstance().initScanner(this, realBundle);

        if (previewLayout != null && view != null) {
            previewLayout.removeAllViews();
            previewLayout.addView(view);
            captureScanLine.setVisibility(View.VISIBLE);
        }


        isScan = true;
        Scanner.getInstance().startScan(2000, (result, data) -> {
            isScan = false;

            captureScanLine.setVisibility(View.GONE);
            try {
                if (result == 0) {
                    System.out.println("扫码成功，获得结果data：" + new String(data));
                    scanImage.setVisibility(View.VISIBLE);
                    tvScanResult.setVisibility(View.VISIBLE);
                    tvScanResult.setText("result：" + new String(data));

                    ShopModel shopModel = DbHelper.getShopModel(new String(data));
                    if (shopModel==null)return;

                    mShopCardModel.addShoppingSingle(shopModel,1);
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
        });
//        });


    }

    private Scanner scanner;

    private void initData() {
        L.d(SPUtils.getString(this,"mShopCardModel"));

        scanMode = getIntent().getIntExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
        mShopCardModel = new ShopCardModel();
        showTotalPrice();
        switch (scanMode) {
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
            shoppingCartTotalTv.setText("$ " +DataUtils.format2Decimals(String.valueOf(mShopCardModel.getShoppingTotalPrice())));
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
        tvScanResult.setText("result：" + rawResult.getText());
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
    protected void onDestroy() {
        super.onDestroy();

        if (mShopCardModel != null && mShopCardModel.getShoppingTotalPrice() > 0) {

            SPUtils.putInt(this,"num",mShopCardModel.getShoppingAccount());
            SPUtils.putString(this,"total",String.valueOf(mShopCardModel.getShoppingTotalPrice()));
           List<ContinueTradingModel>  tradingModels  = new ArrayList<>();
            List<ShopModel> models = new ArrayList<>(mShopCardModel.getShoppingSingle().keySet());
            for (ShopModel s:models) {
                ContinueTradingModel model = new ContinueTradingModel();
                model.setNum(mShopCardModel.getShoppingSingle().get(s));
                model.setShopModel(s);
                tradingModels.add(model);
            }


            SPUtils.putString(this,"mShopCardModel", JsonUtil.objectToString(tradingModels));
        }
    }

    @Override
    public void scanError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        //相机扫描出错时
        if (e.getMessage() != null && e.getMessage().startsWith("相机")) {
            capturePreview.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isScan = false;

    @SuppressLint("SetTextI18n")
    @Override
    public void startScan() {
        showTotalPrice();
//        scanManager.reScan();

        if (isScan) return;


        isScan = true;
        Bundle realBundle = new Bundle();
        realBundle.putBoolean(Scanner.SCANNER_CONTINUE_SCAN, isContinueScan);
        realBundle.putBoolean(Scanner.SCANNER_IS_BACK_CAMERA, isBackCamera);
        realBundle.putBoolean(Scanner.SCANNER_PLAY_BEEP, isBeep);
        realBundle.putBoolean(Scanner.SCANNER_IS_TORCH_ON, isTorchOn);
        view = Scanner.getInstance().initScanner(this, realBundle);

        if (previewLayout != null && view != null) {
            previewLayout.removeAllViews();
            previewLayout.addView(view);
            captureScanLine.setVisibility(View.VISIBLE);
        }
        Scanner.getInstance().startScan(2000, (result, data) -> {
            captureScanLine.setVisibility(View.GONE);
            isScan = false;
            try {
                if (result == 0) {
                    System.out.println("扫码成功，获得结果data：" + new String(data));
                    scanImage.setVisibility(View.VISIBLE);
                    tvScanResult.setVisibility(View.VISIBLE);
                    tvScanResult.setText("result：" + new String(data));
                    ShopModel shopModel = DbHelper.getShopModel(new String(data));
                    if (shopModel==null)return;

                    mShopCardModel.addShoppingSingle(shopModel,1);
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
        });
    }

    @Override
    public void startCheckOut() {
        if (mShopCardModel.getShoppingAccount() < 1)
            return;

        tradingOrderModel =new TradingOrderModel();

        tradingOrderModel.setSn(TimeUtils.generateSequenceNo());
        tradingOrderModel.setOpterator(App.userName);
        tradingOrderModel.setTotalNum(mShopCardModel.getShoppingAccount());
        tradingOrderModel.setTotalAmoumt(mShopCardModel.getShoppingTotalPrice());
        tradingOrderModel.setCreateTime(new Date());

        tradingShopModels  = new ArrayList<>();
        List<ShopModel> models = new ArrayList<>(mShopCardModel.getShoppingSingle().keySet());
        for (ShopModel s:models) {
            TradingShopModel shopModel = new TradingShopModel();
            shopModel.setSn(tradingOrderModel.getSn());
            shopModel.setSellNum(mShopCardModel.getShoppingSingle().get(s));
            shopModel.setItemNo(s.getItemNo());
            shopModel.setId(s.getId());
            shopModel.setName(s.getName());
            shopModel.setNum(s.getNum());
            shopModel.setPrice(s.getPrice());
            tradingShopModels.add(shopModel);
        }





        DbHelper.insertTrading(tradingOrderModel,tradingShopModels);
        PrintManager.getmInstance(this).printTradingSalas(tradingOrderModel,tradingShopModels);
        DbHelper.updateShopModel(new ArrayList<>(mShopCardModel.getShoppingSingle().keySet()));
        mShopCardModel.clear();
        showTotalPrice();
        SPUtils.putString(this,"mShopCardModel", "");
    }


    @OnClick({R.id.iv_light, R.id.qrcode_ic_back, R.id.shopping_cart_bottom, R.id.shopping_cart, R.id.shopping_cart_layout, R.id.btn_new, R.id.trading_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_light:
                startScan();
                break;
            case R.id.qrcode_ic_back:
                Scanner.getInstance().stopScan();
                break;
            case R.id.shopping_cart_bottom:
                showCart(shoppingCartLayout);
                break;
            case R.id.shopping_cart:showCart(shoppingCartLayout);
                break;
            case R.id.shopping_cart_layout:showCart(shoppingCartLayout);
                break;
            case R.id.btn_new:
                startCheckOut();
                break;
            case R.id.trading_layout:
                break;
        }
    }




}
