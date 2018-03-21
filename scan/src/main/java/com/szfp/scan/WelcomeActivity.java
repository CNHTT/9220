package com.szfp.scan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.extra.utils.SPUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView imageView;
    Disposable dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_weclome); ButterKnife.bind(this);
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
        if (SPUtils.getBoolean(this,"L")){
            App.userName = SPUtils.getString(this,"N");
            App.isTurn = SPUtils.getBoolean(this,"O");


            finish();
            startActivity(new Intent(this,MainActivity.class));
        }else {

            finish();
            startActivity(new Intent(this,LoginActivity.class));
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
