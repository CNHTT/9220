package com.odd.sportal.service;

import android.annotation.SuppressLint;
import android.app.IntentService;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.extra.utils.SPUtils;
import com.extra.utils.ToastUtils;
import com.odd.sportal.ContextValue;
import com.odd.sportal.R;
import com.odd.sportal.handler.EventHandler;
import com.odd.sportal.handler.ForecastsHandler;
import com.odd.sportal.handler.GameHandler;
import com.odd.sportal.inter.CallBackSuccess;
import com.odd.sportal.utils.BaseHandler;
import com.odd.sportal.utils.DbHelper;
import com.player.util.L;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 戴尔 on 2017/12/13.
 */

@SuppressLint("Registered")
public class ParseXmlService extends IntentService implements CallBackSuccess {


    private String TAG = "ParseXmlService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * name Used to name the worker thread, important only for debugging.
     */
    public ParseXmlService() {
        super("ParseXmlService");
    }

    private boolean b1=false;
    private boolean b2=false;
    private boolean b3=false;

    private NotificationCompat.Builder builder;
    private NotificationManager manager;
    private int _notificationID = 1024;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        L.d("onHandleIntent");
        try {
            initNotification();
            manager.notify(_notificationID,builder.build());
            SPUtils.putBoolean(this, ContextValue.ISLOADING,true);
            DbHelper.deleteGameAll();
            DbHelper.deleteEventAll();
            DbHelper.deleteForecastsAll();
            Observable.create((ObservableOnSubscribe<Integer>) e -> {
                e.onNext(1);
                e.onComplete();
            }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                BaseHandler handler2 = new EventHandler(this);
                handler2.parse("BetEvents.xml");
            });

            Observable.create((ObservableOnSubscribe<Integer>) e -> {
                e.onNext(1);
                e.onComplete();
            }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                BaseHandler handler1 = new GameHandler(this);
                handler1.parse("BetGames.xml");
            });

            Observable.create((ObservableOnSubscribe<Integer>) e -> {
                e.onNext(1);
                e.onComplete();
            }).subscribeOn(Schedulers.newThread()).subscribe(integer -> {
                BaseHandler handler = new ForecastsHandler(this);
                handler.parse("BetForecasts.xml");
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void initNotification() {

        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Data loading, please do not kill this app").setContentText("Loading……");//图标、标题、内容这三个设置是必须要有的。
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void eventEnd() {
        builder.setContentText("Event Loading completed");
        manager.notify(_notificationID++,builder.build());
        b1=true;
        showLoadSuccess();
    }

    @Override
    public void gameEnd() {
        builder.setContentText("Games Loading completed");
        manager.notify(_notificationID++,builder.build());
        b2=true;
        showLoadSuccess();
    }

    @Override
    public void forecastsEnd() {
        builder.setContentText("Forecasts Loading completed");
        manager.notify(_notificationID++,builder.build());

        b3=true;
        showLoadSuccess();
    }

    private void showLoadSuccess() {
        if (b3&&b1&&b2){
            L.d(TAG+ "showLoadSuccess");
            manager.cancel(_notificationID);
            SPUtils.putBoolean(this, ContextValue.ISLOADING,false);
            SPUtils.putBoolean(this, ContextValue.ISLOADDATA,true);
            ToastUtils.showToast("Data loading completed");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d(TAG+ "onDestroy");
    }
}
