package com.extra.saas;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.extra.retrofit.AppUrl;
import com.extra.retrofit.HttpBuilder;
import com.extra.retrofit.HttpUtil;
import com.extra.saas.result.Result;
import com.extra.saas.result.ResultLogin;
import com.extra.saas.util.JsonUtil;
import com.extra.utils.AlertAnimateUtil;
import com.extra.utils.AppManager;
import com.extra.utils.KeyboardUtils;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.player.util.L;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.extra.utils.DataUtils.isEmpty;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.iv_clean_phone)
    ImageView ivCleanPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.clean_password)
    ImageView cleanPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView ivShowPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private String name;
    private String pass;


    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private int height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyHeight = screenHeight / 3+10;//弹起高度为屏幕高度的1/3
    }

    private void initView() {

        Glide.with(this).load(R.mipmap.logo).apply(RequestOptions.circleCropTransform()).into(logo);

        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && ivCleanPhone.getVisibility() == View.GONE) {
                    ivCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    ivCleanPhone.setVisibility(View.GONE);
                }
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && cleanPassword.getVisibility() == View.GONE) {
                    cleanPassword.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    cleanPassword.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    ToastUtils.error("Please enter a number or letter");
                    s.delete(temp.length() - 1, temp.length());
                    etPassword.setSelection(s.length());
                }
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        scrollView.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
                    int dist = content.getBottom() - bottom;
                    if (dist > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(content, "translationY", 0.0f, -dist);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        AlertAnimateUtil.zoomIn(logo, 0.6f, dist);
                    }

                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
                    if ((content.getBottom() - oldBottom) > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(content, "translationY", content.getTranslationY(), 0);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                        AlertAnimateUtil.zoomOut(logo, 0.6f);
                    }
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtil.cancel(this);
    }

    @OnClick({R.id.logo, R.id.iv_clean_phone, R.id.clean_password, R.id.iv_show_pwd, R.id.btn_login,R.id.btn_register,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logo:
                break;
            case R.id.iv_clean_phone:
                etMobile.setText("");
                break;
            case R.id.clean_password:
                etPassword.setText("");
                break;
            case R.id.iv_show_pwd:
                if (etPassword.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ivShowPwd.setImageResource(R.mipmap.pass_visuable);
                } else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ivShowPwd.setImageResource(R.mipmap.pass_gone);
                }
                String pwd = etPassword.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    etPassword.setSelection(pwd.length());
                break;
            case R.id.btn_login:
                KeyboardUtils.hideSoftInput(this);

                name = etMobile.getText().toString();
                pass  =etPassword .getText().toString();
                if (isEmpty(name)&&isEmpty(pass)){
                    showDialogToast(getResources().getString(R.string.please_input));
                }else {
                    login();
                }

                break;
            case R.id.btn_register:
                KeyboardUtils.hideSoftInput(this);
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }

    private void login() {
        showProgressDialog(R.string.loading);
        new HttpBuilder(AppUrl.login)
                .params("username",name)
                .params("password",pass)
                .tag(this)
                .success(s ->{
                    cancleProgressDialog();
                    L.d(s);
                    try {
                        ResultLogin result = (ResultLogin) JsonUtil.stringToObject(s,ResultLogin.class);
                        if (result.getCode()==1){
                            SPUtils.putString(this,AppUrl.Voucher,result.getData().getVoucher());
                            //跳转主页
                            ToastUtils.showToast(result.getMsg());
                            AppManager.getAppManager().finishAllActivityAndExit(this);
                            Intent intent =new Intent(this,MainActivity.class);
                            intent.putExtra("login",1);
                            startActivity(intent);

                        }else {
                            showDialogToast(result.getMsg());
                        }
                    }catch (Exception e){
                        Result result = (Result) JsonUtil.stringToObject(s,Result.class);
                        showDialogToast(result.getMsg());
                    }

                })
                .error( e ->{
                    cancleProgressDialog();
                    showDialogToast(getResources().getString(R.string.check_net));
                })
                .post();

    }
}
