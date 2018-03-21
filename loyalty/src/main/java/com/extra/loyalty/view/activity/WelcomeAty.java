package com.extra.loyalty.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.extra.loyalty.ConstantValue;
import com.extra.loyalty.R;
import com.extra.loyalty.model.entities.AccountInfo;
import com.extra.utils.JsonUtil;
import com.extra.utils.SPUtils;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class WelcomeAty extends AppCompatActivity {

    Disposable dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_welcome_aty);
        ButterKnife.bind(this);


        Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        dis=d;
                    }

                    @Override
                    public void onNext(Long value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        //完成时调用
                        toLogin();

                    }
                });
    }

    private void toLogin() {

        if (SPUtils.getBoolean(this, ConstantValue.ISLOGIN)){
            ConstantValue.ACCOUNT_ID_VALUE = SPUtils.getString(this, ConstantValue.ACCOUNT_ID_VALUE);
            ConstantValue.USER_ID_VALUE  = SPUtils.getString(this, ConstantValue.USER_ID_VALUE);
            ConstantValue.USER_API_KEY_VALUE  = SPUtils.getString(this, ConstantValue.USER_API_KEY_VALUE);
            Intent in = new Intent();
            in.setClass(this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstantValue.ACCOUNT_INFO, (Serializable) JsonUtil.stringToObject(SPUtils.getString(this,ConstantValue.ACC), AccountInfo.class));
            in.putExtras( bundle);
            finish();
            startActivity(in);
        }else {
            finish();startActivity(new Intent(this,LoginActivity.class));
        }


    }

    /**
     * 取消订阅
     */
    public  void cancel(){
        if(dis!=null&&!dis.isDisposed()){
            dis.dispose();
        }
    }
}