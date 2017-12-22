package com.odd.sportal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.utils.LogUtils;
import com.extra.presenter.BasePresenter;
import com.extra.utils.ContextUtil;
import com.extra.utils.DataUtils;
import com.extra.utils.StatusBarUtil;
import com.extra.utils.TextUtils;
import com.extra.utils.ToastUtils;
import com.extra.view.activity.BaseActivity;
import com.extra.widget.RunTextView;
import com.extra.widget.dialog.BaseDialog;
import com.extra.widget.listview.ListViewForScrollView;
import com.odd.sportal.adapter.BetAdapter;
import com.odd.sportal.adapter.EventLiveModelAdapter;
import com.odd.sportal.adapter.EventModelAdapter;
import com.odd.sportal.adapter.ForecastsLiveRecycleViewAdapter;
import com.odd.sportal.adapter.ForecastsRecycleViewAdapter;
import com.odd.sportal.adapter.GameLiveRecycleViewAdapter;
import com.odd.sportal.adapter.GameRecycleViewAdapter;
import com.odd.sportal.inter.OnBetAdapterListener;
import com.odd.sportal.inter.OnEventAdapterListener;
import com.odd.sportal.inter.OnEventLiveAdapterListener;
import com.odd.sportal.inter.OnLoadMoreEventListener;
import com.odd.sportal.model.BetBean;
import com.odd.sportal.model.BetForecastsLiveModel;
import com.odd.sportal.model.BetForecastsModel;
import com.odd.sportal.model.EventLiveModel;
import com.odd.sportal.model.EventModel;
import com.odd.sportal.model.GameLiveModel;
import com.odd.sportal.model.GameModel;
import com.odd.sportal.utils.DbHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertNewBetActivity extends BaseActivity implements  OnEventAdapterListener, OnEventLiveAdapterListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_sn)
    EditText etSn;
    @BindView(R.id.et_team)
    EditText etTeam;
    @BindView(R.id.bt_team_search)
    ImageButton btTeamSearch;
    @BindView(R.id.tv_team_name)
    RunTextView tvTeamName;
    @BindView(R.id.ck_live_betting)
    CheckBox ckLiveBetting;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_sport)
    TextView tvSport;
    @BindView(R.id.tv_home_team)
    TextView tvHomeTeam;
    @BindView(R.id.tv_awa_team)
    TextView tvAwaTeam;
    @BindView(R.id.tv_competition)
    TextView tvCompetition;
    @BindView(R.id.lv_bet)
    ListViewForScrollView lvBet;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.et_bet_type)
    EditText etBetType;
    @BindView(R.id.bt_bet_type_search)
    ImageButton btBetTypeSearch;
    @BindView(R.id.tv_bet_type_name)
    TextView tvBetTypeName;
    @BindView(R.id.bt_clear_bet_show)
    Button btClearBetShow;
    @BindView(R.id.bt_more)
    Button btMore;
    @BindView(R.id.custom_view)
    XRefreshView customView;

    boolean isLive =false;

    @Override
    protected Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    protected void initWindow() {

        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        toolbar.setTitle(R.string.insert_new_bet);

    }

    @OnClick({R.id.bt_team_search, R.id.tv_date, R.id.tv_sport, R.id.tv_home_team, R.id.tv_awa_team, R.id.tv_competition, R.id.btn_cancel, R.id.bt_bet_type_search, R.id.bt_clear_bet_show, R.id.bt_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_team_search:
                loadTeamSearch();
                break;
            case R.id.tv_date:
                break;
            case R.id.tv_sport:
                break;
            case R.id.tv_home_team:
                break;
            case R.id.tv_awa_team:
                break;
            case R.id.tv_competition:
                break;
            case R.id.btn_cancel:
                showCancel();
                break;
            case R.id.bt_bet_type_search:
                break;
            case R.id.bt_clear_bet_show:
                break;
            case R.id.bt_more:
                break;
        }
    }

    private void showCancel() {
        etSn.setText("");
        etTeam.setText("");
        eventTeamLike="";
        eventOffset=1;
        if (isLive){

            eventLiveModels  = DbHelper.loadEventLiveList(0);
            eventLiveModelAdapter.updateItems(eventLiveModels);
        }else {
            eventModelList  = DbHelper.loadEventList(0);
            eventModelAdapter.updateItems(eventModelList);
        }
    }


    private String eventId;
    private String eventTeamLike;
    private void loadTeamSearch() {
        eventId  = etSn.getText().toString();
        eventTeamLike = etTeam.getText().toString();

        if (DataUtils.isNullString(eventId)&&DataUtils.isNullString(eventTeamLike)){
            showDialogSuccessToast("Please Input Event ID  || Team Name");
            return;
        }

        if (DataUtils.isNullString(eventTeamLike)){
            DbHelper.loadEventById(Long.parseLong(eventId),list1 -> {
                if (list1.size() ==0){
                    showDialogSuccessToast("This Event No Find");
                    return;
                }
                eventModelAdapter.updateItems(list1);
            });
        }else {

            DbHelper.loadMoreEventList(0,eventTeamLike,list1 -> {

                if (list1.size()!=0){eventModelAdapter.updateItems(list1);eventOffset=0;}
            });
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initData();

    }


    private List<BetBean> list;
    private BetAdapter betAdapter;

    public static long lastRefreshTime;
    int eventOffset = 0;
    private List<EventModel> eventModelList;
    private EventModelAdapter eventModelAdapter;

    private List<EventLiveModel> eventLiveModels;
    private EventLiveModelAdapter eventLiveModelAdapter;
    private void initData() {
        etTeam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()==0) eventTeamLike="";
            }
        });

        if (isLive){
            eventLiveModels  = DbHelper.loadEventLiveList(0);
            eventLiveModelAdapter= new EventLiveModelAdapter(this,eventLiveModels,this);
            lvBet.setAdapter(eventLiveModelAdapter);
        }else {
            eventModelList  = DbHelper.loadEventList(0);
            eventModelAdapter = new EventModelAdapter(this, eventModelList, this);
            lvBet.setAdapter(eventModelAdapter);
        }



        customView.setPullRefreshEnable(true);
        // 设置是否可以上拉加载
        customView.setPullLoadEnable(true);
        // 设置上次刷新的时间
        customView.restoreLastRefreshTime(lastRefreshTime);
        //当下拉刷新被禁用时，调用这个方法并传入false可以不让头部被下拉
        customView.setMoveHeadWhenDisablePullRefresh(true);
        // 设置时候可以自动刷新
        customView.setAutoRefresh(false);
        customView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {

                eventOffset=1;

                if (isLive){

                    eventLiveModels  = DbHelper.loadEventLiveList(0);
                    eventLiveModelAdapter.updateItems(eventLiveModels);
                }else {
                    eventModelList  = DbHelper.loadEventList(0);
                    eventModelAdapter.updateItems(eventModelList);
                }

                new Handler().postDelayed(() -> {
                    customView.stopRefresh();
                    lastRefreshTime = customView.getLastRefreshTime();
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                if (isLive){
                    DbHelper.loadMoreEventLiveList(eventOffset, eventTeamLike,list -> {
                        if (list.size()!=0){eventLiveModelAdapter.addItems(list);eventOffset++;}
                        customView.stopLoadMore();
                    });
                }else {
                    DbHelper.loadMoreEventList(eventOffset, eventTeamLike,list -> {
                        if (list.size()!=0){eventModelAdapter.addItems(list);eventOffset++;}
                        customView.stopLoadMore();
                    });
                }



            }

            @Override
            public void onRelease(float direction) {
                super.onRelease(direction);
                if (direction > 0) {
                    ToastUtils.showToast("下拉");
                } else {
                    ToastUtils.showToast("上拉");
                }
            }
        });
        customView.setOnAbsListViewScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                LogUtils.i("onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                LogUtils.i("onScroll");
            }
        });


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_insert_new_bet;
    }



    BaseDialog gameDialog;
    private EditText mEt_bet_type;
    private ImageButton mBt_bet_type_search;
    private RecyclerView mRv_game;
    private RecyclerView mRv_Forecasts;
    private Button mBtn_cancel;

    private List<GameModel> gameModelList;
    private GameRecycleViewAdapter gameRecycleViewAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    public void getEventBean(EventModel bean) {
        toolbar.setSubtitle(bean.getTEAM1_NAME() + "-" + bean.getTEAM2_NAME());
        tvTeamName.setText(bean.getTEAM1_NAME() + "-" + bean.getTEAM2_NAME());

        gameModelList = DbHelper.loadGameByEventId(bean.getEVENT_ID());
        if (gameModelList.size() ==0){
            showDialogSuccessToast("This Event No Game");
            return;
        }

        gameRecycleViewAdapter =   new GameRecycleViewAdapter(gameModelList, bean1 -> {
//          List<BetForecastsModel> list =    DbHelper.loadForecastsList(bean1);
//            ForecastsRecycleViewAdapter forecastsRecycleViewAdapter = new ForecastsRecycleViewAdapter(list);
//            mRv_Forecasts.setAdapter(forecastsRecycleViewAdapter);

            List<BetForecastsLiveModel> list =    DbHelper.loadForecastsListTest(bean1);
            ForecastsLiveRecycleViewAdapter forecastsRecycleViewAdapter = new ForecastsLiveRecycleViewAdapter(list);
            mRv_Forecasts.setAdapter(forecastsRecycleViewAdapter);

        });


        if (gameDialog==null){
            gameDialog = new BaseDialog(this,R.style.AlertDialogStyle);
            View gView = ContextUtil.inflate(this,R.layout.dialog_game_layout);
            gameDialog.setContentView(gView);
            gameDialog.setCancelable(false);
            mEt_bet_type = (EditText)gView. findViewById(R.id.et_bet_type);
            mBt_bet_type_search = (ImageButton) gView.findViewById(R.id.bt_bet_type_search);
            mRv_game = (RecyclerView) gView.findViewById(R.id.rv_game);
            mRv_Forecasts = (RecyclerView) gView.findViewById(R.id.rv_Forecasts);
            //设置布局管理器 , 将布局设置成纵向
            LinearLayoutManager linerLayoutManager = new GridLayoutManager(this, 3);
            LinearLayoutManager linerLayoutManager2 = new GridLayoutManager(this, 3);
            mRv_game.setLayoutManager(linerLayoutManager);
            mRv_Forecasts.setLayoutManager(linerLayoutManager2);
           gView.findViewById(R.id.btn_cancel).setOnClickListener( v ->{
               gameDialog.cancel();
           });
        }

        mRv_game.setAdapter(gameRecycleViewAdapter);

        if (!gameDialog.isShowing())gameDialog.show();

    }


    private List<GameLiveModel> gameLiveModels;
    private GameLiveRecycleViewAdapter gameLiveRecycleViewAdapter;
    @SuppressLint("SetTextI18n")
    @Override
    public void getEventLiveBean(EventLiveModel bean) {
        toolbar.setSubtitle(bean.getTEAM1_NAME() + "-" + bean.getTEAM2_NAME());
        tvTeamName.setText(bean.getTEAM1_NAME() + "-" + bean.getTEAM2_NAME());
        gameLiveModels = DbHelper.loadGameLiveByEventId(bean.getEVENT_ID());
        if (gameLiveModels.size() ==0){
            showDialogSuccessToast("This Event No Game");
            return;
        }

        gameLiveRecycleViewAdapter =   new GameLiveRecycleViewAdapter(gameLiveModels, bean1 -> {
            List<BetForecastsLiveModel> list =    DbHelper.loadForecastsLiveList(bean1);
            ForecastsLiveRecycleViewAdapter forecastsRecycleViewAdapter = new ForecastsLiveRecycleViewAdapter(list);
            mRv_Forecasts.setAdapter(forecastsRecycleViewAdapter);

        });


        if (gameDialog==null){
            gameDialog = new BaseDialog(this,R.style.AlertDialogStyle);
            View gView = ContextUtil.inflate(this,R.layout.dialog_game_layout);
            gameDialog.setContentView(gView);
            gameDialog.setCancelable(false);
            mEt_bet_type = (EditText)gView. findViewById(R.id.et_bet_type);
            mBt_bet_type_search = (ImageButton) gView.findViewById(R.id.bt_bet_type_search);
            mRv_game = (RecyclerView) gView.findViewById(R.id.rv_game);
            mRv_Forecasts = (RecyclerView) gView.findViewById(R.id.rv_Forecasts);
            //设置布局管理器 , 将布局设置成纵向
            LinearLayoutManager linerLayoutManager = new GridLayoutManager(this, 3);
            LinearLayoutManager linerLayoutManager2 = new GridLayoutManager(this, 3);
            mRv_game.setLayoutManager(linerLayoutManager);
            mRv_Forecasts.setLayoutManager(linerLayoutManager2);
            gView.findViewById(R.id.btn_cancel).setOnClickListener( v ->{
                gameDialog.cancel();
            });
        }

        mRv_game.setAdapter(gameRecycleViewAdapter);

        if (!gameDialog.isShowing())gameDialog.show();
    }
}
