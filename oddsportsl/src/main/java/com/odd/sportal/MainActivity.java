package com.odd.sportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.extra.presenter.BasePresenter;
import com.extra.utils.SPUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.odd.sportal.handler.EventHandler;
import com.odd.sportal.handler.ForecastsHandler;
import com.odd.sportal.handler.GameHandler;
import com.odd.sportal.model.GameModel;
import com.odd.sportal.service.ParseXmlService;
import com.odd.sportal.utils.BaseHandler;
import com.odd.sportal.utils.DbHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_select_program)
    TextView tvSelectProgram;
    @BindView(R.id.tv_reload)
    TextView tvReload;
    @BindView(R.id.tv_new_bet)
    TextView tvNewBet;
    @BindView(R.id.tv_pending_bets)
    TextView tvPendingBets;
    @BindView(R.id.tv_win_search)
    TextView tvWinSearch;
    @BindView(R.id.tv_cancel_main)
    TextView tvCancelMain;
    @BindView(R.id.tv_my_account)
    TextView tvMyAccount;
    @BindView(R.id.tv_open_close)
    TextView tvOpenClose;
    @BindView(R.id.tv_utilities)
    TextView tvUtilities;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.tv_selections)
    TextView tvSelections;
    @BindView(R.id.tv_instructions)
    TextView tvInstructions;
    @BindView(R.id.tv_last_live_results)
    TextView tvLastLiveResults;
    @BindView(R.id.tv_last_coupons_saved)
    TextView tvLastCouponsSaved;
    @BindView(R.id.tv_filters)
    TextView tvFilters;




    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {
        ButterKnife.bind(this);

        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.main);
    }
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

        initData();
    }

    private void initData() {
        boolean isloading = SPUtils.getBoolean(this,ContextValue.ISLOADING);
        if (!isloading)
        if (!SPUtils.getBoolean(this,ContextValue.ISLOADDATA)){
//            loadData();
            ToastUtils.showToast("Data loading, please do not kill this app");
            Intent intent=new Intent(this,ParseXmlService.class);
            startService(intent);
        }



    }

    private void loadData() {
//        DbHelper.deleteGameAll();
//        DbHelper.deleteEventAll();
//        DbHelper.deleteForecastsAll();
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.newThread()).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                        BaseHandler handler2 = new EventHandler();
//                        handler2.parse("BetEvents.xml");
//            }
//        });
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.newThread()).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//               BaseHandler handler1 = new GameHandler();
//                        handler1.parse("BetGames.xml");
//            }
//        });
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.newThread()).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                BaseHandler handler = new ForecastsHandler();
//                        handler.parse("BetForecasts.xml");
//            }
//        });




    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }
    @OnClick({R.id.tv_select_program, R.id.tv_reload, R.id.tv_new_bet, R.id.tv_pending_bets, R.id.tv_win_search, R.id.tv_cancel_main, R.id.tv_my_account, R.id.tv_open_close, R.id.tv_utilities, R.id.tv_logout, R.id.tv_selections, R.id.tv_instructions, R.id.tv_last_live_results, R.id.tv_last_coupons_saved, R.id.tv_filters})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_program:
                startActivity(new Intent(this,SelectProgramActivity.class));
                break;
            case R.id.tv_reload:
                break;
            case R.id.tv_new_bet:
                startActivity(new Intent(this, InsertNewBetActivity.class));
                break;
            case R.id.tv_pending_bets:
                break;
            case R.id.tv_win_search:
                break;
            case R.id.tv_cancel_main:
                break;
            case R.id.tv_my_account:
                break;
            case R.id.tv_open_close:
                break;
            case R.id.tv_utilities:
                break;
            case R.id.tv_logout:
                break;
            case R.id.tv_selections:
                break;
            case R.id.tv_instructions:
                break;
            case R.id.tv_last_live_results:
                break;
            case R.id.tv_last_coupons_saved:
                break;
            case R.id.tv_filters:
                break;
        }
    }


}
