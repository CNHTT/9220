package com.odd.sportal.adapter;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.extra.adapter.BaseRecycleAdapter;
import com.odd.sportal.R;
import com.odd.sportal.inter.OnGameAdapter;
import com.odd.sportal.inter.OnGameLiveAdapter;
import com.odd.sportal.model.GameLiveModel;
import com.odd.sportal.model.GameModel;

import java.util.List;

/**
 * Created by 戴尔 on 2017/12/20.
 */

public class GameLiveRecycleViewAdapter extends BaseRecycleAdapter<GameLiveModel> {

    OnGameLiveAdapter listner;
    public GameLiveRecycleViewAdapter(List<GameLiveModel> datas, OnGameLiveAdapter listner) {
        super(datas);
        this.listner = listner;
    }

    @Override
    protected void bindData(BaseRecycleAdapter.BaseViewHolder holder, int position) {
        GameLiveModel gameModel = datas.get(position);
        ((TextView)holder.getView(R.id.v_game_descr)).setText(gameModel.getGAME_NAME());
        ((TextView)holder.getView(R.id.v_chart_descr)).setText(gameModel.getFC_TYPE());
        ((LinearLayout)holder.getView(R.id.ll_game_view)).setOnClickListener(v ->{
            listner.getBetGameBean(gameModel);
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_game_item;
    }
}
